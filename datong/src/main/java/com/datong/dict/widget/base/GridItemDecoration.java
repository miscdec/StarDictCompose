package com.datong.dict.widget.base;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int col;
    private int space;

    public GridItemDecoration(int col, int space) {
        this.col = col;
        this.space = space;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = this.space;
        if (parent.getChildAdapterPosition(view) % this.col == 0) {
            outRect.left = this.space;
        } else {
            outRect.left = this.space / 2;
        }
        if ((parent.getChildAdapterPosition(view) + 1) % this.col == 0) {
            outRect.right = this.space;
        } else {
            outRect.right = this.space / 2;
        }
    }
}
