package com.datong.dict.data.crawler.dict.en;

import com.datong.dict.data.crawler.pojo.ciba.CibaDict;
import com.datong.dict.data.crawler.pojo.ciba.authority.Authority;
import com.datong.dict.data.crawler.pojo.ciba.baseInfo.Part;
import com.datong.dict.data.crawler.pojo.ciba.bilingual.Bilingual;
import com.datong.dict.data.dictionary.en.local.entity.CibaWord;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

/* loaded from: classes.dex */
public class Ciba {
    private static final String URL = "http://www.iciba.com/index.php";
    private CibaDict dict;
    private JsonObject jsonObject;
    private String word;

    public Ciba(String word) {
        this.word = word;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("Host", "www.iciba.com");
            hashMap.put("Referer", "http://www.iciba.com/" + word);
            hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
            hashMap.put("Cookie", "is_new_index=1; UM_distinctid=1676ffe59d2abf-0cbee8b3bab862-b79183d-1fa400-1676ffe59d3f0; __SDID=73908fd5cbf4a597; iciba_u_rand=a7492f624738fba9ff122275570fae9f%4039.137.101.28; iciba_u_rand_t=1549971373; screen-skin=screen-blue; cbdownload_time=download; CNZZDATA1257391275=248586085-1543770562-null%7C1549976974; cbdownload_num=7; search-history=wonder%2Cuniform; CNZZDATA1256556802=262055950-1543772209-http%253A%252F%252Fwww.iciba.com%252F%7C1549980507; c_word_history=uniform%2Cwonder%2CLife+itself%2C+without+the+assistance+of+colleges+and+universities%2C+is+becoming+an+advanced+institution+of+learning.");
            HashMap hashMap2 = new HashMap();
            hashMap2.put("a", "getWordMean");
            hashMap2.put("c", "search");
            hashMap2.put("word", word);
            hashMap2.put("list", "1,2,3,4,5,8,9,10,12,13,14,15,18,21,22,24,3003,3004,3005");
            hashMap2.put("_", System.currentTimeMillis() + "");
            String body = Jsoup.connect(URL).ignoreContentType(true).headers(hashMap).data(hashMap2).execute().body();
            this.dict = new Gson().fromJson(body, CibaDict.class);
            this.jsonObject = new Gson().fromJson(body, JsonObject.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CibaWord getCibaWord() {
        CibaWord cibaWord = new CibaWord();
        cibaWord.setWord(getWord());
        cibaWord.setPhoneticUs(getPhoneticUs());
        cibaWord.setPhoneticUk(getPhoneticUk());
        cibaWord.setSoundUsUrl(getSoundUsUrl());
        cibaWord.setSoundUkUrl(getSoundUkUrl());
        cibaWord.expCN = getExpCN();
        cibaWord.setShape(getShape());
        cibaWord.cet4 = getCet4();
        cibaWord.cet6 = getCet6();
        cibaWord.collins = getCollins();
        cibaWord.setWordRoot(getWordRoot());
        cibaWord.setPhrase(getPhrase());
        cibaWord.setSynonym(getSynonym());
        cibaWord.antonym = getAntonym();
        cibaWord.analysis = getAnalysis();
        cibaWord.expEn2En = getExpEn2En();
        cibaWord.expEn2Cn = getExpEn2Cn();
        cibaWord.bilingual = getBilingual();
        cibaWord.authority = getAuthority();
        return cibaWord;
    }

    public String getWord() {
        try {
            return this.dict.baesInfo.word_name;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getPhoneticUk() {
        try {
            return "/" + this.dict.baesInfo.symbols.get(0).ph_en.replace(" ", "") + "/";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getPhoneticUs() {
        try {
            return "/" + this.dict.baesInfo.symbols.get(0).ph_am.replace(" ", "") + "/";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getSoundUkUrl() {
        try {
            return this.dict.baesInfo.symbols.get(0).ph_en_mp3;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getSoundUsUrl() {
        try {
            return this.dict.baesInfo.symbols.get(0).ph_am_mp3;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getExpCN() {
        String str;
        Exception e;
        try {
            List<Part> list = this.dict.baesInfo.symbols.get(0).parts;
            str = "";
            int i = 0;
            while (i < list.size()) {
                try {
                    Part part = list.get(i);
                    String str2 = part.part;
                    String str3 = "";
                    int i2 = 0;
                    while (i2 < part.means.size()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str3);
                        sb.append(part.means.get(i2));
                        sb.append(i2 == part.means.size() + (-1) ? "" : "；");
                        str3 = sb.toString();
                        i2++;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str);
                    sb2.append(str2);
                    sb2.append(str3);
                    sb2.append(i == list.size() + (-1) ? "" : "\n");
                    str = sb2.toString();
                    i++;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return str;
                }
            }
        } catch (Exception e3) {
            str = "";
            e = e3;
        }
        return str;
    }

    public String getShape() {
        String str = "";
        ArrayList arrayList = new ArrayList();
        try {
            int i = 0;
            if (this.dict.baesInfo.exchange.word_pl.size() > 0) {
                arrayList.add("复数:" + this.dict.baesInfo.exchange.word_pl.get(0));
            }
            if (this.dict.baesInfo.exchange.word_done.size() > 0) {
                arrayList.add("过去式:" + this.dict.baesInfo.exchange.word_done.get(0));
            }
            if (this.dict.baesInfo.exchange.word_done.size() > 0) {
                arrayList.add("过去分词:" + this.dict.baesInfo.exchange.word_past.get(0));
            }
            if (this.dict.baesInfo.exchange.word_ing.size() > 0) {
                arrayList.add("现在分词:" + this.dict.baesInfo.exchange.word_ing.get(0));
            }
            if (this.dict.baesInfo.exchange.word_third.size() > 0) {
                arrayList.add("第三人称单数:" + this.dict.baesInfo.exchange.word_third.get(0));
            }
            String str2 = "";
            while (i < arrayList.size()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append((String) arrayList.get(i));
                    sb.append(i == arrayList.size() + (-1) ? "" : "  ");
                    str2 = sb.toString();
                    i++;
                } catch (Exception e) {
                    str = str2;
                    e.printStackTrace();
                    return str;
                }
            }
            return str2;
        } catch (Exception e2) {

        }
        return str;
    }

    public String getCet4() {
        try {
            String jsonElement = this.jsonObject.get("cetFour").toString();
            return "{\"cetFour\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getCet6() {
        try {
            String jsonElement = this.jsonObject.get("cetSix").toString();
            return "{\"cetSix\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getCollins() {
        try {
            String jsonElement = this.jsonObject.get("collins").toString();
            return "{\"collins\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getWordRoot() {
        try {
            String jsonElement = this.jsonObject.get("stems_affixes").toString();
            return "{\"wordRoots\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getPhrase() {
        try {
            String jsonElement = this.jsonObject.get("phrase").toString();
            return "{\"phrases\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getSynonym() {
        try {
            String jsonElement = this.jsonObject.get("synonym").toString();
            return "{\"synonyms\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getAntonym() {
        try {
            String jsonElement = this.jsonObject.get("antonym").toString();
            return "{\"antonyms\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getAnalysis() {
        try {
            String jsonElement = this.jsonObject.get("sameAnalysis").toString();
            return "{\"sameAnalysis\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getExpEn2En() {
        try {
            String jsonElement = this.jsonObject.get("ee_mean").toString();
            return "{\"ee_mean\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getExpEn2Cn() {
        try {
            String jsonElement = this.jsonObject.get("bidec").toString();
            return "{\"bidec\":" + jsonElement + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getBilingual() {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 1; i2 <= 2; i2++) {
                Iterator<Element> it = Jsoup.connect("http://dj.iciba.com/" + this.word + "-1-" + i2 + "-%01-0-0-0-0.html").get().getElementsByTag("li").iterator();
                while (it.hasNext()) {
                    Element next = it.next();
                    i++;
                    String text = next.getElementsByClass("stc_en").text();
                    String text2 = next.getElementsByClass("stc_cn").text();
                    String text3 = next.getElementsByClass("stc_ft clear").text();
                    Bilingual bilingual = new Bilingual();
                    bilingual.en = text.replace(i + ". ", "");
                    bilingual.cn = text2;
                    bilingual.from = text3;
                    arrayList.add(bilingual);
                }
            }
            String json = new Gson().toJson(arrayList);
            return "{\"bilinguals\":" + json + "}";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getAuthority() {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 1; i2 <= 2; i2++) {
                Iterator<Element> it = Jsoup.connect("http://dj.iciba.com/" + this.word + "-2-" + i2 + "-%01-0-0-0-0.html").get().getElementsByTag("li").iterator();
                while (it.hasNext()) {
                    Element next = it.next();
                    i++;
                    String text = next.getElementsByClass("stc_en").text();
                    String text2 = next.getElementsByClass("stc_ft clear").text();
                    Authority authority = new Authority();
                    authority.en = text.replace(i + ". ", "");
                    authority.from = text2;
                    arrayList.add(authority);
                }
            }
            String json = new Gson().toJson(arrayList);
            return "{\"authorities\":" + json + "}";
        } catch (Exception unused) {
            return "";
        }
    }
}
