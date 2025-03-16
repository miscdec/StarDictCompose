package com.datong.dict.data.dictionary.jp.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.datong.dict.data.dictionary.jp.local.dao.DatongJapContentDao
import com.datong.dict.data.dictionary.jp.local.dao.DatongJapIndexDao
import com.datong.dict.data.dictionary.jp.local.database.DatongJapDatabase
import com.datong.dict.data.dictionary.jp.local.entity.datong.Content
import com.datong.dict.data.dictionary.jp.local.entity.datong.DatongJapWord
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index
import com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
import com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordIndexCallback
import com.datong.dict.utils.PathUtil
import com.datong.dict.utils.RC4Util
import java.io.File

/* loaded from: classes.dex */
class DatongJapLocalDateSource private constructor(context: Context) :
    DatongJapDateSource {
    private val contentDao: DatongJapContentDao = DatongJapDatabase.getInstance(context).contentDao()
    private val indexDao: DatongJapIndexDao = DatongJapDatabase.getInstance(context).indexDao()

    // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
    override fun getWord(wordId: String, callback: DatongJapDateSource.GetWordCallback) {
        if (!isDatabaseExists) {
            callback.error()
        } else {
            // from class: com.datong.dict.data.dictionary.jp.local.DatongJapLocalDateSource$$ExternalSyntheticLambda0
// java.lang.Runnable
            Thread(Runnable {
                val index: Index = indexDao.getIndex(wordId)
                if (index == null) {
                    // from class: com.datong.dict.data.dictionary.jp.local.DatongJapLocalDateSource$$ExternalSyntheticLambda2
                    // java.lang.Runnable
                    Handler(Looper.getMainLooper()).post { callback.error() }
                    return@Runnable
                }
                index.word = d(index.word)
                index.kana = d(index.kana)
                index.romaji = d(index.romaji)
                index.expBase = d(index.expBase)
                index.expJP = d(index.expJP)
                val content: Content =
                    contentDao.getContent(wordId)
                content.expCJ = d(content.expCJ)
                content.example = d(content.example)
                val datongJapWord = DatongJapWord()
                datongJapWord.index = index
                datongJapWord.content = content
                // from class: com.datong.dict.data.dictionary.jp.local.DatongJapLocalDateSource$$ExternalSyntheticLambda3
// java.lang.Runnable
                Handler(Looper.getMainLooper()).post { callback.onload(datongJapWord) }
            }).start()
        }
    }

    /* synthetic */ /* synthetic */ /* synthetic */
    // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
    override fun getWordIndex(word: String, callback: GetWordIndexCallback) {
        if (!isDatabaseExists) {
            callback.error()
        } else {
            Thread {
                val indexByText = indexDao.getIndexByText(e(word))
                for (index: Index in indexByText) {
                    index.word = d(index.word)
                    index.kana = d(index.kana)
                    index.romaji = d(index.romaji)
                    index.expBase = d(index.expBase)
                    index.expJP = d(index.expJP)
                }
                //        LogUtils.log("word-->" + str);
                //        LogUtils.log("getWordIndex-->" + indexByText.size());
                Handler(Looper.getMainLooper()).post { callback.onload(indexByText) }
            }.start()
        }
    }

    private val isDatabaseExists: Boolean
        get() = File(PathUtil.PATH_DOWNLOAD + "/DICT_JP_DATONG").exists()

    companion object {
        private var INSTANCE: DatongJapLocalDateSource? = null
        @JvmStatic
        fun getInstance(context: Context): DatongJapLocalDateSource? {
            if (INSTANCE == null) {
                INSTANCE = DatongJapLocalDateSource(context)
            }
            return INSTANCE
        }

        /* synthetic */ /* synthetic */
        private fun e(str: String): String {
            val sb = StringBuilder()
            var i = 0
            while (i < str.length) {
                try {
                    val i2 = i + 1
                    sb.append(RC4Util.encryString(str.substring(i, i2), "u5JphK9Iw7"))
                    if (i < str.length - 1) {
                        sb.append("-")
                    }
                    i = i2
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return sb.toString()
        }

        private fun d(str: String?): String {
            val sb = StringBuilder()
            try {
                for (str2: String in str!!.split("-".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()) {
                    sb.append(RC4Util.decryString(str2, "u5JphK9Iw7"))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return sb.toString()
        }
    }
}
