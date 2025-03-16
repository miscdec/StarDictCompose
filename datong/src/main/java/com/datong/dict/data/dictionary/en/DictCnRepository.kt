package com.datong.dict.data.dictionary.en

import android.content.Context
import com.datong.dict.base.callback.GetWordCallback
import com.datong.dict.data.dictionary.en.local.DictCnLocalDataSource
import com.datong.dict.data.dictionary.en.local.entity.DictCnWord
import com.datong.dict.data.dictionary.en.source.DictCnDataSource

/* loaded from: classes.dex */
class DictCnRepository private constructor(context: Context) : DictCnDataSource {
    private val localDataSource: DictCnLocalDataSource = DictCnLocalDataSource.getInstance(context)!!

    //    private DictCnRemoteDataSource remoteDataSource = DictCnRemoteDataSource.getInstance();
    private val cachedWord: MutableMap<String, DictCnWord> = HashMap()

    // com.datong.dict.data.dictionary.en.source.DictCnDataSource
    override fun getWord(keywords: String, callback: GetWordCallback<DictCnWord>) {
        if (cachedWord.containsKey(keywords)) {
            callback.onLoad(cachedWord[keywords])
        } else {
            getFromLocalDataSource(keywords, callback)
        }
    }

    /* JADX INFO: Access modifiers changed from: private */ //    public void getFromRemoteDataSource(final String keywords, final GetWordCallback<DictCnWord> callback) {
    //        if (!NetworkUtil.isUsable()) {
    //            callback.onError();
    //        } else {
    //            this.remoteDataSource.getWord(keywords, new GetWordCallback<DictCnWord>() { // from class: com.datong.dict.data.dictionary.en.DictCnRepository.1
    //                @Override // com.datong.dict.base.callback.GetWordCallback
    //                public void onLoad(DictCnWord word) {
    //                    DictCnRepository.this.refreshCache(keywords, word);
    //                    callback.onLoad(word);
    //                }
    //
    //                @Override // com.datong.dict.base.callback.GetWordCallback
    //                public void onError() {
    //                    callback.onError();
    //                }
    //            });
    //        }
    //    }
    private fun getFromLocalDataSource(keywords: String, callback: GetWordCallback<DictCnWord>) {
        localDataSource.getWord(keywords, object : GetWordCallback<DictCnWord> {
            // from class: com.datong.dict.data.dictionary.en.DictCnRepository.2
            // com.datong.dict.base.callback.GetWordCallback
            override fun onLoad(word: DictCnWord?) {
                this@DictCnRepository.refreshCache(keywords, word)
                callback.onLoad(word)
            }

            // com.datong.dict.base.callback.GetWordCallback
            override fun onError() {
                callback.onError()
                //                DictCnRepository.this.getFromRemoteDataSource(keywords, callback);
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: private */
    fun refreshCache(key: String, word: DictCnWord?) {
        if (word != null) {
            cachedWord[key] = word
        }
    }

    companion object {
        private var INSTANCE: DictCnRepository? = null
        fun getInstance(context: Context): DictCnRepository? {
            if (INSTANCE == null) {
                INSTANCE = DictCnRepository(context)
            }
            return INSTANCE
        }
    }
}
