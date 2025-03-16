package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord;

/* loaded from: classes.dex */
public interface DatongEnDateSource {

    /* loaded from: classes.dex */
    public interface GetWordCallback {
        void onError();

        void onLoad(DatongEnWord word);
    }

    void getWord(String keywords, GetWordCallback callback);
}
