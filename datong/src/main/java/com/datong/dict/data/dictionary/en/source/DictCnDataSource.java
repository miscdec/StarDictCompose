package com.datong.dict.data.dictionary.en.source;

import com.datong.dict.base.callback.GetWordCallback;
import com.datong.dict.data.dictionary.en.local.entity.DictCnWord;

/* loaded from: classes.dex */
public interface DictCnDataSource {
    void getWord(String keywords, GetWordCallback<DictCnWord> callback);
}
