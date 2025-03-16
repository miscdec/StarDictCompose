package com.datong.dict.data.dictionary.en

import android.content.Context
import com.datong.dict.data.dictionary.en.local.YoudaoLocalDataSource
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord
import com.datong.dict.data.dictionary.en.source.YoudaoDataSource

/* loaded from: classes.dex */
class YoudaoRepository private constructor(context: Context) : YoudaoDataSource {
    private val cacheWord: MutableMap<String?, YoudaoWord?> = HashMap()
    //        this.remoteDataSoucre = null;
    private var loaclDataSoucre: YoudaoLocalDataSource = YoudaoLocalDataSource.getInstance(context)!!

    init {
        //        this.remoteDataSoucre = YoudaoRemoteDataSource.getInstance();
    }

    // com.datong.dict.data.dictionary.en.source.YoudaoDataSource
    override fun getWord(keywords: String, callback: YoudaoDataSource.GetWordCallback) {
        val map: Map<String?, YoudaoWord?> = this.cacheWord
        if (map != null && map.containsKey(keywords)) {
            callback.onLoad(cacheWord[keywords])
        } else {
            getFromLocalDataSource(keywords, callback)
        }
    }

    private fun getFromLocalDataSource(
        keywords: String,
        callback: YoudaoDataSource.GetWordCallback
    ) {
        loaclDataSoucre!!.getWord(keywords, object : YoudaoDataSource.GetWordCallback {
            // from class: com.datong.dict.data.dictionary.en.YoudaoRepository.1
            // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
            override fun onLoad(word: YoudaoWord) {
                this@YoudaoRepository.refreshCache(keywords, word)
                callback.onLoad(word)
            }

            // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
            override fun onError() {
                callback.onError()
                //                YoudaoRepository.this.getFromRemoteDataSource(keywords, callback);
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: private */ //    public void getFromRemoteDataSource(final String keywords, final YoudaoDataSource.GetWordCallback callback) {
    //        if (!NetworkUtil.isUsable()) {
    //            callback.onError();
    //        } else {
    //            this.remoteDataSoucre.getWord(keywords, new YoudaoDataSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.en.YoudaoRepository.2
    //                @Override // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
    //                public void onLoad(YoudaoWord word) {
    //                    YoudaoRepository.this.refreshCache(keywords, word);
    //                    callback.onLoad(word);
    //                }
    //
    //                @Override // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
    //                public void onError() {
    //                    callback.onError();
    //                }
    //            });
    //        }
    //    }
    /* JADX INFO: Access modifiers changed from: private */
    fun refreshCache(key: String?, word: YoudaoWord?) {
        if (word != null) {
            cacheWord[key] = word
        }
    }

    companion object {
        private var INSTANCE: YoudaoRepository? = null

        //    private YoudaoRemoteDataSource remoteDataSoucre;
        fun getInstance(context: Context): YoudaoRepository? {
            if (INSTANCE == null) {
                INSTANCE = YoudaoRepository(context)
            }
            return INSTANCE
        }
    }
}
