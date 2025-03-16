package com.datong.dict.base.callback;

/* loaded from: classes.dex */
public interface GetWordCallback<T> {
    void onError();

    void onLoad(T word);
}
