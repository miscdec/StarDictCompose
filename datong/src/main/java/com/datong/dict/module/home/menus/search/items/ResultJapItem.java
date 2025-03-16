package com.datong.dict.module.home.menus.search.items;


import com.datong.dict.data.dictionary.jp.local.entity.datong.Index;
import com.datong.dict.utils.rvHelper.BaseItem;

/* loaded from: classes.dex */
public class ResultJapItem extends BaseItem {
    public static final int VIEW_TYPE = 2131493058;
    private Index index;

    public ResultJapItem(Index index) {
        this.index = index;
//        setViewType(R.layout.item_list_search_result_jp);
    }

    public Index getIndex() {
        return this.index;
    }

    public void setIndex(Index index) {
        this.index = index;
    }
}
