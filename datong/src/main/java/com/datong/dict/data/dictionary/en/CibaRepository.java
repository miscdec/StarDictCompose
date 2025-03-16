package com.datong.dict.data.dictionary.en;

import android.content.Context;

import com.datong.dict.data.dictionary.en.local.CibaLocalDataSource;
import com.datong.dict.data.dictionary.en.local.entity.CibaWord;
import com.datong.dict.data.dictionary.en.source.CibaDataSource;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CibaRepository implements CibaDataSource {
    private static CibaRepository INSTANCE;
    private CibaLocalDataSource localDataSource;
    private Map<String, CibaWord> cachedWord = new HashMap();
//    private CibaRemoteDataSource remoteDataSource = CibaRemoteDataSource.getInstance();

    public static CibaRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CibaRepository(context);
        }
        return INSTANCE;
    }

    private CibaRepository(Context context) {
        this.localDataSource = CibaLocalDataSource.getInstance(context);
    }

    @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource
    public void getWord(String keywords, CibaDataSource.GetWordCallback callback) {
        if (this.cachedWord.containsKey(keywords)) {
            callback.onLoad(this.cachedWord.get(keywords));
        } else {
            getWordFromLocalDataSourde(keywords, callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
//    public void getWordFromRemoteDataSourde(final String keywords, final CibaDataSource.GetWordCallback callback) {
//        this.remoteDataSource.getWord(keywords, new CibaDataSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.en.CibaRepository.1
//            @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource.GetWordCallback
//            public void onLoad(CibaWord word) {
//                CibaRepository.this.refreshCache(keywords, word);
//                callback.onLoad(word);
//            }
//
//            @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource.GetWordCallback
//            public void onError() {
//                callback.onError();
//            }
//        });
//    }

    private void getWordFromLocalDataSourde(final String keywords, final CibaDataSource.GetWordCallback callback) {
        this.localDataSource.getWord(keywords, new CibaDataSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.en.CibaRepository.2
            @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource.GetWordCallback
            public void onLoad(CibaWord word) {
                CibaRepository.this.cachedWord.put(keywords, word);
                callback.onLoad(word);
            }

            @Override // com.datong.dict.data.dictionary.en.source.CibaDataSource.GetWordCallback
            public void onError() {
                callback.onError();
//                CibaRepository.this.getWordFromRemoteDataSourde(keywords, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCache(String keywords, CibaWord word) {
        if (word != null) {
            this.cachedWord.put(keywords, word);
        }
    }
}
