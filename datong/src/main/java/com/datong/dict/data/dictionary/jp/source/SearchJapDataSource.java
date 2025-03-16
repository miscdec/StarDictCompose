package com.datong.dict.data.dictionary.jp.source;

import com.datong.dict.data.dictionary.jp.local.entity.datong.Index;

import java.util.List;

/* loaded from: classes.dex */
public interface SearchJapDataSource {

    /* loaded from: classes.dex */
    public interface SearchCallback {
        void error();

        void onload(List<Index> indexes);
    }

    void search(String keywords, SearchCallback callback);
}
