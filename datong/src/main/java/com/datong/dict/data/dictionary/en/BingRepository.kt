package com.datong.dict.data.dictionary.en

import android.content.Context
import com.datong.dict.data.dictionary.en.local.BingLocalDataSource
import com.datong.dict.data.dictionary.en.local.entity.BingWord
import com.datong.dict.data.dictionary.en.source.BingDataSource

/* loaded from: classes.dex */
class BingRepository private constructor(context: Context) : BingDataSource {
    private val cachedWord = HashMap<String, BingWord>()
    private var loaclDataSoucre: BingLocalDataSource = BingLocalDataSource.getInstance(context)!!


    // com.datong.dict.data.dictionary.en.source.BingDataSource
    override fun getWord(keywords: String, callback: BingDataSource.GetWordCallback) {
        val hashMap = this.cachedWord
        if (hashMap != null && hashMap.containsKey(keywords)) {
            callback.onLoad(cachedWord[keywords])
        } else {
            getFromLocalDataSource(keywords, callback)
        }
    }

    private fun getFromLocalDataSource(keywords: String, callback: BingDataSource.GetWordCallback) {
        loaclDataSoucre.getWord(keywords, object : BingDataSource.GetWordCallback {
            // from class: com.datong.dict.data.dictionary.en.BingRepository.1
            // com.datong.dict.data.dictionary.en.source.BingDataSource.GetWordCallback
            override fun onLoad(word: BingWord) {
                refreshCache(keywords, word)
                callback.onLoad(word)
            }

            // com.datong.dict.data.dictionary.en.source.BingDataSource.GetWordCallback
            override fun onError() {
                callback.onError()
//                BingRepository.this.getFromRemoteDataSource(keywords, callback);
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: private */ //    public void getFromRemoteDataSource(final String keywords, final BingDataSource.GetWordCallback callback) {
    //        if (!NetworkUtil.isUsable()) {
    //            callback.onError();
    //        } else {
    //            this.remoteDataSoucre.getWord(keywords, new BingDataSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.en.BingRepository.2
    //                @Override // com.datong.dict.data.dictionary.en.source.BingDataSource.GetWordCallback
    //                public void onLoad(BingWord word) {
    //                    BingRepository.this.refreshCache(keywords, word);
    //                    callback.onLoad(word);
    //                }
    //
    //                @Override // com.datong.dict.data.dictionary.en.source.BingDataSource.GetWordCallback
    //                public void onError() {
    //                    callback.onError();
    //                }
    //            });
    //        }
    //    }
    /* JADX INFO: Access modifiers changed from: private */
    fun refreshCache(key: String, word: BingWord?) {
        if (word != null) {
            cachedWord[key] = word
        }
    }

    companion object {
        private var INSTANCE: BingRepository? = null

        //    private BingRemoteDataSoure remoteDataSoucre;
        fun getInstance(context: Context): BingRepository? {
            if (INSTANCE == null) {
                INSTANCE = BingRepository(context)
            }
            return INSTANCE
        }
    }
}
