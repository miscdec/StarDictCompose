package com.datong.dict.data.crawler.dict.en;

import androidx.room.FtsOptions;
import java.util.HashMap;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* loaded from: classes.dex */
public class HJDict {
    private static final String URL = "https://dict.hjenglish.com/w/";
    private Element dictElement;
    private Element expENElement;
    private Element phraseElement;
    private Document root;
    private Element synAndAntElement;
    private Element wordInfoElement;

    public HJDict(String word) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("Cookie", "HJ_UID=6667f2d3-dd5f-9efd-d93f-3a64cad17a91; HJ_CMATCH=1; TRACKSITEMAP=3%2C88%2C; HJ_SID=4cad5d61-0bf9-a07c-3cfe-a4fdc2894b32; HJ_SSID_3=ba2f9a9a-0c26-e580-5e75-66d6401aea62; HJ_CSST_3=0; HJ_CST=0; _REF=https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DVEqazrYBxasKH3TtgSxa5Su8pEgoXK2P7Ctxdy9wl1c4xpOKa2wHjB4Qv03ps78A%26ck%3D5691.4.112.242.249.346.718.592%26shh%3Dwww.baidu.com%26sht%3Dbaidu%26wd%3D%26eqid%3Ddc3460e00003d562000000055badd866; _SREF_3=https%253A%252F%252Fwww.baidu.com%252Flink%253Furl%253DVEqazrYBxasKH3TtgSxa5Su8pEgoXK2P7Ctxdy9wl1c4xpOKa2wHjB4Qv03ps78A%2526ck%253D5691.4.112.242.249.346.718.592%2526shh%253Dwww.baidu.com%2526sht%253Dbaidu%2526wd%253D%2526eqid%253Ddc3460e00003d562000000055badd866");
            hashMap.put("Host", "dict.hjenglish.com");
            hashMap.put("If-None-Match", "bb42-ebZy6bzrlKQusrDjKjhrj4D5jrw");
            hashMap.put("Referer", "https://dict.hjenglish.com/");
            hashMap.put("Upgrade-Insecure-Requests", "1");
            hashMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36");
            this.root = Jsoup.connect(URL + word).ignoreHttpErrors(true).ignoreContentType(true).headers(hashMap).get();
            initWordInfoElement();
            initDictElement();
            initPhraseElement();
            initExpEN();
            initSynAndAntElement();
        } catch (Exception unused) {
        }
    }

    private void initWordInfoElement() {
        try {
            this.wordInfoElement = this.root.getElementsByClass("word-details-pane-header").first();
        } catch (Exception unused) {
        }
    }

    private void initDictElement() {
        try {
            this.dictElement = this.root.getElementsByClass("word-details-item detail").first();
        } catch (Exception unused) {
        }
    }

    private void initPhraseElement() {
        try {
            this.phraseElement = this.root.getElementsByClass("phrase-items").first();
        } catch (Exception unused) {
        }
    }

    private void initExpEN() {
        try {
            this.expENElement = this.root.getElementsByClass("enen-groups").first();
        } catch (Exception unused) {
        }
    }

    private void initSynAndAntElement() {
        try {
            this.synAndAntElement = this.root.getElementsByClass("word-details-item-content synant-content").first();
        } catch (Exception unused) {
        }
    }

    public String getWord() {
        try {
            return this.wordInfoElement.getElementsByClass("word-text").text();
        } catch (Exception unused) {
            return "";
        }
    }

    public String getPhoneticUk() {
        String str = "";
        try {
            str = this.wordInfoElement.getElementsByClass("pronounce-value-en").text();
            return str.replace("[", "/").replace("]", "/");
        } catch (Exception unused) {
            return str;
        }
    }

    public String getPhoneticUs() {
        String str = "";
        try {
            str = this.wordInfoElement.getElementsByClass("pronounce-value-us").text();
            return str.replace("[", "/").replace("]", "/");
        } catch (Exception unused) {
            return str;
        }
    }

    public String getPhoneticUkUrl() {
        try {
            return this.wordInfoElement.getElementsByClass("pronounce-value-en").first().nextElementSibling().attr("data-src");
        } catch (Exception unused) {
            return "";
        }
    }

    public String getPhoneticUsUrl() {
        try {
            return this.wordInfoElement.getElementsByClass("pronounce-value-us").first().nextElementSibling().attr("data-src");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getBaseExplain() {
        String str;
        Exception e;
        try {
            Elements elementsByTag = this.wordInfoElement.getElementsByClass(FtsOptions.TOKENIZER_SIMPLE).first().getElementsByTag("p");
            int i = 0;
            str = "";
            while (i < elementsByTag.size()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(elementsByTag.get(i).text());
                    sb.append(i == elementsByTag.size() + (-1) ? "" : "\n");
                    str = sb.toString();
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

    public String getDictExplain() {
        String str;
        String str2;
        String str3 = "detail-pron";
        String str4 = "";
        try {
            String str5 = "<testTag>" + this.dictElement.getElementsByClass("detail-tags-en clearfix").text() + "</testTag>";
            Iterator<Element> it = this.dictElement.getElementsByTag("dl").iterator();
            String str6 = "";
            while (it.hasNext()) {
                Element next = it.next();
                String str7 = "<wordClass>" + next.getElementsByTag("dt").first().ownText() + "</wordClass>";
                if (next.getElementsByClass(str3).first() != null) {
                    str2 = "<phonetic>" + next.getElementsByClass(str3).first().text() + "</phonetic>";
                } else {
                    str2 = str4;
                }
                Elements elementsByTag = next.getElementsByTag("dd");
                int i = 0;
                String str8 = str4;
                while (i < elementsByTag.size()) {
                    Element element = elementsByTag.get(i);
                    StringBuilder sb = new StringBuilder();
                    sb.append("<index>");
                    i++;
                    sb.append(i);
                    sb.append("</index>");
                    String sb2 = sb.toString();
                    String replace = ("<explain>" + element.getElementsByTag("h3").text() + "</explain>").replace("（", "(").replace("）", ")");
                    Iterator<Element> it2 = element.getElementsByTag("li").iterator();
                    String str9 = str4;
                    while (it2.hasNext()) {
                        Element next2 = it2.next();
                        String str10 = str3;
                        String str11 = "<en>" + next2.getElementsByClass("def-sentence-from").text() + "</en>";
                        StringBuilder sb3 = new StringBuilder();
                        str = str4;
                        try {
                            sb3.append("<cn>");
                            sb3.append(next2.getElementsByClass("def-sentence-to").text());
                            sb3.append("</cn>");
                            String sb4 = sb3.toString();
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("<soundUrl>");
                            sb5.append(next2.getElementsByClass("word-audio").first().attr("data-src"));
                            sb5.append("</soundUrl>");
                            str9 = str9 + "<sentenceItem>" + str11 + sb4 + sb5.toString() + "</sentenceItem>";
                            str3 = str10;
                            str4 = str;
                            it = it;
                        } catch (Exception e) {
                            e = e;
                            e.printStackTrace();
                            return str;
                        }
                    }
                    str8 = str8 + "<explainItem>" + sb2 + replace + str9 + "</explainItem>";
                    str3 = str3;
                    str4 = str4;
                    it = it;
                }
                str6 = str6 + "<item>" + str7 + str2 + str8 + "</item>";
            }
            return "<dictExplain>" + str5 + str6 + "</dictExplain>";
        } catch (Exception e2) {
            str = str4;
        }
        return str;
    }

    public String getPhrase() {
        try {
            Elements elementsByTag = this.phraseElement.getElementsByTag("li");
            int i = 0;
            String str = "";
            while (i < elementsByTag.size()) {
                Element element = elementsByTag.get(i);
                StringBuilder sb = new StringBuilder();
                sb.append("<index>");
                i++;
                sb.append(i);
                sb.append("</index>");
                str = str + "<item>" + sb.toString() + ("<en>" + element.children().first().text() + "</en>") + ("<cn>" + element.children().last().text() + "</cn>") + "</item>";
            }
            return "<phrase>" + str + "</phrase>";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getExpEN() {
        try {
            Iterator<Element> it2 = this.expENElement.getElementsByTag("dl").iterator();
            String str = "";
            while (it2.hasNext()) {
                String str2 = "<wordClass>" + it2.next().getElementsByTag("dt").first().text() + "</wordClass>";
                String str3 = "";
                while (it2.next().getElementsByTag("dd").iterator().hasNext()) {
                    str3 = str3 + "<explain>" + it2.next().text() + "</explain>";
                }
                str = str + "<item>" + str2 + str3 + "</item>";
            }
            return "<expEN>" + str + "</expEN>";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getSynAndAnt() {
        String str;
        String str2;
        try {
            Element first = this.synAndAntElement.getElementsByClass("syn").first();
            int i = 0;
            if (first != null) {
                Elements elementsByTag = first.getElementsByTag("td");
                String str3 = "";
                int i2 = 0;
                while (i2 < elementsByTag.size()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str3);
                    sb.append(elementsByTag.get(i2).text());
                    sb.append(i2 == elementsByTag.size() + (-1) ? "" : ",");
                    str3 = sb.toString();
                    i2++;
                }
                str = "<syn>" + str3 + "</syn>";
            } else {
                str = "";
            }
            Element first2 = this.synAndAntElement.getElementsByClass("ant").first();
            if (first2 != null) {
                Elements elementsByTag2 = first2.getElementsByTag("td");
                String str4 = "";
                while (i < elementsByTag2.size()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str4);
                    sb2.append(elementsByTag2.get(i).text());
                    sb2.append(i == elementsByTag2.size() + (-1) ? "" : ",");
                    str4 = sb2.toString();
                    i++;
                }
                str2 = "<ant>" + str4 + "</ant>";
            } else {
                str2 = "";
            }
            return "<synAndAnt>" + str + str2 + "</synAndAnt>";
        } catch (Exception unused) {
            return "";
        }
    }
}
