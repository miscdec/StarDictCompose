package com.datong.dict.data.dictionary.jp.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.datong.dict.data.dictionary.jp.local.dao.DatongJapIndexDao
import com.datong.dict.data.dictionary.jp.local.database.DatongJapDatabase
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index
import com.datong.dict.data.dictionary.jp.source.SearchJapDataSource
import com.datong.dict.data.dictionary.jp.source.SearchJapDataSource.SearchCallback
import com.datong.dict.utils.PathUtil
import com.datong.dict.utils.RC4Util
import java.io.File

/* loaded from: classes.dex */
class SearchJapLocalDateSource private constructor(context: Context) :
    SearchJapDataSource {
    private val indexDao: DatongJapIndexDao = DatongJapDatabase.getInstance(context).indexDao()

    // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource
    override fun search(keywords: String, callback: SearchCallback) {
        if (!File(PathUtil.PATH_DOWNLOAD + "/DICT_JP_DATONG").exists()) {
            callback.error()
        } else {
            Thread(object : Runnable {
                // from class: com.datong.dict.data.dictionary.jp.local.SearchJapLocalDateSource$$ExternalSyntheticLambda0
                // java.lang.Runnable
                override fun run() {
                    var indexByWildcard: List<Index>
                    if (keywords.contains("*") || keywords.contains("?")) {
                        indexByWildcard = indexDao.getIndexByWildcard(
                            e(keywords).replace(e("*"), "%").replace(
                                e("?"), "______"
                            )
                        )
                    } else {
                        indexByWildcard = indexDao.getIndex(e(keywords) + "%", "%\"$keywords\"%")
                        if (indexByWildcard == null || indexByWildcard.isEmpty()) {
                            indexByWildcard = indexDao.getIndexByBlurry(
                                "%$keywords%"
                            )
                            if (indexByWildcard == null || indexByWildcard.isEmpty()) {
                                Handler(Looper.getMainLooper()).post { callback.error() }
                                return
                            }
                        }
                    }
                    for (index: Index in indexByWildcard) {
                        index.word = d(index.word)
                        index.kana = d(index.kana)
                        index.romaji = d(index.romaji)
                        index.expBase = d(index.expBase)
                        index.expJP = d(index.expJP)
                    }
                    val finalIndexByWildcard: List<Index> = indexByWildcard
                    Handler(Looper.getMainLooper()).post { callback.onload(finalIndexByWildcard) }
                }
            }).start()
        }
    }

    companion object {
        private var INSTANCE: SearchJapLocalDateSource? = null
        @JvmStatic
        fun getInstance(context: Context): SearchJapLocalDateSource? {
            if (INSTANCE == null) {
                INSTANCE = SearchJapLocalDateSource(context)
            }
            return INSTANCE
        }

        /* synthetic */ /* synthetic */ /* synthetic */
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
