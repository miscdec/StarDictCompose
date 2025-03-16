package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.utils.rvHelper.BaseItem;
import java.util.List;

/* loaded from: classes.dex */
public interface SearchEnDataSource {

    /* loaded from: classes.dex */
    public interface SearchResultCallback {
        void onError();

        void onLoad(List<BaseItem> resultList);
    }

    void getSearchResult(String keywords, SearchResultCallback callback);
}
