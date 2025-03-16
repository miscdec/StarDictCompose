package com.datong.dict.base.callback;

import android.text.Editable;
import android.text.TextWatcher;

/* loaded from: classes.dex */
public abstract class OnTextChangedCallback implements TextWatcher {
    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
