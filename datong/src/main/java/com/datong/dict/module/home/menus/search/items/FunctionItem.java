package com.datong.dict.module.home.menus.search.items;

import com.datong.dict.utils.rvHelper.BaseItem;

/* loaded from: classes.dex */
public class FunctionItem extends BaseItem {
    public static final String ITEM_BOOK = "单词本";
    public static final String ITEM_NEW_VERSION = "新版本内测";
    public static final String ITEM_NEXT = "Next";
    public static final String ITEM_TRANSLATE = "翻译";
    public static final String ITEM_WIKI = "维基百科";
    private int imgResId;
    private String title = "";

    public int getImgResId() {
        return this.imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
