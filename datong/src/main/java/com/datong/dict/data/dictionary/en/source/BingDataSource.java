package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.data.dictionary.en.local.entity.BingWord;

/* loaded from: classes.dex */
public interface BingDataSource {

    /* loaded from: classes.dex */
    public interface GetWordCallback {
        void onError();

        void onLoad(BingWord word);
    }

    void getWord(String keywords, GetWordCallback callback);
}
