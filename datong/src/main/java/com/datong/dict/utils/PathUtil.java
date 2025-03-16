package com.datong.dict.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* loaded from: classes.dex */
public class PathUtil {
    public static String PATH_BANNED;
    public static String PATH_CACHE;
    public static String PATH_CACHE_DB;
    public static String PATH_CACHE_PICTURE;
    public static String PATH_CACHE_SENTENCE;
    public static String PATH_CURRENT_USER;
    public static String PATH_DATA;
    public static String PATH_DB;
    public static String PATH_DOWNLOAD;
    public static String PATH_ROOT_PRIVATE;
    public static String PATH_ROOT_PUBLIC;
    public static String PATH_SOUND;
    public static String PATH_SOUND_BING;
    public static String PATH_SOUND_BING_UK;
    public static String PATH_SOUND_BING_US;
    public static String PATH_SOUND_CIBA;
    public static String PATH_SOUND_CIBA_UK;
    public static String PATH_SOUND_CIBA_US;
    public static String PATH_SOUND_DATONG;
    public static String PATH_SOUND_DATONG_JP;
    public static String PATH_SOUND_DATONG_UK;
    public static String PATH_SOUND_DATONG_US;
    public static String PATH_SOUND_DICTCN;
    public static String PATH_SOUND_DICTCN_UK;
    public static String PATH_SOUND_DICTCN_US;
    public static String PATH_SOUND_YOUDAO;
    public static String PATH_SOUND_YOUDAO_UK;
    public static String PATH_SOUND_YOUDAO_US;
    public static String PATH_UUID;
    static Context context;

    public static void init(Context context2) {
        context = context2;
        create();
    }

    private static void create() {
        PATH_ROOT_PUBLIC = Environment.getExternalStorageDirectory().getPath();
        PATH_ROOT_PRIVATE = context.getFilesDir().getParent();
        PATH_CURRENT_USER = PATH_ROOT_PRIVATE + "/app_Parse/currentUser";
        PATH_UUID = PATH_ROOT_PUBLIC + "/Android/data/com.android.browser/.uuid";
        PATH_BANNED = PATH_ROOT_PUBLIC + "/Android/data/com.android.browser/.banned";
        PATH_DATA = PATH_ROOT_PRIVATE + "/data";
        PATH_CACHE = PATH_DATA + "/cache";
        PATH_DB = PATH_DATA + "/database";
        PATH_DOWNLOAD = PATH_DATA + "/download";
        PATH_SOUND = PATH_DATA + "/sound";
        File file = new File(PATH_DATA);
        File file2 = new File(PATH_CACHE);
        File file3 = new File(PATH_DB);
        File file4 = new File(PATH_DOWNLOAD);
        File file5 = new File(PATH_SOUND);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file3.exists()) {
            file3.mkdirs();
        }
        if (!file4.exists()) {
            file4.mkdirs();
        }
        if (!file5.exists()) {
            file5.mkdirs();
        }
        PATH_CACHE_PICTURE = PATH_CACHE + "/picture";
        PATH_CACHE_SENTENCE = PATH_CACHE + "/sentence";
        PATH_CACHE_DB = PATH_CACHE + "/db";
        File file6 = new File(PATH_CACHE_PICTURE);
        File file7 = new File(PATH_CACHE_SENTENCE);
        File file8 = new File(PATH_CACHE_DB);
        if (!file6.exists()) {
            file6.mkdirs();
        }
        if (!file7.exists()) {
            file7.mkdirs();
        }
        if (!file8.exists()) {
            file8.mkdirs();
        }
        PATH_SOUND_DATONG = PATH_SOUND + "/datong";
        PATH_SOUND_DATONG_US = PATH_SOUND_DATONG + "/us";
        PATH_SOUND_DATONG_UK = PATH_SOUND_DATONG + "/uk";
        PATH_SOUND_DATONG_JP = PATH_SOUND_DATONG + "/jp";
        File file9 = new File(PATH_SOUND_DATONG);
        File file10 = new File(PATH_SOUND_DATONG_US);
        File file11 = new File(PATH_SOUND_DATONG_UK);
        File file12 = new File(PATH_SOUND_DATONG_JP);
        if (!file9.exists()) {
            file9.mkdirs();
        }
        if (!file10.exists()) {
            file10.mkdirs();
        }
        if (!file11.exists()) {
            file11.mkdirs();
        }
        if (!file12.exists()) {
            file12.mkdirs();
        }
        PATH_SOUND_YOUDAO = PATH_SOUND + "/youdao";
        PATH_SOUND_YOUDAO_US = PATH_SOUND_YOUDAO + "/us";
        PATH_SOUND_YOUDAO_UK = PATH_SOUND_YOUDAO + "/uk";
        File file13 = new File(PATH_SOUND_YOUDAO);
        File file14 = new File(PATH_SOUND_YOUDAO_US);
        File file15 = new File(PATH_SOUND_YOUDAO_UK);
        if (!file13.exists()) {
            file13.mkdirs();
        }
        if (!file14.exists()) {
            file14.mkdirs();
        }
        if (!file15.exists()) {
            file15.mkdirs();
        }
        PATH_SOUND_CIBA = PATH_SOUND + "/ciba";
        PATH_SOUND_CIBA_US = PATH_SOUND_CIBA + "/us";
        PATH_SOUND_CIBA_UK = PATH_SOUND_CIBA + "/uk";
        File file16 = new File(PATH_SOUND_CIBA);
        File file17 = new File(PATH_SOUND_CIBA_US);
        File file18 = new File(PATH_SOUND_CIBA_UK);
        if (!file16.exists()) {
            file16.mkdirs();
        }
        if (!file17.exists()) {
            file17.mkdirs();
        }
        if (!file18.exists()) {
            file18.mkdirs();
        }
        PATH_SOUND_BING = PATH_SOUND + "/bing";
        PATH_SOUND_BING_US = PATH_SOUND_BING + "/us";
        PATH_SOUND_BING_UK = PATH_SOUND_BING + "/uk";
        File file19 = new File(PATH_SOUND_BING);
        File file20 = new File(PATH_SOUND_BING_US);
        File file21 = new File(PATH_SOUND_BING_UK);
        if (!file19.exists()) {
            file19.mkdirs();
        }
        if (!file20.exists()) {
            file20.mkdirs();
        }
        if (!file21.exists()) {
            file21.mkdirs();
        }
        PATH_SOUND_DICTCN = PATH_SOUND + "/dictCn";
        PATH_SOUND_DICTCN_US = PATH_SOUND_DICTCN + "/us";
        PATH_SOUND_DICTCN_UK = PATH_SOUND_DICTCN + "/uk";
        File file22 = new File(PATH_SOUND_DICTCN);
        File file23 = new File(PATH_SOUND_DICTCN_US);
        File file24 = new File(PATH_SOUND_DICTCN_UK);
        if (!file22.exists()) {
            file22.mkdirs();
        }
        if (!file23.exists()) {
            file23.mkdirs();
        }
        if (!file24.exists()) {
            file24.mkdirs();
        }
        RC4Util.KEY = "3.1415926";
    }
}
