/*
 * StarDict implementation for Android.
 * Copyright (C) 2022  Bohdan Kolvakh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package com.acmpo6ou.stardict.presentation.screens.dicts

import android.net.Uri
import android.system.ErrnoException
import android.system.Os
import android.system.OsConstants
import android.text.format.DateUtils.formatDateTime
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.stardict.SRC_DIR
import com.acmpo6ou.stardict.datastore.setting.UserDataRepository
import com.acmpo6ou.stardict.starDict
import com.acmpo6ou.stardict.utils.expansion.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import me.zhanghai.android.libarchive.Archive
import me.zhanghai.android.libarchive.ArchiveException
import org.yage.dict.star.StarDict
import splitties.init.appCtx
import java.io.File
import java.io.FileDescriptor
import java.io.InterruptedIOException
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import javax.inject.Inject


@HiltViewModel
open class DictViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    val userData get() = userDataRepository.userData

    fun setDictPath(value: List<String>) = userDataRepository.setDictPath(value)

    fun getDictPath() = userDataRepository.value.dictPath

    val mIntent = Channel<DictUIIntent>(Channel.UNLIMITED)

    //为了保证不能在UI中修改状态，我们应该定义一个可变的StateFlow与一个不可变的StateFlow
    private val _searchUiState: MutableStateFlow<DictStatusUiState> =
        MutableStateFlow(DictStatusUiState.Idle)
    val searchUiState: StateFlow<DictStatusUiState>
        get() = _searchUiState.asStateFlow()


    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mIntent.consumeAsFlow().collect {
                when (it) {
                    DictUIIntent.GetLoadedDictionaries -> getLoadedDictionaries(starDict.dicts)
                    is DictUIIntent.LoadDictionariesFromUris -> importDictByMediaStore(it.uris)
                    is DictUIIntent.RemoveLoadedDictionaries -> removeLoadedDictionaries()
                }
            }
        }
    }

    private fun removeLoadedDictionaries() {

    }

    private fun getLoadedDictionaries(dicts: List<StarDict>) {
        viewModelScope.launch {
            _searchUiState.value =
                DictStatusUiState.Success(dicts.map { StarDictInfo(it.name, it.filePath.first()) })
        }

    }


//    /**
//     * Copies dict files from [data] to SRC_DIR.
//     * @param data contains URIs with paths to dict files.
//     */
//    private fun copyDictFilesFromClip(data: ClipData) {
//        for (i in 0 until data.itemCount) {
//            val uri = data.getItemAt(i).uri
//            val ext = File(uri.path!!).extension
//
//            // skip all non dict files
//            if (ext !in listOf("idx", "ifo", "dict"))
//                continue
//
//            val name = File(uri.path!!).name
//            val file = File("${SRC_DIR}/$name")
//            val stream = app.contentResolver.openInputStream(uri)
//
//            stream.use {
//                file.writeBytes(it!!.readBytes())
//            }
//        }
//    }
//
//    /**
//     * Copies dict files using [copyDictFilesFromClip] handling all errors.
//     */
//    fun importDictFromClip(data: ClipData) {
//        try {
//            copyDictFilesFromClip(data)
//        } catch (e: Exception) {
//            e.printStackTrace()
//            error.value = e.toString()
//            errorDialog.show()
//            return
//        }
//
//        val path = data.getItemAt(0).uri.path!!
//        val name = File(path).nameWithoutExtension
//        loadDictionary(name)
//    }


    private suspend fun downloadAndExtractDictionary(uri: Uri) {


        Log.d("url", uri.path.toString())

//        if (!url.path!!.endsWith(".tar.bz2")) {
//            _searchUiState.value = DictStatusUiState.Error(Exception("Not a tar.bz2 file"))
//            return
//        }

        _searchUiState.value = DictStatusUiState.Loading("Preparing...")

        val dictPaths = DictPath()

        val releasedFiles = mutableListOf<String>()

        try {

            _searchUiState.value = DictStatusUiState.Loading("Creating temp file...")

//            val inputStream = AppContext.contentResolver.openInputStream(uri) ?: return
//
//            val targetDirectory = File(SRC_DIR)
//            // 检查目标目录是否存在，如果不存在则创建
//            if (!targetDirectory.exists()) targetDirectory.mkdirs()
//
//            // 从URL下载tar.bz2文件到临时文件
//            withContext(Dispatchers.IO) {
//
//
//                val tempFile = File.createTempFile("tempDict", ".tar.bz2")
//                releasedFiles.add(tempFile.path)
//                FileOutputStream(tempFile).let {
//                    inputStream.copyTo(out = it)
//                    it.close()
//                }
//                _searchUiState.value = DictStatusUiState.Loading("Extracting...")
//                TarArchiveInputStream(BZip2CompressorInputStream(FileInputStream(tempFile))).use { tarIn ->
//                    ZipUtil.createDirectory(targetDirectory.path, null)
//                    var entry: TarArchiveEntry
//                    while ((tarIn.nextTarEntry.also { entry = it }) != null) {
//                        Log.d("nextTarEntry is null:", tarIn.nextTarEntry.equals(null).toString())
//                        Log.d("entry", entry.equals(null).toString())
//                        if (entry.isDirectory) {
//                            releasedFiles.add(targetDirectory.path + File.separator + entry.name)
//                            _searchUiState.value =
//                                DictStatusUiState.Loading("Extracting Folder ${entry.name} to ${targetDirectory.path}...")
//                            ZipUtil.createDirectory(targetDirectory.path, entry.name)
//                        } else {
//                            releasedFiles.add(targetDirectory.path + File.separator + entry.name)
//                            _searchUiState.value =
//                                DictStatusUiState.Loading("Extracting File ${entry.name} to ${targetDirectory.path}...")
//                            FileOutputStream(targetDirectory.path + File.separator + entry.name).use { out ->
//                                ZipUtil.writeFile(tarIn, out)
//                            }
//                            Log.d("entry", entry.name)
//                            when {
//                                entry.name.contains(".dict.dz") -> {
//                                    _searchUiState.value =
//                                        DictStatusUiState.Loading("Unzipping ${entry.name} to ${targetDirectory.path}...")
//                                    val fullPath = "${targetDirectory.path}${File.separator}${entry.name}"
//                                    val fileNameWithoutExtension = fullPath.substring(fullPath.lastIndexOf("/")+1,fullPath.lastIndexOf("."))
//                                    File(fullPath)
//                                        .also { zipIn ->
//                                            ZipUtil.unzipFileIntoDirectory(
//                                                zipIn,
//                                                File(fullPath.substring(0, fullPath.lastIndexOf("/")))
//                                            )
//                                            fullPath.substring(0, fullPath.lastIndexOf(".")).toFile().apply {
//                                                if (this.exists() and this.isFile)
//                                                releasedFiles.add(this.absolutePath)
//                                            }
//                                        }
//                                    File(targetDirectory.path + File.separator + entry.name).delete()
//                                    dictPaths.dictFile =
//                                        ("${targetDirectory.path}/${entry.name}".toFile().nameWithoutExtension + ".dict")
//                                }
//
//                                entry.name.contains("ifo") -> {
//                                    _searchUiState.value =
//                                        DictStatusUiState.Loading("Configuring ${entry.name} to ${targetDirectory.path}...")
//                                    dictPaths.ifoFile =
//                                        (targetDirectory.path + File.separator + entry.name)
//                                }
//
//                                entry.name.contains("idx") -> {
//                                    _searchUiState.value =
//                                        DictStatusUiState.Loading("Configuring ${entry.name} to ${targetDirectory.path}...")
//                                    dictPaths.indexFile =
//                                        targetDirectory.path + File.separator + entry.name
//                                }
//                            }
//                        }
//                    }
//                    if ((dictPaths.dictFile != null) and (dictPaths.indexFile != null) and (dictPaths.ifoFile != null)) {
//
//                        dictPaths.dictFile?.toFile()?.parent?.let { path: String ->
//                            starDict.loadDictionary(path)
//                            setDictPath(getDictPath().toMutableList()
//                                .also { it.add(path) })
//                        }
//                    }
//                }
//
//                tempFile.delete()
//                _searchUiState.value =
//                    DictStatusUiState.Success(starDict.dicts.map {
//                        StarDictInfo(
//                            it.name,
//                            it.filePath.first()
//                        )
//                    })
//            }
        } catch (e: Exception) {
            releasedFiles.forEach { it.toFile().deleteRecursively() }
            _searchUiState.value = DictStatusUiState.Error(Exception("Error:$uri", e))
            Log.e("importDictByMediaStore", e.stackTraceToString())
            throw e

        }
    }

    //从url获取tar.bz2格式的字典文件路径,将此文件里面的dict.dz、idx、ifo文件解压到应用内部的data/files/dicts/词典名称/，并将dict.dz作为zip解压到同目录，整个函数用try catch处理，遇到问题抛出异常
    private fun importDictByMediaStore(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            downloadAndExtractDictionary(uri)
        }


//        //only support tar.bz2 format dict archive
//        uriList.forEach { uri ->
//            uri.toFile().apply {
//                if (this.exists()) {
//                    val mimeType = this.getMimeType()
//                    if (mimeType.contains("bzip2")) {
////                        uncompressBzip2Dict()
//                    }
//                    if (this.extension == "bz2") {
////                        uncompressBzip2Dict(this.absoluteFile)
//                    }
//                    else if (this.extension == "ifo") || (this.extension == "idx")   {
//                        val dictName = this.nameWithoutExtension
//                        this.parentFile
//                            ?.listFiles { dir, name -> name.contains(this.nameWithoutExtension) }
//                            ?.map { it.name }
//                            ?.run {
//                                this.takeIf {
//                                    it.containsAll(listOf(
//                                        "$dictName.ifo",
//                                        "$dictName.idx"
//                                    ))
//                                }?.takeIf {
//                                    it.contains("$dictName.dict") or it.contains("$dictName.dz")
//                                }?.run {
//                                    val path = uri.toFile().parentFile?.path
//
//                                    if(this.count()== 3){
//
//                                    }else if (this.count()>3){
//                                        val filesWillCopy = mutableListOf("$dictName.ifo", "$dictName.idx")
//                                        if (File("$path/$dictName.dict").exists()){
//                                            filesWillCopy.add("$path/$dictName.dict")
//                                        }else if (File("$path/$dictName.dz").exists()){
//                                            filesWillCopy.add("$path/$dictName.dz")
//                                        } else { }
//                                        filesWillCopy.map { File(it) }.forEach {
//                                            try {
//                                                Path("$SRC_DIR/${it.nameWithoutExtension}").createDirectories()
//                                                it.source().buffer().use { bufferedSource ->
//                                                    File("$SRC_DIR/${it.nameWithoutExtension}/${it.name}").sink().buffer().use { bufferedSink ->
//                                                        bufferedSink.writeAll(bufferedSource)
//                                                    }
//                                                }
//                                            } catch (e: IOException) {
//                                                e.printStackTrace()
//                                            }
//                                        }
//                                    } else {
//
//                                    }
//                                }
//
//                            }
//
//                    }
//                    else if (this.extension == "idx") {
//
//                    }
//                    else if (this.extension == "dict") {
//
//                    }
//                    else if (this.extension == "dz") {
//
//                    }
//                    else {
//                        throw Exception("unsupported star dict file format!")
//                    }
//                }
//            }
//        }
//
//        val archiveFile = File("/home/test.zip")
//        val outputDir = File("/home/peterlee/output")
//
//        ArchiveStreamFactory.detect(FileInputStream("/home/test.zip"))
//
//// 指定文件所用字符集，这里以UTF-8为例
//        val factory = ArchiveStreamFactory("UTF-8")
//        FileInputStream(archiveFile).use { inputStream ->
//            factory.createArchiveInputStream(ArchiveStreamFactory.ZIP, inputStream)
//                .use { archiveInputStream ->
//                    var archiveEntry: ArchiveEntry = archiveInputStream.getNextEntry()
//                    var outputStream: OutputStream
//                    var outputFile: File
//                    val buffer = ByteArray(512)
//                    var bytesRead: Int
//                    while (archiveInputStream.nextEntry.also { archiveEntry = it } != null) {
//                        outputFile = File(outputDir, archiveEntry.name)
//                        if (!outputFile.getParentFile()?.exists()!!) {
//                            outputFile.getParentFile()?.mkdirs()
//                        }
//                        outputStream = FileOutputStream(outputFile)
//
//                        // 进行数据拷贝
//                        while (archiveInputStream.read(buffer).also { bytesRead = it } > 0) {
//                            outputStream.write(buffer, 0, bytesRead)
//                        }
//                        outputStream.close()
//                    }
//                }
//        }
//        runCatching {
//
//            for (uri in uriList) {
//                val ext = File(uri.path!!).extension
//
//                // skip all non dict files
//                if (ext in listOf("idx", "ifo", "dict")) {
//                    val name = File(uri.path!!).name
//                    val file = File("${SRC_DIR}/$name")
//                    val stream = context.contentResolver.openInputStream(uri)
//
//                    stream?.use {
//                        file.writeBytes(uriList.readBytes())
//                    }
//                }
//            }
//            copyDictFilesFromUri(context = context, uriList = uriList)
//            uriList.forEach {
//                val path = it.path!!
//                val name = File(path).nameWithoutExtension
//                starDict.loadDictionary(name)
//            }
//
//        }.fold(
//            onSuccess = {
//                Toast.makeText(context, "finish!", Toast.LENGTH_LONG).show()
//            },
//            onFailure = {
////                error.value = it.localizedMessage
////                errorDialog.show()
//            }
//        )
//
//
    }
//
//    private fun copyDictFilesFromUri(context: Context, uriList: List<Uri>) {
//
//    }


}


data class DictPath(
    var indexFile: String? = null,
    var dictFile: String? = null,
    var ifoFile: String? = null
)
