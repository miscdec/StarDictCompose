package org.yage.dict.star

import com.acmpo6ou.stardict.utils.expansion.toFile
import okio.Path
import java.io.FileFilter

class StarDictEngine private constructor(
)  {

    private var _dictionaries = mutableListOf<StarDict>()

    val dicts = _dictionaries.toList()

    /**
     * Populates [_dictionaries] with dictionaries residing in the SRC_DIR.
     */
    fun loadDictionaries(path: String) {
        path.toFile().listFiles(FileFilter {
            it.name.contains(".ifo",ignoreCase = true)
        })?.forEach { file ->
            loadDictionary(file.path.substring(0, file.path.lastIndexOf(".")))
        }
    }

    fun loadDictionaries(list: List<String>) {
        list.forEach { loadDictionary(it) }
    }

    /**
     * Loads dictionary handling all errors, deletes all files of a dict
     * from SRC_DIR if there is an error.
     */
    fun loadDictionary(path:String,deleteFiles: Boolean = true): Result<String> {
        try {
            StarDict().run {
                initialize(indexFilePath = "$path.idx", dictFilePath = "$path.dict", ifoFilePath = "$path.ifo")
                addDict(this)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // delete all dict files since they are probably invalid
            if(deleteFiles) path.toFile().parentFile?.deleteRecursively()
//            for (ext in listOf("ifo", "idx", "dict"))
//                File("$path").delete()
            return Result.failure(e.fillInStackTrace())
        }
        return Result.success("OK")
    }

    private fun addDict(dict: StarDict) {
        if (dict.filePath !in _dictionaries.map { it.filePath }) {
            _dictionaries.add(dict)
        }
    }

    private fun removeDict(dict: StarDict) {
        _dictionaries.remove(dict)
    }

    /**
     * Deletes dict files and removes the dict from dicts list.
     */
    fun removeDictionary(path: Path, deleteFiles:Boolean = false){
//        _dictionaries.run {
//            this.filter {
////                it.filePath == "${path.toFile().path}"
//            }.forEach { dict ->
//                removeDict(dict)
//                if (deleteFiles){
//                    for (ext in listOf("ifo", "idx", "dict")) {
//                        val file = File("${dict.filePath}.$ext")
//                        file.delete()
//                    }
//                }
//            }
//        }

    }

    /**
     * Deletes dict files and removes the dict from dicts list.
     */


    operator fun invoke(): StarDictEngine {
        return instance
    }


    companion object {
        val instance: StarDictEngine by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            StarDictEngine()
        }
    }
}