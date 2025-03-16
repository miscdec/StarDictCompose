package com.datong.dict.data.dictionary.jp.source;

import com.datong.dict.data.dictionary.jp.local.entity.datong.Index;
import com.datong.dict.data.dictionary.jp.local.entity.datong.DatongJapWord;

import java.util.List;

/* loaded from: classes.dex */
public interface DatongJapDateSource {

    /* loaded from: classes.dex */
    public interface GetWordCallback {
        void error();

        void onload(DatongJapWord word);
    }

    /* loaded from: classes.dex */
    public interface GetWordIndexCallback {
        void error();

        void onload(List<Index> index);
    }

    void getWord(String wordId, GetWordCallback callback);

    void getWordIndex(String word, GetWordIndexCallback callback);
}
