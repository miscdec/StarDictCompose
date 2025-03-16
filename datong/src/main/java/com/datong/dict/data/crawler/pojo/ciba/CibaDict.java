package com.datong.dict.data.crawler.pojo.ciba;

import com.datong.dict.data.crawler.pojo.ciba.analysis.Analysis;
import com.datong.dict.data.crawler.pojo.ciba.antonym.Antonym;
import com.datong.dict.data.crawler.pojo.ciba.authority.Authority;
import com.datong.dict.data.crawler.pojo.ciba.baseInfo.BaseInfo;
import com.datong.dict.data.crawler.pojo.ciba.bilingual.Bilingual;
import com.datong.dict.data.crawler.pojo.ciba.cet.CET;
import com.datong.dict.data.crawler.pojo.ciba.collins.Collins;
import com.datong.dict.data.crawler.pojo.ciba.expEN.ExpEn;
import com.datong.dict.data.crawler.pojo.ciba.expEN2CN.ExpEN2CN;
import com.datong.dict.data.crawler.pojo.ciba.phrase.Phrase;
import com.datong.dict.data.crawler.pojo.ciba.synonym.Synonym;
import com.datong.dict.data.crawler.pojo.ciba.wordRoot.WordRoot;
import java.util.List;

/* loaded from: classes.dex */
public class CibaDict {
    public List<Antonym> antonyms;
    public List<Authority> authorities;
    public BaseInfo baesInfo;
    public ExpEN2CN bidec;
    public List<Bilingual> bilinguals;
    public CET cetFour;
    public CET cetSix;
    public List<Collins> collins;
    public List<ExpEn> ee_mean;
    public List<Phrase> phrases;
    public List<Analysis> sameAnalysis;
    public List<Synonym> synonyms;
    public List<WordRoot> wordRoots;
}
