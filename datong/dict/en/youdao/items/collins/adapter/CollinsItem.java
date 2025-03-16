package com.datong.dict.module.dict.en.youdao.items.collins.adapter;

import java.util.List;

/* loaded from: classes.dex */
public class CollinsItem {
    String exam;
    List<CollinsExplainItem> explainItemList;
    String phonetic;
    String shape;
    String starCount;
    String title;
    String word;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWord() {
        return this.word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPhonetic() {
        return this.phonetic;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public String getStarCount() {
        return this.starCount;
    }

    public void setStarCount(String starCount) {
        String str = "";
        try {
            for (int i = 0; i < Integer.valueOf(starCount).intValue(); i++) {
                str = str + "★";
            }
        } catch (Exception unused) {
        }
        this.starCount = str;
    }

    public String getExam() {
        return this.exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getShape() {
        return this.shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public List<CollinsExplainItem> getExplainItemList() {
        return this.explainItemList;
    }

    public void setExplainItemList(List<CollinsExplainItem> explainItemList) {
        this.explainItemList = explainItemList;
    }
}
