package com.datong.dict.module.home.menus.search.items;


import com.acmpo6ou.stardict.R;
import com.datong.dict.utils.rvHelper.BaseItem;

/* loaded from: classes.dex */
public class ResultCn2EnItem extends BaseItem {
    public static final int VIEW_TYPE = 2131493056;
    private String explain;
    private String word;
    private String[] wordCollects;

//    public ResultCn2EnItem() {
//        setViewType(R.layout.item_list_search_result_cn_en);
//    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String[] getWordCollects() {
        return this.wordCollects;
    }

    public void setWordCollects(String[] wordCollects) {
        this.wordCollects = wordCollects;
    }
}
