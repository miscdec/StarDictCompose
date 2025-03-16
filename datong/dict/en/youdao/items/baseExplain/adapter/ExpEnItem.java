package com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter;

/* loaded from: classes.dex */
public class ExpEnItem {
    String explain;
    String index;
    String sentence;
    String synonyms;
    String wordClass;

    public String getWordClass() {
        return this.wordClass;
    }

    public void setWordClass(String wordClass) {
        this.wordClass = wordClass;
    }

    public String getIndex() {
        return this.index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getExplain() {
        return this.explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getSentence() {
        return this.sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSynonyms() {
        return this.synonyms.replace(",", " / ");
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }
}
