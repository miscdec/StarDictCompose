package com.datong.dict.data.crawler.dict.en;

import com.datong.dict.data.crawler.pojo.dictCn.FrequencyItem;
import com.datong.dict.data.crawler.pojo.dictCn.SentenceFrequencyItem;
import com.datong.dict.data.dictionary.en.local.entity.DictCnWord;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/* loaded from: classes.dex */
public class DictCn {
    private static final String URL = "http://dict.cn/";
    private Element collocationElement;
    private Element discriminateElement;
    private Element expDetailElement;
    private Element expDoubleElement;
    private Element expENElement;
    private Element mistakesElement;
    private Element oftenPhraseElement;
    private Element oftenSentenceElement;
    private Document rootDocument;
    private Element sentenceElement;
    private Element synonAndAntonElement;
    private Element usageElement;
    private Element wordInfoElement;

    public DictCnWord getDictWord() {
        DictCnWord dictCnWord = new DictCnWord();
        dictCnWord.word = getWord();
        dictCnWord.syllable = getSyllable();
        dictCnWord.star = getStar();
        dictCnWord.setPhoneticUs(getPhoneticUs());
        dictCnWord.phoneticUk = getPhoneticUk();
        dictCnWord.usSoundUrlMan = getUsUrlMan();
        dictCnWord.usSoundUrlWoman = getUsUrlWoman();
        dictCnWord.ukSoundUrlMan = getUkUrlMan();
        dictCnWord.ukSoundUrlWoman = getUkUrlWoman();
        dictCnWord.expCN = getExpCN();
        dictCnWord.shape = getShape();
        dictCnWord.frequency = getFrequency();
        dictCnWord.expDetail = getExpDetail();
        dictCnWord.expDouble = getExpDouble();
        dictCnWord.expEN = getExpEN();
        dictCnWord.sentence = getSentence();
        dictCnWord.sentenceFrequency = getSentenceFrequency();
        dictCnWord.oftenSentence = getOftenSentence();
        dictCnWord.oftenPhrase = getOftenPhrase();
        dictCnWord.collocation = getCollocation();
        dictCnWord.usage = getUsage();
        dictCnWord.discriminate = getDiscriminate();
        dictCnWord.mistakes = getMistakes();
        dictCnWord.synonAndAnton = getSynonAndAnton();
        return dictCnWord;
    }

    public DictCn(String word) {
        this.rootDocument = null;
        this.wordInfoElement = null;
        this.expDetailElement = null;
        this.expDoubleElement = null;
        this.expENElement = null;
        this.sentenceElement = null;
        this.oftenSentenceElement = null;
        this.oftenPhraseElement = null;
        this.collocationElement = null;
        this.usageElement = null;
        this.discriminateElement = null;
        this.mistakesElement = null;
        this.synonAndAntonElement = null;
        try {
            this.rootDocument = Jsoup.connect(URL + word).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.wordInfoElement = this.rootDocument.getElementsByClass("word").first();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            this.expDetailElement = this.rootDocument.getElementsByClass("layout detail").first();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        try {
            this.expDoubleElement = this.rootDocument.getElementsByClass("layout dual").first();
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        try {
            this.expENElement = this.rootDocument.getElementsByClass("layout en").first();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        try {
            this.sentenceElement = this.rootDocument.getElementsByClass("layout sort").first();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
        try {
            this.oftenSentenceElement = this.rootDocument.getElementsByClass("layout patt").first();
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        try {
            this.oftenPhraseElement = this.rootDocument.getElementsByClass("layout phrase").first();
        } catch (Exception e8) {
            e8.printStackTrace();
        }
        try {
            this.collocationElement = this.rootDocument.getElementsByClass("layout coll").first();
        } catch (Exception e9) {
            e9.printStackTrace();
        }
        try {
            this.usageElement = this.rootDocument.getElementsByClass("layout ess").first();
        } catch (Exception e10) {
            e10.printStackTrace();
        }
        try {
            this.discriminateElement = this.rootDocument.getElementsByClass("layout discrim").first();
        } catch (Exception e11) {
            e11.printStackTrace();
        }
        try {
            this.mistakesElement = this.rootDocument.getElementsByClass("layout comn").first();
        } catch (Exception e12) {
            e12.printStackTrace();
        }
        try {
            this.synonAndAntonElement = this.rootDocument.getElementsByClass("layout nfo").first();
        } catch (Exception e13) {
            e13.printStackTrace();
        }
    }

//    public DictCnWord getDictCnWord(ParseObject object) {
//        return new DictCnWord();
//    }

    public String getWord() {
        try {
            return this.wordInfoElement.getElementsByClass("keyword").first().text();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getSyllable() {
        try {
            String attr = this.rootDocument.getElementsByClass("keyword").first().attr("tip");
            return attr.substring(attr.indexOf("：") + 1, attr.length());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getStar() {
        try {
            String attr = this.wordInfoElement.getElementsByClass("level-title").first().nextElementSibling().attr("class");
            return Integer.valueOf(attr.substring(attr.indexOf("_") + 1, attr.length())).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getPhoneticUk() {
        String str = "";
        try {
            str = this.wordInfoElement.getElementsByClass("phonetic").first().children().first().getElementsByTag("bdo").text();
            return str.replace("[", "/").replace("]", "/");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getPhoneticUs() {
        String str = "";
        try {
            str = this.wordInfoElement.getElementsByClass("phonetic").first().children().last().getElementsByTag("bdo").text();
            return str.replace("[", "/").replace("]", "/");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public String getUkUrlWoman() {
        try {
            return "http://audio.dict.cn/" + this.wordInfoElement.getElementsByClass("phonetic").first().children().first().getElementsByTag("i").first().attr("naudio");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getUkUrlMan() {
        try {
            return "http://audio.dict.cn/" + this.wordInfoElement.getElementsByClass("phonetic").first().children().first().getElementsByTag("i").last().attr("naudio");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getUsUrlWoman() {
        try {
            return "http://audio.dict.cn/" + this.wordInfoElement.getElementsByClass("phonetic").first().children().last().getElementsByTag("i").first().attr("naudio");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getUsUrlMan() {
        try {
            return "http://audio.dict.cn/" + this.wordInfoElement.getElementsByClass("phonetic").first().children().last().getElementsByTag("i").last().attr("naudio");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getExpCN() {
        String str;
        Exception e;
        try {
            Elements children = this.wordInfoElement.getElementsByClass("dict-basic-ul").first().children();
            int i = 0;
            str = "";
            while (i < children.size()) {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(children.get(i).text());
                    sb.append(i >= children.size() + (-2) ? "" : "\n");
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
        }
        return str;
    }

    public String getShape() {
        String str;
        Exception e;
        try {
            Elements elementsByTag = this.wordInfoElement.getElementsByClass("shape").first().getElementsByTag("label");
            int i = 0;
            str = "";
            while (i < elementsByTag.size()) {
                try {
                    String text = elementsByTag.get(i).text();
                    String text2 = elementsByTag.get(i).nextElementSibling().text();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(text);
                    sb.append(" ");
                    sb.append(text2);
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

    public String getFrequency() {
        String str = "";
        Exception e;
        try {
            try {
                str = URLDecoder.decode(this.wordInfoElement.getElementById("dict-chart-basic").attr("data"), "utf-8");
                LinkedTreeMap linkedTreeMap = new Gson().fromJson(str, LinkedTreeMap.class);
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < linkedTreeMap.size()) {
                    Gson gson = new Gson();
                    StringBuilder sb = new StringBuilder();
                    i++;
                    sb.append(i);
                    sb.append("");
                    arrayList.add(gson.fromJson(linkedTreeMap.get(sb.toString()).toString(), FrequencyItem.class));
                }
                String json = new Gson().toJson(arrayList);
                linkedTreeMap.clear();
                arrayList.clear();
                return json;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            str = "";
        }
        return str;
    }


    public String getExpDetail() {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<expDetail>");

        try {
            for (Element spanElement : this.expDetailElement.getElementsByTag("span")) {
                String wordClassText = spanElement.text();
                String wordClassTag = "<wordClass>" + wordClassText + "</wordClass>";

                // 移除(wordClassText中括号包含的内容)
                String cleanedWordClassTag = removeBracketsContent(wordClassTag);

                Elements liElements = spanElement.nextElementSibling().getElementsByTag("li");
                Iterator<Element> liIt = liElements.iterator();
                StringBuilder itemBuilder = new StringBuilder();
                while (liIt.hasNext()) {
                    itemBuilder.append("<expItem>").append(liIt.next().text()).append("</expItem>");
                }

                resultBuilder.append("<item>").append(cleanedWordClassTag).append(itemBuilder).append("</item>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        resultBuilder.append("</expDetail>");
        return resultBuilder.toString();
    }

    private String removeBracketsContent(String input) {
        int openBracketIndex = input.indexOf("(");
        int closeBracketIndex = input.indexOf(")");

        if (openBracketIndex != -1 && closeBracketIndex != -1 && openBracketIndex < closeBracketIndex) {
            return input.substring(0, openBracketIndex) + input.substring(closeBracketIndex + 1);
        }
        return input;
    }



    // 改进后的函数
    public String getExpDouble() {
        try {
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("<expDouble>");

            Iterator<Element> it = this.expDoubleElement.getElementsByTag("span").iterator();
            while (it.hasNext()) {
                Element currentSpan = it.next();

                // 对 <wordClass> 进行处理
                String wordClassContent = processWordClass(currentSpan);

                // 获取下一个 sibling，并处理 <expItem>
                Element nextSibling = currentSpan.nextElementSibling();
                if (nextSibling != null) {
                    String itemContent = processItem(nextSibling);
                    wordClassContent += itemContent;
                }

                resultBuilder.append("<item>").append(wordClassContent).append("</item>");
            }

            resultBuilder.append("</expDouble>");
            return resultBuilder.toString();
        } catch (Exception e) {
            // 优化异常处理：记录异常信息，而不是静默处理
            // log.error("Error processing expDouble elements", e); // 假设存在日志记录机制
            System.out.println("Error occurred: " + e.getMessage());
            return "";
        }
    }

    // 处理 <wordClass> 标签的辅助方法
    private String processWordClass(Element wordClassElement) {
        return "<wordClass>" + sanitizeText(wordClassElement.text()) + "</wordClass>";
    }

    // 处理 <item> 中的 <expItem> 标签的辅助方法
    private String processItem(Element itemElement) {
        StringBuilder itemContentBuilder = new StringBuilder();
        Iterator<Element> it2 = itemElement.getElementsByTag("li").iterator();
        while (it2.hasNext()) {
            Element nextLi = it2.next();
            String expCN = sanitizeText(nextLi.getElementsByTag("strong").text());
            String expEN = sanitizeText(nextLi.ownText());
            itemContentBuilder.append("<expItem><expCN>").append(expCN).append("</expCN><expEN>").append(expEN).append("</expEN></expItem>");
        }
        return itemContentBuilder.toString();
    }

    // 对文本进行清理和转义，防止XSS攻击
    private String sanitizeText(String text) {
        // 这里可以使用第三方库或Java提供的API进行HTML清理和转义
        // 例如：return Jsoup.isValid(text, whitelist); // 假设使用jsoup库
        return text; // 仅作为示例，实际应用中应使用专门的库进行处理
    }



    public String getExpEN() {
        try {
            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("<expEN>");

            Elements expENElements = this.expENElement.getElementsByTag("span");
            for (Element spanElement : expENElements) {
                String wordClassContent = spanElement.text();
                resultBuilder.append("<wordClass>").append(wordClassContent).append("</wordClass>");

                Element nextElement = spanElement.nextElementSibling();
                if (nextElement != null) {
                    Elements liElements = nextElement.getElementsByTag("li");
                    for (Element liElement : liElements) {
                        String expContent = liElement.ownText().replace(";", ".");
                        resultBuilder.append("<exp>").append(expContent).append("</exp>");

                        String sentenceContent = liElement.getElementsByTag("p").text();
                        if (!sentenceContent.isEmpty()) {
                            resultBuilder.append("<sentence>").append(sentenceContent).append("</sentence>");
                        }
                        resultBuilder.append("<expItem></expItem>");
                    }
                }

                resultBuilder.append("<item></item>");
            }

            resultBuilder.append("</expEN>");
            return resultBuilder.toString();
        } catch (Exception e) {
            // Consider logging the exception details for debugging purposes.
            System.err.println("An error occurred while processing expEN: " + e.getMessage());
            return ""; // Return empty string as per original behavior.
        }
    }




    public String getSentence() {
        try {
            Iterator<Element> it = this.sentenceElement.getElementsByTag("div").iterator();
            String str = "";
            while (it.hasNext()) {
                Element next = it.next();
                if (!next.attr("class").equals("layout sort") && !next.attr("id").equals("dict-chart-examples")) {
                    String str2 = "<title>" + next.text() + "</title>";
                    Iterator<Element> it2 = next.nextElementSibling().getElementsByTag("li").iterator();
                    String str3 = "";
                    while (it2.hasNext()) {
                        Element next2 = it2.next();
                        str3 = str3 + "<sentenceItem>" + ("<en>" + next2.html().split("<br>")[0] + "</en>") + ("<cn>" + next2.html().split("<br>")[1] + "</cn>") + "</sentenceItem>";
                    }
                    str = str + "<item>" + str2 + str3 + "</item>";
                }
            }
            return "<sentence>" + str + "</sentence>";
        } catch (Exception unused) {
            return "";
        }
    }

    public String getSentenceFrequency() {
        String decode = "";
        try {
            try {
                decode = URLDecoder.decode(this.sentenceElement.getElementById("dict-chart-examples").attr("data"), "utf-8");
                LinkedTreeMap linkedTreeMap = new Gson().fromJson(decode, LinkedTreeMap.class);
                ArrayList arrayList = new ArrayList();
                int i = 0;
                while (i < linkedTreeMap.size()) {
                    Gson gson = new Gson();
                    StringBuilder sb = new StringBuilder();
                    i++;
                    sb.append(i);
                    sb.append("");
                    arrayList.add(gson.fromJson(linkedTreeMap.get(sb.toString()).toString(), SentenceFrequencyItem.class));
                }
                String json = new Gson().toJson(arrayList);
                linkedTreeMap.clear();
                arrayList.clear();
                return json;
            } catch (Exception unused) {
                return decode;
            }
        } catch (Exception unused2) {
            return "";
        }
    }

    public String getOftenSentence() {
    try {
        Elements children = this.oftenSentenceElement.children();
        StringBuilder oftenSentenceBuilder = new StringBuilder();
        for (int i = 0; i < children.size(); i++) {
            Element element = children.get(i);
            if ("ol".equals(element.tagName())) {
                String divTagContent = getDivTagContent(children, i);
                if (divTagContent != null) {
                    processOlElement(element, oftenSentenceBuilder, divTagContent);
                }
            }
        }
        return "<oftenSentence>" + oftenSentenceBuilder.toString() + "</oftenSentence>";
    } catch (Exception e) {
        // 更具体的异常类型（如NullPointerException, IndexOutOfBoundsException）可根据实际情况进行捕获
        // 此处为示例，实际应用中应根据具体需求进行调整
        // 记录日志等
        return "";
    }
}

private String getDivTagContent(Elements children, int currentIndex) {
    // 适当调整逻辑以减少嵌套
    if (currentIndex - 3 >= 0 && "div".equals(children.get(currentIndex - 3).tagName())) {
        return children.get(currentIndex).text();
    }
    return null;
}

private void processOlElement(Element olElement, StringBuilder oftenSentenceBuilder, String divTagContent) {
    Elements olChildren = olElement.children();
    for (int i = 0; i < olChildren.size(); i++) {
        Element olChild = olChildren.get(i);
        // 优化字符串构建
        oftenSentenceBuilder.append(generateItemString(olChild, i + 1, divTagContent));
    }
}

private String generateItemString(Element element, int index, String divTagContent) {
    // 使用StringBuilder以提高性能
    StringBuilder itemStringBuilder = new StringBuilder();
    itemStringBuilder.append("<index>").append(index).append("</index>");
    String[] parts = element.html().split("<br>");
    if (parts.length > 1) {
        itemStringBuilder.append("<cn>").append(parts[1]).append("</cn>");
    }
    itemStringBuilder.append("<en>").append(parts[0]).append("</en>");
    return itemStringBuilder.toString();
}




    public String getOftenPhrase() {
        StringBuilder oftenPhraseBuilder = new StringBuilder();
        try {
            Elements oftenPhraseElements = this.oftenPhraseElement.getElementsByTag("dl");
            for (Element next : oftenPhraseElements) {
                String title = "<title>" + next.getElementsByTag("dt").text() + "</title>";
                String explanation = next.getElementsByTag("ol").first() != null ? "<explain>" + next.getElementsByTag("ol").first().ownText() + "</explain>" : "";
                String phrase = next.getElementsByTag("strong").first() != null ? "<phrase>" + next.getElementsByTag("strong").first().text() + "</phrase>" : "";
                StringBuilder sentenceItemsBuilder = new StringBuilder();
                for (int i = 0; i < next.getElementsByTag("p").size(); i += 2) {
                    sentenceItemsBuilder.append("<sentenceItem><en>").append(next.getElementsByTag("p").get(i).text()).append("</en><cn>").append(next.getElementsByTag("p").get(i + 1).text()).append("</cn></sentenceItem>");
                }
                String sentenceItems = sentenceItemsBuilder.toString();
                oftenPhraseBuilder.append("<item>").append(title).append(explanation).append(phrase).append(sentenceItems).append("</item>");
            }
            return "<OftenPhrase>" + oftenPhraseBuilder.toString() + "</OftenPhrase>";
        } catch (Exception e) {
            // 推荐至少记录异常信息，这里使用System.err.println()作为示例，实际应用中应使用日志框架如Log4j等
            System.err.println("Error processing the HTML content: " + e.getMessage());
            return ""; // 在异常情况下返回空字符串
        }
    }



    public String getCollocation() {
    try {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("<collocation>");

        Elements children = this.collocationElement.children();
        for (int i = 0; i < children.size(); i++) {
            Element element = children.get(i);
            if ("ul".equals(element.tagName())) {
                String itemTitle = getItemTitle(children, i);
                String itemSubTitle = getItemSubTitle(children, i);
                String itemContent = getItemContent(element);
                resultBuilder.append("<item>").append(itemTitle).append(itemSubTitle).append(itemContent).append("</item>");
            }
        }

        resultBuilder.append("</collocation>");
        return resultBuilder.toString();
    } catch (Exception e) {
        // 在实际应用中，最好对不同类型的异常进行单独处理，并记录异常信息
        // 例如，日志记录或用户友好的错误反馈
        return "";
    }
}

private String getItemTitle(Elements children, int index) {
    if (index < 2 || !"div".equals(children.get(index - 2).tagName())) {
        return "";
    }
    return "<itemTitle>" + children.get(index).text() + "</itemTitle>";
}

private String getItemSubTitle(Elements children, int index) {
    if (index < 1 || !"b".equals(children.get(index - 1).tagName())) {
        return "";
    }
    return "<itemSubTitle>" + children.get(index).text() + "</itemSubTitle>";
}

private String getItemContent(Element element) {
    StringBuilder contentBuilder = new StringBuilder();
    Elements liElements = element.getElementsByTag("li");
    for (Element li : liElements) {
        String phrase = "<phrase>" + li.getElementsByTag("a").text()
                .replace("〔", "(").replace("〕", ")") + "</phrase>";
        String explain = "<explain>" + li.ownText() + "</explain>";
        contentBuilder.append("<phraseItem>").append(phrase).append(explain).append("</phraseItem>");
    }
    return contentBuilder.toString();
}


    public String getUsage() {
    Element nextElement; // 更明确的变量名
    StringBuilder strBuilder = new StringBuilder(); // 使用StringBuilder来构建字符串
    try {
        Iterator<Element> usageIterator = this.usageElement.getElementsByTag("span").iterator();

        while (usageIterator.hasNext()) {
            nextElement = usageIterator.next(); // 正确使用Iterator

            // 检查nextElement是否为null
            if (nextElement != null) {
                String str2 = "<wordClass>" + nextElement.text() + "</wordClass>";
                StringBuilder str3Builder = new StringBuilder(); // 使用StringBuilder来构建内部字符串

                // 迭代下一个兄弟元素，这里假设nextElementSibling()不会返回null，这个假设基于对原代码的理解
                Element siblingElement = nextElement.nextElementSibling();
                if (siblingElement != null) {
                    Iterator<Element> liIterator = siblingElement.getElementsByTag("li").iterator();
                    while (liIterator.hasNext()) {
                        Element liElement = liIterator.next();
                        if (liElement != null) {
                            str3Builder.append("<usageContent>").append(liElement.text()).append("</usageContent>");
                        }
                    }
                }

                strBuilder.append("<item>").append(str2).append(str3Builder.toString()).append("</item>");
            }
        }

        return "<usage>" + strBuilder.toString() + "</usage>";
    } catch (NullPointerException | NoSuchElementException e) { // 捕获更具体的异常
        // 可以考虑记录日志或进行其他错误处理
        return ""; // 在异常情况下返回空字符串
    }
}


    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public String getDiscriminate() {
        String discriminateXml = "";
        try {
            Iterator<Element> it = this.discriminateElement.getElementsByTag("dl").iterator();
            StringBuilder xmlBuilder = new StringBuilder();

            while (it.hasNext()) {
                Element dlElement = it.next();
                String titleTagContent = "<title>" + dlElement.getElementsByTag("dt").text() + "</title>";
                Elements liElements = dlElement.getElementsByTag("li");

                StringBuilder contentItemsStringBuilder = new StringBuilder();
                for (int i = 0; i < liElements.size(); i++) {
                    contentItemsStringBuilder.append(liElements.get(i).text());
                    if (i < liElements.size() - 1) {
                        contentItemsStringBuilder.append("||");
                    }
                }

                xmlBuilder.append("<item>")
                        .append(titleTagContent)
                        .append("<content>")
                        .append(contentItemsStringBuilder)
                        .append("</content>")
                        .append("</item>");
            }

            discriminateXml = "<discriminate>" + xmlBuilder.toString() + "</discriminate>";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error generating discriminate XML", e);
        }

        return discriminateXml;
    }





    public String getMistakes() {
        StringBuilder resultBuilder = new StringBuilder();
        try {
            Elements spans = this.mistakesElement.getElementsByTag("span");
            for (Element span : spans) {
                String wordClassText = "<wordClass>" + span.text() + "</wordClass>";
                Element nextElement = span.nextElementSibling();
                if (nextElement == null) {
                    continue; // 防止空指针异常
                }
                String[] parts = nextElement.ownText().split("。 ");
                Elements children = nextElement.children();
                for (int i = 0; i < parts.length; i++) {
                    String part = parts[i];
                    if (isValidErrorPart(children, i)) {
                        resultBuilder.append(generateMistakeItem(part, children.get(i).ownText(),
                                children.get(i + 1).ownText(), children.get(i + 2).ownText(), children.get(i + 3).ownText()));
                    }
                }
            }
            return "<mistakes>" + resultBuilder.toString() + "</mistakes>";
        } catch (Exception e) {
            // 适当的异常处理，这里只是简单打印异常堆栈，实际应用中可能需要更详细的处理
            e.printStackTrace();
            return ""; // 在异常情况下返回空字符串，保持了原方法的契约
        }
    }

    private boolean isValidErrorPart(Elements children, int index) {
        // 检查是否有足够的元素来避免索引越界，并检查是否有误正标签
        return index + 3 < children.size() &&
               "误".equals(children.get(index).getElementsByTag("误").text()) &&
               "正".equals(children.get(index + 2).getElementsByTag("正").text());
    }

    private String generateMistakeItem(String part, String error, String proper01, String proper02, String analyze) {
        // 生成单个错误项目字符串
        return "<mistakeItem>" +
               "<cn>" + part + "</cn>" +
               "<error>" + error + "</error>" +
               "<proper01>" + proper01 + "</proper01>" +
               "<proper02>" + proper02 + "</proper02>" +
               "<analyze>" + analyze + "</analyze>" +
               "</mistakeItem>";
    }


    public String getSynonAndAnton() {
        try {
            Elements elementsByTag = this.synonAndAntonElement.getElementsByTag("div");
            elementsByTag.remove(0);
            Iterator<Element> it = elementsByTag.iterator();
            String str = "";
            while (it.hasNext()) {
                Element next = it.next();
                str = str + "<item>" + ("<title>" + next.text().replace("【", "").replace("】", "") + "</title>") + ("<content>" + next.nextElementSibling().getElementsByTag("a").text() + "</content>") + "</item>";
            }
            return "<synonAndAnton>" + str + "</synonAndAnton>";
        } catch (Exception unused) {
            return "";
        }
    }
}
