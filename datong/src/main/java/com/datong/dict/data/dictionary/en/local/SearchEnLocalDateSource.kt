package com.datong.dict.data.dictionary.en.local

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.datong.dict.data.dictionary.en.local.dao.SearchDao
import com.datong.dict.data.dictionary.en.local.database.DatongDatabase
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord
import com.datong.dict.data.dictionary.en.source.SearchEnDataSource
import com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
import com.datong.dict.module.home.menus.search.items.ResultEn2EnItem
import com.datong.dict.utils.ConvertUtil
import com.datong.dict.utils.SecretUtil
import com.datong.dict.utils.TextUtil
import com.datong.dict.utils.rvHelper.BaseItem

/* loaded from: classes.dex */
class SearchEnLocalDateSource private constructor(context: Context) : SearchEnDataSource {

    private val dao: SearchDao = DatongDatabase.getInstance(context)!!.searchDao()

    // com.datong.dict.data.dictionary.en.source.SearchEnDataSource
    override fun getSearchResult(keywords: String, callback: SearchResultCallback) {
        if (TextUtil.isLetter(keywords)) {
            getSearchResultByWord(keywords, callback)
        } else {
            getSearchResultByExpCN(keywords, callback)
        }
    }

    private fun getSearchResultByWord(keywords: String, callback: SearchResultCallback) {
        Thread {
            if (keywords.contains("*") || keywords.contains("?")) {
                getSearchResultByWildcard(this.dao, keywords, callback)
                return@Thread
            }
            val lastNextChar = TextUtil.lastNextChar(keywords)
            val upperFrist = TextUtil.upperFrist(keywords)
            val searchWordByEN =
                dao.searchWordByEN(keywords, lastNextChar, upperFrist, TextUtil.lastNextChar(upperFrist))
            Handler(Looper.getMainLooper()).post {
                if (searchWordByEN.isEmpty()) {
                    callback.onError()
                } else {
                    callback.onLoad(decrypt(searchWordByEN))
                }
            }
        }.start()
    }


    private fun getSearchResultByExpCN(keywords: String, callback: SearchResultCallback) {
        Thread {
            val searchDao = this.dao
            val searchWordByCN = searchDao.searchWordByCN("%$keywords%")
            Handler(Looper.getMainLooper()).post {
                if (searchWordByCN.isEmpty()) {
                    callback.onError()
                } else {
                    callback.onLoad(decrypt(searchWordByCN))
                }
            }
        }.start()
    }


    private fun getSearchResultByWildcard(
        dao: SearchDao,
        keywords: String,
        callback: SearchResultCallback
    ) {
        Thread {
            val searchByWildcard = dao.searchByWildcard(keywords.replace("*", "%").replace("?", "_"))
            Handler(Looper.getMainLooper()).post {
                if (searchByWildcard.isEmpty()) {
                    callback.onError()
                } else {
                    callback.onLoad(decrypt(searchByWildcard))
                }
            }
        }.start()
    }


    private fun decrypt(wordList: List<DatongEnWord>): List<BaseItem> {
        val arrayList = ArrayList<ResultEn2EnItem>()
        for (datongEnWord: DatongEnWord in wordList) {
            if (!TextUtils.isEmpty(ConvertUtil.MAP_DATONG_EN)) {
                datongEnWord.phoneticUk =
                    ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, datongEnWord.phoneticUk)
                datongEnWord.phoneticUs =
                    ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, datongEnWord.phoneticUs)
                datongEnWord.shape =
                    ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, datongEnWord.shape)
                datongEnWord.expCN =
                    ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, datongEnWord.expCN)
                datongEnWord.expEN =
                    ConvertUtil.decrypt(ConvertUtil.MAP_DATONG_EN, datongEnWord.expEN)
            } else {
                datongEnWord.phoneticUk =
                    SecretUtil.decrypt(datongEnWord.phoneticUk, datongEnWord.word)
                datongEnWord.phoneticUs =
                    SecretUtil.decrypt(datongEnWord.phoneticUs, datongEnWord.word)
                datongEnWord.expEN = SecretUtil.decrypt(datongEnWord.expEN, datongEnWord.word)
            }
            arrayList.add(ResultEn2EnItem(datongEnWord))
        }
        return arrayList
    }

    companion object {
        private var INSTANCE: SearchEnLocalDateSource? = null
        @JvmStatic
        fun getInstance(context: Context): SearchEnLocalDateSource? {
            if (INSTANCE == null) {
                INSTANCE = SearchEnLocalDateSource(context)
            }
            return INSTANCE
        }
    }
}
