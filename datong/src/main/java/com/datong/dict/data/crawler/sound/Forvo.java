package com.datong.dict.data.crawler.sound;

import com.datong.dict.module.dict.en.datong.pages.globalPron.adapter.GlobalPronItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* loaded from: classes.dex */
public class Forvo {
    public static final String URL = "https://dict.eudic.net/dicts/en/";
    Document document;

    public Forvo(String word) {
        try {
            this.document = Jsoup.connect(URL + word).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GlobalPronItem> getGlobalPron() {
        ArrayList arrayList = new ArrayList();
        try {
            Iterator<Element> it = this.document.getElementsByClass("gv_details").first().getElementsByClass("gv_item").iterator();
            while (it.hasNext()) {
                Element next = it.next();
                String attr = next.getElementsByTag("a").first().attr("data-rel");
                String text = next.getElementsByClass("gv_person").first().text();
                String text2 = next.getElementsByClass("gv_contury").first().text();
                GlobalPronItem globalPronItem = new GlobalPronItem();
                globalPronItem.setUrl(attr);
                globalPronItem.setGender(text);
                globalPronItem.setCountry(text2);
                arrayList.add(globalPronItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
