package com.datong.dict.data.dictionary.en

import android.content.Context
import com.datong.dict.data.dictionary.en.local.DatongEnLoaclDataSource
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord
import com.datong.dict.data.dictionary.en.source.DatongEnDateSource

/* loaded from: classes.dex */
class DatongEnRepository private constructor(context: Context) : DatongEnDateSource {
    private val cachedWord: MutableMap<String, DatongEnWord> = HashMap()
    //        this.remoteDataSource = null;
    private var localDataSource: DatongEnLoaclDataSource = DatongEnLoaclDataSource.getInstance(context)

    init {
        //        this.remoteDataSource = DatongEnRemoteDataSource.getInstance(context);
    }

    // com.datong.dict.data.dictionary.en.source.DatongEnDateSource
    override fun getWord(keywords: String, callback: DatongEnDateSource.GetWordCallback) {
        val map: Map<String, DatongEnWord> = this.cachedWord
        if (map != null && map.containsKey(keywords)) {
            callback.onLoad(cachedWord[keywords])
        } else {
            getFromLocalDataSource(keywords, callback)
        }
    }

    private fun getFromLocalDataSource(
        keywords: String,
        callback: DatongEnDateSource.GetWordCallback
    ) {
        localDataSource.getWord(keywords, object : DatongEnDateSource.GetWordCallback {
            // from class: com.datong.dict.data.dictionary.en.DatongEnRepository.1
            // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
            override fun onLoad(word: DatongEnWord) {
                this@DatongEnRepository.refreshCache(keywords, word)
                callback.onLoad(word)
            }

            // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
            override fun onError() {
                callback.onError()
                //                DatongEnRepository.this.getFromRemoteDataSource(keywords, callback);
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: private */ //    public void getFromRemoteDataSource(final String keywords, final DatongEnDateSource.GetWordCallback callback) {
    //        if (!NetworkUtil.isUsable()) {
    //            callback.onError();
    //        } else {
    //            this.remoteDataSource.getWord(keywords, new DatongEnDateSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.en.DatongEnRepository.2
    //                @Override // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
    //                public void onLoad(DatongEnWord word) {
    //                    DatongEnRepository.this.refreshCache(keywords, word);
    //                    callback.onLoad(word);
    //                }
    //
    //                @Override // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
    //                public void onError() {
    //                    callback.onError();
    //                }
    //            });
    //        }
    //    }
    /* JADX INFO: Access modifiers changed from: private */
    fun refreshCache(key: String, word: DatongEnWord?) {
        if (word != null) {
            cachedWord[key] = word
        }
    }

    companion object {
        private var INSTANCE: DatongEnRepository? = null

        //    private DatongEnRemoteDataSource remoteDataSource;
        fun getInstance(context: Context): DatongEnRepository? {
            if (INSTANCE == null) {
                INSTANCE = DatongEnRepository(context)
            }
            return INSTANCE
        }
    }
}
