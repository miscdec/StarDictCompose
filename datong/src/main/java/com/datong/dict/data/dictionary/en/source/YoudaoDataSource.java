package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord;

/* loaded from: classes.dex */
public interface YoudaoDataSource {

    /* loaded from: classes.dex */
    public interface GetWordCallback {
        void onError();

        void onLoad(YoudaoWord word);
    }

    void getWord(String keywords, GetWordCallback callback);
}
