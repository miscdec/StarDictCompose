package com.datong.dict.utils;

import android.text.Html;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public class TextUtil {
    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static String htmlString(String str) {
        return Html.fromHtml(str).toString();
    }

    public static String cleanWord(String word) {
        return word.replace(",", "").replace(".", "").replace("!", "").replace("?", "").trim();
    }

    public static boolean isLetter(String str) {
        return isLetter(str.charAt(0));
    }

    public static String upperFrist(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String lastNextChar(String str) {
        return str.substring(0, str.length() - 1) + ((char) (str.charAt(str.length() - 1) + 1));
    }

    public static String URLEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8" );
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String strToUnicode(String str) {
        StringBuilder stringBuffer = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            stringBuffer.append("\\u").append(Integer.toHexString(c));
        }
        return stringBuffer.toString();
    }

    public static String unicodeToStr(String unicode) {
        StringBuilder stringBuffer = new StringBuilder();
        String[] split = unicode.split("\\\\u");
        for (int i = 1; i < split.length; i++) {
            stringBuffer.append((char) Integer.parseInt(split[i], 16));
        }
        return stringBuffer.toString();
    }

    public static String unicodeToUTF8(String str) {
        try {
            return new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
