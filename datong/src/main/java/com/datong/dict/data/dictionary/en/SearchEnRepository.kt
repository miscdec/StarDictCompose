package com.datong.dict.data.dictionary.en

import android.content.Context
import com.datong.dict.data.dictionary.en.local.SearchEnLocalDateSource
import com.datong.dict.data.dictionary.en.source.SearchEnDataSource
import com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
import com.datong.dict.utils.TextUtil
import com.datong.dict.utils.rvHelper.BaseItem

/* loaded from: classes.dex */
class SearchEnRepository private constructor(context: Context) : SearchEnDataSource {
    private val cachedResult: MutableMap<String, MutableList<BaseItem>?> = HashMap()
    private val localDataSource = SearchEnLocalDateSource.getInstance(context)

    init {
        //        this.remoteDataSource = SearchEnRemoteDateSource.getInstance(context);
    }

    // com.datong.dict.data.dictionary.en.source.SearchEnDataSource
    override fun getSearchResult(keywords: String, callback: SearchResultCallback) {
        val replace = keywords.replace("%", "").replace("_", "").replace("＊", "*").replace("？", "?")
        if (replace.replace(" ", "").replace("*", "").replace("?", "") == "") {
            callback.onError()
            return
        }
        val map: Map<String, MutableList<BaseItem>?> = this.cachedResult
        if (map != null && map.containsKey(replace)) {
            if (callback != null) {
                callback.onLoad(cachedResult[replace])
            }
        }
        //        else if (SettingUtil.isCloudSearch()) {
//            getFromRemoteDataSource(replace, callback);
//        } else {
        getFromLocalDataSource(replace, callback)
        //        }
    }

    //    private void getFromRemoteDataSource(final String keywords, final SearchEnDataSource.SearchResultCallback callback) {
    //        if (!NetworkUtil.isUsable()) {
    //            getFromLocalDataSource(keywords, callback);
    //        } else {
    //            this.remoteDataSource.getSearchResult(keywords, new SearchEnDataSource.SearchResultCallback() { // from class: com.datong.dict.data.dictionary.en.SearchEnRepository.1
    //                @Override // com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
    //                public void onLoad(List<BaseItem> resultList) {
    //                    if (!TextUtil.isLetter(keywords)) {
    //                        resultList = SearchEnRepository.this.handleSearchResult(keywords, resultList);
    //                    }
    //                    SearchEnRepository.this.refreshCache(keywords, resultList);
    //                    SearchEnDataSource.SearchResultCallback searchResultCallback = callback;
    //                    if (searchResultCallback != null) {
    //                        searchResultCallback.onLoad((List) SearchEnRepository.this.cachedResult.get(keywords));
    //                    }
    //                }
    //
    //                @Override // com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
    //                public void onError() {
    //                    SearchEnRepository.this.getFromLocalDataSource(keywords, callback);
    //                }
    //            });
    //        }
    //    }
    /* JADX INFO: Access modifiers changed from: private */
    fun getFromLocalDataSource(keywords: String, callback: SearchResultCallback) {
        localDataSource!!.getSearchResult(keywords, object : SearchResultCallback {
            // from class: com.datong.dict.data.dictionary.en.SearchEnRepository.2
            // com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
            override fun onLoad(resultList: MutableList<BaseItem>) {
                var resultList = resultList
                if (!TextUtil.isLetter(keywords)) {
                    resultList = this@SearchEnRepository.handleSearchResult(keywords, resultList)
                }
                this@SearchEnRepository.refreshCache(keywords, resultList)
                if (callback != null) {
                    callback.onLoad(cachedResult[keywords])
                }
            }

            // com.datong.dict.data.dictionary.en.source.SearchEnDataSource.SearchResultCallback
            override fun onError() {
                callback.onError()
                //                SearchEnRepository.this.remoteDataSource.getSearchResult(keywords, callback);
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun refreshCache(keywords: String, wordList: MutableList<BaseItem>?) {
        if (wordList == null || wordList.size == 0) {
            return
        }
        if (cachedResult.containsKey(keywords)) {
            if (cachedResult[keywords] != null) {
                cachedResult[keywords]!!.addAll(wordList)
                return
            }
            return
        }
        cachedResult[keywords] = wordList
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun handleSearchResult(
        keywords: String,
        resultList: MutableList<BaseItem>
    ): MutableList<BaseItem> {
        var str: String
        var str2: String
        var str3: String
        var str4: String
        val str5 = ". $keywords\n"
        val str6 = ". $keywords，"
        val str7 = ". $keywords；"
        val str8 = "，$keywords\n"
        val str9 = "，$keywords，"
        val str10 = "，$keywords；"
        val str11 = "；$keywords\n"
        val str12 = "；$keywords，"
        val str13 = "；$keywords；"
        val sb = StringBuilder()
        sb.append(". ")
        sb.append(keywords)
        val str14 = ". " + keywords + "的\n"
        sb.append("的，")
        val sb2 = sb.toString()
        val str15 = ". " + keywords + "的；"
        val str16 = "，" + keywords + "的\n"
        val str17 = "，" + keywords + "的，"
        val str18 = "，" + keywords + "的；"
        val str19 = "；" + keywords + "的\n"
        val str20 = "；" + keywords + "的，"
        val str21 = "；" + keywords + "的；"
        val arrayList = ArrayList<BaseItem>()
        val i = 0
        arrayList.size
        arrayList.addAll(resultList)
        resultList.clear()
        return arrayList
    }

    companion object {
        private var INSTANCE: SearchEnRepository? = null

        //    private final SearchEnRemoteDateSource remoteDataSource;
        fun getInstance(context: Context): SearchEnRepository? {
            if (INSTANCE == null) {
                INSTANCE = SearchEnRepository(context)
            }
            return INSTANCE
        }
    }
}
