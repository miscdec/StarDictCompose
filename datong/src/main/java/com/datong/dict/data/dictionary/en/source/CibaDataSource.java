package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.data.dictionary.en.local.entity.CibaWord;

/* loaded from: classes.dex */
public interface CibaDataSource {

    /* loaded from: classes.dex */
    public interface GetWordCallback {
        void onError();

        void onLoad(CibaWord word);
    }

    void getWord(String keywords, GetWordCallback callback);
}
