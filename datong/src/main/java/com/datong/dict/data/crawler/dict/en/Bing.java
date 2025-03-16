package com.datong.dict.data.crawler.dict.en;

import android.util.Log;

import com.datong.dict.data.dictionary.en.local.entity.BingWord;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* loaded from: classes.dex */
public class Bing {
    private static final String URL = "http://cn.bing.com/dict/getIndex?q=";
    private Element antonymElement;
    private Element baseExpCNElement;
    private Element collocationElement;
    private Element expCNElement;
    private Element expENElement;
    private Element oxfordElement;
    private Document rootDocument;
    private Element sentenceElement;
    private Element shapeElement;
    private Element synonymElement;
    private Element wordInfoElement;

    public BingWord getBingWord() {
        BingWord bingWord = new BingWord();
        bingWord.word = getWord();
        bingWord.phoneticUs = getPhoneticUs();
        bingWord.phoneticUk = getPhoneticUk();
        bingWord.soundUkUrl = getUkUrl();
        bingWord.soundUsUrl = getUsUrl();
        bingWord.baseExpCN = getExpCN();
        bingWord.expEN = getExpEN();
        bingWord.shape = getShape();
        bingWord.expEN2CN = getExpEN2CN();
        bingWord.oxford = getOxford();
        bingWord.collocation = getCollocation();
        bingWord.antonym = getAntonym();
        bingWord.synonym = getSynonym();
        bingWord.sentence = getSentence();
        return bingWord;
    }

    public Bing(String word) {
        this.rootDocument = null;
        this.wordInfoElement = null;
        this.baseExpCNElement = null;
        this.shapeElement = null;
        this.collocationElement = null;
        this.synonymElement = null;
        this.antonymElement = null;
        this.oxfordElement = null;
        this.expCNElement = null;
        this.expENElement = null;
        this.sentenceElement = null;
        try {
            Document document = Jsoup.connect(URL + word).get();
            this.rootDocument = document;
            if (document.getElementsByClass("in_tip").size() > 0) {
                String text = this.rootDocument.getElementsByClass("in_tip").text();
                String substring = text.substring(text.indexOf("是") + 1, text.indexOf("的"));
                this.rootDocument = Jsoup.connect(URL + substring).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.wordInfoElement = this.rootDocument.getElementsByClass("hd_area").first();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.baseExpCNElement = this.rootDocument.getElementsByClass("hd_area").first().nextElementSibling();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            this.shapeElement = this.rootDocument.getElementsByClass("hd_div1").first();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            this.collocationElement = this.rootDocument.getElementById("colid");
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            this.synonymElement = this.rootDocument.getElementById("synoid");
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            this.antonymElement = this.rootDocument.getElementById("antoid");
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        try {
            this.oxfordElement = this.rootDocument.getElementById("authid");
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        try {
            this.expCNElement = this.rootDocument.getElementById("crossid");
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        try {
            this.expENElement = this.rootDocument.getElementById("homoid");
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        try {
            this.sentenceElement = this.rootDocument.getElementById("sentenceSeg");
        } catch (Exception e11) {
            e11.printStackTrace();
        }
    }

    public String getWord() {
        try {
            return this.wordInfoElement.getElementsByTag("h1").text();
        } catch (Exception unused) {
            return "";
        }
    }

    public String getPhoneticUk() {
        String str = null;
        try {
            String text = this.wordInfoElement.getElementsByClass("hd_pr").text();
            try {
                return text.replace("英 ", "").replace("[", "/").replace("]", "/");
            } catch (Exception unused) {
                str = text;

            }
        } catch (Exception unused2) {
        }
        return str;
    }


    public String getPhoneticUs() {
        try {
            // 检查wordInfoElement是否为null
            if (this.wordInfoElement == null) {
//                logger.warn("wordInfoElement is null.");
                return "";
            }

            String text = this.wordInfoElement.getElementsByClass("hd_prUS").text();
            // 检查text是否为null或空
            if (text == null || text.isEmpty()) {
//                logger.warn("No text found for the given element.");
                return "";
            }

            // 使用正则表达式优化字符串处理
            String cleanedText = text.replaceAll("美 ", "").replaceAll("\\[", "/").replaceAll("]", "/");
            return cleanedText;
        } catch (NullPointerException e) {
//            logger.error("Failed to process the text due to null pointer exception.", e);
            return "";
        } catch (Exception e) {
//            logger.error("An unexpected error occurred.", e);
            return "";
        }
    }



    public String getUkUrl() {
        try {
            String attr = this.wordInfoElement.getElementsByClass("hd_pr").first().nextElementSibling().getElementsByTag("a").first().attr("onmouseover");
            return attr.substring(attr.indexOf("https:"), attr.indexOf(".mp3") + 4);
        } catch (Exception unused) {
            return "";
        }
    }

    public String getUsUrl() {
        try {
            String attr = this.wordInfoElement.getElementsByClass("hd_prUS").first().nextElementSibling().getElementsByTag("a").first().attr("onmouseover");
            return attr.substring(attr.indexOf("https:"), attr.indexOf(".mp3") + 4);
        } catch (Exception unused) {
            return "";
        }
    }

    public String getExpCN() {
        String str = "";
        try {
            Elements children = this.baseExpCNElement.children();
            int i = 0;
            String str2 = "";
            while (i < children.size()) {
                try {
                    Element element = children.get(i);
                    String replace = element.children().first().text().replace("网络", "web.");
                    String text = element.children().last().text();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(replace);
                    sb.append(" ");
                    sb.append(text);
                    sb.append(i == children.size() + (-1) ? "" : "\n");
                    str2 = sb.toString();
                    i++;
                } catch (Exception unused) {
                    str = str2;
                    return str;
                }
            }
            return str2;
        } catch (Exception unused2) {
        }
        return str;
    }



    public String getShape() {
        StringBuilder strBuilder = new StringBuilder();
        try {
            Elements elementsByTag = this.shapeElement.getElementsByTag("span");
            Elements elementsByTag2 = this.shapeElement.getElementsByTag("a");

            // 检查以确保两个Elements列表长度相等
            if (elementsByTag.size() != elementsByTag2.size()) {
                // 这里可以选择记录日志或抛出一个具体的异常
                System.err.println("Error: The 'span' and 'a' elements do not match in size.");
                return ""; // 返回一个默认值或根据具体场景调整
            }

            for (int i = 0; i < elementsByTag.size(); i++) {
                try {
                    String text = elementsByTag.get(i).text();
                    String text2 = elementsByTag2.get(i).text();
                    strBuilder.append(text).append(text2).append("\n"); // 直接使用StringBuilder append 方法
                } catch (Exception e) {
                    // 记录或处理异常，例如打印堆栈跟踪
                    System.err.println("Exception occurred while processing elements: " + e.getMessage());
                    // 可以考虑是否需要继续处理其他元素或直接返回
                    break; // 这里选择跳出循环，也可以根据情况选择return或其他操作
                }
            }
            return strBuilder.toString();
        } catch (Exception e) {
            // 捕获到异常时记录或处理，例如打印堆栈跟踪
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return ""; // 返回一个默认值或根据具体场景调整
        }
    }


    public String getCollocation() {
        try {
            Elements children = this.collocationElement.children();
            String str = "";
            for (int i = 0; i < children.size(); i++) {
                Element element = children.get(i);
                str = str + "<item>" + ("<title>" + element.children().first().text() + "</title>") + ("<phrase>" + element.children().last().text() + "</phrase>") + "</item>";
            }
            return "<collocation>" + str + "</collocation>";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getSynonym() {
        try {
            Elements children = this.synonymElement.children();
            String str = "";
            for (int i = 0; i < children.size(); i++) {
                Element element = children.get(i);
                str = str + "<item>" + ("<title>" + element.children().first().text() + "</title>") + ("<phrase>" + element.children().last().text() + "</phrase>") + "</item>";
            }
            return "<synonym>" + str + "</synonym>";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getAntonym() {
        try {
            Elements children = this.antonymElement.children();
            String str = "";
            for (int i = 0; i < children.size(); i++) {
                Element element = children.get(i);
                str = str + "<item>" + ("<title>" + element.children().first().text() + "</title>") + ("<phrase>" + element.children().last().text() + "</phrase>") + "</item>";
            }
            return "<antonym>" + str + "</antonym>";
        } catch (Exception unused) {
            return "";
        }
    }



    public String getOxford() {

        try {
            return processDocument();
        } catch (Exception e) {
            Log.d(Level.SEVERE.toString(), "Failed to process the document", e);
            return "";
        }
    }

    private String processDocument() {
        Elements oxfordElements = this.oxfordElement.getElementsByClass("each_seg");
        StringBuilder resultBuilder = new StringBuilder(1000); // Assuming a reasonable size for the result
        for (int i = 0; i < oxfordElements.size(); i++) {
            Element oxfordElement = oxfordElements.get(i);
            String wordClass = "<wordClass>" + oxfordElement.getElementsByClass("pos_lin").text() + "</wordClass>";
            Elements sentenceItems = oxfordElement.getElementsByClass("se_lis");

            for (int j = 0; j < sentenceItems.size(); j++) {
                Element sentenceItem = sentenceItems.get(j);
                String expCN = "<expCN>" + sentenceItem.getElementsByClass("bil").text() + "</expCN>";
                String expEN = "<expEN>" + sentenceItem.getElementsByClass("val").text() + "</expEN>";
                String flag = buildFlag(sentenceItem);

                String sentence = buildSentence(oxfordElement, sentenceItem);

                resultBuilder.append("<expItem>")
                        .append(flag)
                        .append(expCN)
                        .append(expEN)
                        .append(sentence)
                        .append("</expItem>");
            }
        }

        return "<oxford>" + resultBuilder + "</oxford>";
    }

    private String buildFlag(Element sentenceItem) {
        StringBuilder flagBuilder = new StringBuilder();
        flagBuilder.append("<flag>");
        flagBuilder.append(sentenceItem.getElementsByClass("gra").text());
        flagBuilder.append("</flag>");
        return flagBuilder.toString();
    }

    private String buildSentence(Element oxfordElement, Element sentenceItem) {
        StringBuilder sentenceBuilder = new StringBuilder();
        sentenceBuilder.append("<sentence>");

        Element nextElement = sentenceItem.nextElementSibling();
        while (nextElement != null && !"li_exs".equals(nextElement.attr("class"))) {
            sentenceBuilder.append("<en>").append(nextElement.getElementsByClass("val_ex").text()).append("</en>");
            sentenceBuilder.append("<cn>").append(nextElement.getElementsByClass("bil_ex").text()).append("</cn>");

            nextElement = nextElement.nextElementSibling();
        }

        sentenceBuilder.append("</sentence>");
        return sentenceBuilder.toString();
    }






    /**
     * 将英文解释转换为中文格式的字符串。
     * @return 格式化的中文解释字符串，若发生异常则返回空字符串。
     */
    public String getExpEN2CN() {
        StringBuilder xmlBuilder = new StringBuilder();
        Elements elementsByTag;
        try {
            elementsByTag = this.expCNElement.getElementsByTag("tr");
            if (elementsByTag == null || elementsByTag.isEmpty()) {
                // 如果没有找到元素，直接返回空字符串
                return "";
            }

            for (Element row : elementsByTag) {
                Element wordClassElement = row.getElementsByClass("pos pos1").first();
                if (wordClassElement != null) {
                    String wordClass = "<wordClass>" + wordClassElement.text() + "</wordClass>";
                    StringBuilder expItemsBuilder = new StringBuilder();
                    Elements expItems = row.getElementsByClass("df_cr_w");
                    for (Element item : expItems) {
                        String processedText = item.text().replace(";", "；").replace(",", "，");
                        expItemsBuilder.append("<expItem>").append(processedText).append("</expItem>");
                    }
                    xmlBuilder.append("<item>").append(wordClass)
                            .append("<exp>").append(expItemsBuilder.toString()).append("</exp>")
                            .append("</item>");
                }
            }
            return "<expCN>" + xmlBuilder.toString() + "</expCN>";
        } catch (Exception e) {
            // 优化异常处理：记录异常信息，对于示例代码，我们只是打印堆栈跟踪
            e.printStackTrace();
            return "";
        }
    }



    public String getExpEN() {
    try {
        Elements elementsByTag = this.expENElement.getElementsByTag("tr");

        // 检查是否为空，避免IndexOutOfBoundsException
        if (elementsByTag.isEmpty()) {
            return "<expEN></expEN>";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<expEN>");

        for (int i = 0; i < elementsByTag.size(); i++) {
            Element itemElement = elementsByTag.get(i);

            // 修正了未使用的变量问题，同时提高了代码的可读性
            String posText = itemElement.getElementsByClass("pos pos1").text();
            StringBuilder expItemsBuilder = new StringBuilder();

            Elements expItems = itemElement.getElementsByClass("df_cr_w");
            for (int j = 0; j < expItems.size(); j++) {
                expItemsBuilder.append("<expItem>").append(expItems.get(j).text()).append("</expItem>");
            }

            sb.append("<item>");
            sb.append("<wordClass>").append(posText).append("</wordClass>");
            sb.append("<exp>").append(expItemsBuilder.toString()).append("</exp>");
            sb.append("</item>");
        }

        sb.append("</expEN>");
        return sb.toString();
    } catch (Exception e) {
        // 改进了异常处理，记录异常信息
        e.printStackTrace();
        // 在异常情况下返回空字符串，保持了函数的契约
        return "";
    }
}


    public String getSentence() {
        try {
            Iterator<Element> it = this.sentenceElement.getElementsByClass("se_li").iterator();
            String str = "";
            while (it.hasNext()) {
                Element next = it.next();
                str = str + "<item>" + ("<en>" + next.getElementsByClass("sen_en").text() + "</en>") + ("<cn>" + next.getElementsByClass("sen_cn").text() + "</cn>") + ("<from>" + next.getElementsByClass("sen_li").text() + "</from>") + "</item>";
            }
            return "<sentence>" + str + "</sentence>";
        } catch (Exception unused) {
            return "";
        }
    }
}
