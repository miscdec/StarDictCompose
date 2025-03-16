package com.datong.dict.utils;

import java.util.HashMap;

/* loaded from: classes.dex */
public class ConvertUtil {
    public static String MAP_BING;
    public static String MAP_CAMBRIDGE;
    public static String MAP_COLLINS;
    public static String MAP_DATONG_EN;
    public static String MAP_DICTCN;
    public static String MAP_ETYMON;
    public static String MAP_LONGMAN;
    public static String MAP_OXFORD;
    public static String MAP_WEBSTER;
    public static String MAP_YOUDAO;

    public static String decrypt(String map, String content) {
        return (map == null || map.isEmpty()) ? content : translate(translate(content, getDecryptMap(map)), getDecryptMap(map));
    }

    public static String translate(String content, HashMap<String, String> keyMap) {
        String str = "";
        for (int i = 0; i < content.length(); i++) {
            String str2 = content.charAt(i) + "";
            if (keyMap.containsKey(str2)) {
                str2 = keyMap.get(str2);
            }
            str = str + str2;
        }
        return str;
    }

    public static HashMap<String, String> getEncryptMap(String key) {
        String[] split = key.split(" ");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : split) {
            hashMap.put(str.charAt(0) + "", str.charAt(1) + "");
        }
        return hashMap;
    }

    public static HashMap<String, String> getDecryptMap(String key) {
        String[] split = key.split(" ");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : split) {
            hashMap.put(str.charAt(1) + "", str.charAt(0) + "");
        }
        return hashMap;
    }
}
