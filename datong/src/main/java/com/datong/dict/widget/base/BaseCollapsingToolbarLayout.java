package com.datong.dict.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.datong.dict.base.BaseActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

/* loaded from: classes.dex */
public class BaseCollapsingToolbarLayout extends CollapsingToolbarLayout {
    public BaseCollapsingToolbarLayout(Context context) {
        super(context);
    }

    public BaseCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        initMinHeight();
    }

    private void initMinHeight() {
        // from class: com.datong.dict.widget.base.BaseCollapsingToolbarLayout$$ExternalSyntheticLambda0
// java.lang.Runnable
        post(() -> {
            try {
                Toolbar toolbar = ((BaseActivity) getContext()).toolbar;
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    if (childAt instanceof TabLayout) {
                        setMinimumHeight(toolbar.getHeight() + childAt.getHeight());
                        return;
                    }
                }
            } catch (Exception unused) {
            }
        });
    }

    /* synthetic */
}
