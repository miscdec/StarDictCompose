package com.datong.dict.data.dictionary.jp;

import android.content.Context;

import com.datong.dict.data.dictionary.jp.local.SearchJapLocalDateSource;
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index;
import com.datong.dict.data.dictionary.jp.source.SearchJapDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class SearchJapRepository implements SearchJapDataSource {
    private static SearchJapRepository INSTANCE;
    private Map<String, List<Index>> cachedIndexes = new HashMap();
    private String keywords;
    private final SearchJapLocalDateSource localDateSource;
//    private final SearchJapRemoteDateSource remoteDateSource;

    public static SearchJapRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SearchJapRepository(context);
        }
        return INSTANCE;
    }

    private SearchJapRepository(Context context) {
//        this.remoteDateSource = SearchJapRemoteDateSource.getInstance(context);
        this.localDateSource = SearchJapLocalDateSource.getInstance(context);
    }

    @Override // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource
    public void search(String keywords, SearchJapDataSource.SearchCallback callback) {
        String replace = keywords.replace("%", "").replace("_", "").replace("＊", "*").replace("？", "?");
        this.keywords = replace;
        if (replace.replace(" ", "").replace("*", "").replace("?", "").equals("")) {
            callback.error();
        } else if (this.cachedIndexes.containsKey(replace)) {
            callback.onload(this.cachedIndexes.get(replace));
        }
//        else if (SettingUtil.isCloudSearch()) {
//            searchFromRemote(replace, callback);
//        } else {
            searchFromLocal(replace, callback);
//        }
    }

    /* JADX INFO: Access modifiers changed from: private */
//    public void searchFromRemote(final String keywords, final SearchJapDataSource.SearchCallback callback) {
//        if (!NetworkUtil.isUsable()) {
//            if (SettingUtil.isCloudSearch()) {
//                searchFromLocal(keywords, callback);
//                return;
//            } else {
//                callback.error();
//                return;
//            }
//        }
//        this.remoteDateSource.search(keywords, new SearchJapDataSource.SearchCallback() { // from class: com.datong.dict.data.dictionary.jp.SearchJapRepository.1
//            @Override // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource.SearchCallback
//            public void onload(List<Index> indexes) {
//                if (indexes.isEmpty()) {
//                    SearchJapRepository.this.searchFromLocal(keywords, callback);
//                    return;
//                }
//                SearchJapRepository.this.refreshCache(keywords, indexes);
//                callback.onload(indexes);
//            }
//
//            @Override // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource.SearchCallback
//            public void error() {
//                if (SettingUtil.isCloudSearch()) {
//                    SearchJapRepository.this.searchFromLocal(keywords, callback);
//                } else {
//                    callback.error();
//                }
//            }
//        });
//    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchFromLocal(final String keywords, final SearchJapDataSource.SearchCallback callback) {
        this.localDateSource.search(keywords, new SearchJapDataSource.SearchCallback() { // from class: com.datong.dict.data.dictionary.jp.SearchJapRepository.2
            @Override // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource.SearchCallback
            public void onload(List<Index> indexes) {
                SearchJapRepository.this.refreshCache(keywords, indexes);
                callback.onload(indexes);
            }

            @Override // com.datong.dict.data.dictionary.jp.source.SearchJapDataSource.SearchCallback
            public void error() {
//                if (!SettingUtil.isCloudSearch()) {
//                    SearchJapRepository.this.searchFromRemote(keywords, callback);
//                } else {
                    callback.error();
//                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCache(String keywords, List<Index> indexes) {
        if (indexes.isEmpty() || this.cachedIndexes.containsKey(keywords)) {
            return;
        }
        this.cachedIndexes.put(keywords, indexes);
    }
}
