package com.datong.dict.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import androidx.core.widget.NestedScrollView;
import com.datong.dict.base.BaseActivity;
import com.datong.dict.module.dict.DictActivity;
import com.datong.dict.widget.MessageSnackbar;
import com.datong.dict.widget.WordSnackbar;

/* loaded from: classes.dex */
public class BaseScrollView extends NestedScrollView {
    public BaseScrollView(Context context) {
        super(context);
        init();
    }

    public BaseScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOverScrollMode(2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.core.widget.NestedScrollView, android.view.View
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        setDictSelectorVisibility(t < oldt);
    }

    private void setDictSelectorVisibility(boolean visibility) {
        BaseActivity baseActivity = (BaseActivity) getContext();
        if (baseActivity instanceof DictActivity) {
            DictActivity dictActivity = (DictActivity) baseActivity;
            WordSnackbar.dismiss();
            MessageSnackbar.dismiss();
            if (visibility) {
                dictActivity.showDictSelector();
            } else {
                dictActivity.hideDictSelector();
            }
        }
    }
}
