package com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter;

import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain.The21stDictExplainItem;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.phrase.The21stDictPhraseItem;
import java.util.List;

/* loaded from: classes.dex */
public class The21DictItem {
    List<The21stDictExplainItem> explainItemList;
    String explainTitle;
    List<The21stDictPhraseItem> phraseItemList;
    String phraseTitle;

    public String getExplainTitle() {
        return this.explainTitle;
    }

    public void setExplainTitle(String explainTitle) {
        this.explainTitle = explainTitle;
    }

    public List<The21stDictExplainItem> getExplainItemList() {
        return this.explainItemList;
    }

    public void setExplainItemList(List<The21stDictExplainItem> explainItemList) {
        this.explainItemList = explainItemList;
    }

    public String getPhraseTitle() {
        return this.phraseTitle;
    }

    public void setPhraseTitle(String phraseTitle) {
        this.phraseTitle = phraseTitle;
    }

    public List<The21stDictPhraseItem> getPhraseItemList() {
        return this.phraseItemList;
    }

    public void setPhraseItemList(List<The21stDictPhraseItem> phraseItemList) {
        this.phraseItemList = phraseItemList;
    }
}
