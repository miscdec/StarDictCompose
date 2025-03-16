package com.datong.dict.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/* loaded from: classes.dex */
public class BaseWebview extends WebView {
    private OnScrollCallback scrollCallback;

    /* loaded from: classes.dex */
    public interface OnScrollCallback {
        void onScroll(int l, int t, int oldl, int oldt);
    }

    public BaseWebview(Context context) {
        super(context);
        init();
    }

    public BaseWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOverScrollMode(2);
    }

    public void setScrollCallback(OnScrollCallback scrollCallback) {
        this.scrollCallback = scrollCallback;
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        OnScrollCallback onScrollCallback = this.scrollCallback;
        if (onScrollCallback != null) {
            onScrollCallback.onScroll(l, t, oldl, oldt);
        }
    }
}
