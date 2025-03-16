package com.datong.dict.module.home.menus.search.items;


import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord;
import com.datong.dict.utils.NotNullUtil;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.utils.rvHelper.BaseItem;
//import com.parse.ParseObject;

/* loaded from: classes.dex */
public class ResultEn2EnItem extends BaseItem {
//    public static final int VIEW_TYPE = 2131493057;
    private String explain;
    private String phonetic;
    private String word;

    public ResultEn2EnItem(DatongEnWord word) {
        this.word = word.word;
        this.explain = word.expCN;
        this.phonetic = SettingUtil.getPhoneticType() == 0 ? word.phoneticUs : word.phoneticUk;
//        setViewType(R.layout.item_list_search_result_en_en);
    }

//    public ResultEn2EnItem(ParseObject object) {
//        this.word = NotNullUtil.getString(object.getString("word"));
//        this.explain = NotNullUtil.getString(object.getString("expCN"));
//        this.phonetic = NotNullUtil.getString(object.getString(SettingUtil.getPhoneticType() == 0 ? "phoneticUs" : "phoneticUk"));
//        setViewType(R.layout.item_list_search_result_en_en);
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

    public String getPhonetic() {
        return this.phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }
}
