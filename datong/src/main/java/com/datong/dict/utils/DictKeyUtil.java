package com.datong.dict.utils;

import android.content.Context;

/* loaded from: classes.dex */
public class DictKeyUtil {
    public static final String KEY_BING = "DICT_EN_BING";
    public static final String KEY_CIBA = "DICT_EN_CIBA";
    public static final String KEY_DATONG_EN = "DICT_EN_DATONG";
    public static final String KEY_DATONG_JP = "DICT_JP_DATONG";
    public static final String KEY_DICTCN = "DICT_EN_DICTCN";
    public static final String KEY_YOUDAO = "DICT_EN_YOUDAO";
    static Context context;

    public static void init(Context context2) {
        context = context2;
    }

    public static String getKey(String key) {
        return context.getSharedPreferences("DICT_KEYS", 0).getString(key, null);
    }

    public static void setKey(String key, String value) {
        context.getSharedPreferences("DICT_KEYS", 0).edit().putString(key, value).apply();
    }
}
