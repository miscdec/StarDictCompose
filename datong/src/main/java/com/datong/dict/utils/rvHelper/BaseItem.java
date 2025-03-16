package com.datong.dict.utils.rvHelper;

/* loaded from: classes.dex */
public abstract class BaseItem {
    private int layoutId;
    private int viewType;

    public int getLayoutId() {
        return this.layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getViewType() {
        return this.viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
