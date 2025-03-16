package com.datong.dict.widget.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.datong.dict.module.dict.DictActivity;
import com.datong.dict.widget.MessageSnackbar;
import com.datong.dict.widget.WordSnackbar;

/* loaded from: classes.dex */
public class BaseRecyclerView extends RecyclerView {
    public BaseRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOverScrollMode(2);
    }

    public void initLinear(int orientation) {
        setLayoutManager(new LinearLayoutManager(getContext(), orientation, false));
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
    }

    public void initGrid(int col) {
        setLayoutManager(new GridLayoutManager(getContext(), col));
        setItemAnimator(new DefaultItemAnimator());
        setHasFixedSize(true);
    }

    public void setDivider(int orientation) {
        addItemDecoration(new DividerItemDecoration(getContext(), orientation));
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        setDictSelectorVisibility(dy <= 0);
    }

    private void setDictSelectorVisibility(boolean visibility) {
        try {
            Activity activity = (Activity) getContext();
            if (activity instanceof DictActivity) {
                DictActivity dictActivity = (DictActivity) activity;
                WordSnackbar.dismiss();
                MessageSnackbar.dismiss();
                if (visibility) {
                    dictActivity.showDictSelector();
                } else {
                    dictActivity.hideDictSelector();
                }
            }
        } catch (Exception unused) {
        }
    }
}
