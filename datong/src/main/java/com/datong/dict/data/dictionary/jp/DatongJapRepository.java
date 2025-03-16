package com.datong.dict.data.dictionary.jp;

import android.content.Context;

import com.datong.dict.data.dictionary.jp.local.DatongJapLocalDateSource;
import com.datong.dict.data.dictionary.jp.local.entity.datong.DatongJapWord;
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index;
import com.datong.dict.data.dictionary.jp.source.DatongJapDateSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class DatongJapRepository implements DatongJapDateSource {
    private static DatongJapRepository INSTANCE;
    private DatongJapLocalDateSource localDateSoucre;
//    private DatongJapRemoteDateSource remoteDateSoucre;
    private Map<String, DatongJapWord> wordCache = new HashMap<>();
    private Map<String, List<Index>> indexCache = new HashMap<>();

    private DatongJapRepository(Context context) {
//        this.remoteDateSoucre = DatongJapRemoteDateSource.getInstance(context);
        this.localDateSoucre = DatongJapLocalDateSource.getInstance(context);
    }

    public static DatongJapRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatongJapRepository(context);
        }
        return INSTANCE;
    }

    @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
    public void getWord(String wordId, DatongJapDateSource.GetWordCallback callback) {
        if (this.wordCache.containsKey(wordId)) {
            callback.onload(this.wordCache.get(wordId));
        } else {
            getWordFromLocalDateSource(wordId, callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
//    public void getWordFromRemoteDateSource(final String wordId, final DatongJapDateSource.GetWordCallback callback) {
//        this.remoteDateSoucre.getWord(wordId, new DatongJapDateSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.jp.DatongJapRepository.1
//            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
//            public void onload(DatongJapWord word) {
//                callback.onload(word);
//                DatongJapRepository.this.refreshWordCache(wordId, word);
//            }
//
//            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
//            public void error() {
//                callback.error();
//            }
//        });
//    }

    private void getWordFromLocalDateSource(final String wordId, final DatongJapDateSource.GetWordCallback callback) {
        this.localDateSoucre.getWord(wordId, new DatongJapDateSource.GetWordCallback() { // from class: com.datong.dict.data.dictionary.jp.DatongJapRepository.2
            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
            public void onload(DatongJapWord word) {
                callback.onload(word);
                DatongJapRepository.this.refreshWordCache(wordId, word);
            }

            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
            public void error() {
                callback.error();
//                DatongJapRepository.this.getWordFromRemoteDateSource(wordId, callback);
            }
        });
    }

    @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
    public void getWordIndex(String word, DatongJapDateSource.GetWordIndexCallback callback) {
        if (this.indexCache.containsKey(word)) {
            callback.onload(this.indexCache.get(word));
        } else {
            getWordIndexFromLocalDateSource(word, callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
//    public void getWordIndexFromRemoteDateSource(final String word, final DatongJapDateSource.GetWordIndexCallback callback) {
//        this.remoteDateSoucre.getWordIndex(word, new DatongJapDateSource.GetWordIndexCallback() { // from class: com.datong.dict.data.dictionary.jp.DatongJapRepository.3
//            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordIndexCallback
//            public void onload(List<Index> indexes) {
//                callback.onload(indexes);
//                DatongJapRepository.this.refreshWordIndexCache(word, indexes);
//            }
//
//            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordIndexCallback
//            public void error() {
//                callback.error();
//            }
//        });
//    }

    private void getWordIndexFromLocalDateSource(final String word, final DatongJapDateSource.GetWordIndexCallback callback) {
        this.localDateSoucre.getWordIndex(word, new DatongJapDateSource.GetWordIndexCallback() { // from class: com.datong.dict.data.dictionary.jp.DatongJapRepository.4
            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordIndexCallback
            public void onload(List<Index> indexes) {
                callback.onload(indexes);
                DatongJapRepository.this.refreshWordIndexCache(word, indexes);
            }

            @Override // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordIndexCallback
            public void error() {
                callback.error();
//                DatongJapRepository.this.getWordIndexFromRemoteDateSource(word, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWordCache(String wordId, DatongJapWord word) {
        if (word == null || this.wordCache.containsKey(wordId)) {
            return;
        }
        this.wordCache.put(wordId, word);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWordIndexCache(String word, List<Index> indexes) {
        if (word == null || this.indexCache.containsKey(word)) {
            return;
        }
        this.indexCache.put(word, indexes);
    }
}
