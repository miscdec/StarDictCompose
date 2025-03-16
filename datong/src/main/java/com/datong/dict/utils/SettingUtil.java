package com.datong.dict.utils;

import android.content.Context;
import android.os.Build;

/* loaded from: classes.dex */
public class SettingUtil {
    public static final String KEY_ALLOWED_SCREEN_SHOT = "ALLOWED_SCREEN_SHOT";
    public static final String KEY_AUTO_COLLECT = "AUTO_COLLECT";
    public static final String KEY_AUTO_COLLECT_MODE = "COLLECT_MODE";
    public static final String KEY_AUTO_COLLECT_WHEN_CLICK_QUERY = "AUTO_COLLECT_WHEN_CLICK_QUERY";
    public static final String KEY_AUTO_COLLECT_WHEN_OUTSIDE_QUERY = "AUTO_COLLECT_WHEN_OUTSIDE_QUERY";
    public static final String KEY_AUTO_PLAY_SOUND = "AUTO_PLAY_SOUND";
    public static final String KEY_BACK_TO_SEARCH = "BACK_TO_SEARCH";
    public static final String KEY_BETA_PUSH = "BETA_PUSH";
    public static final String KEY_CLOUD_SEARCH = "CLOUD_SEARCH";
    public static final String KEY_DEFAULT_DICT_EN = "DEFAULT_DICT_EN";
    public static final String KEY_DEFAULT_DICT_JP = "DEFAULT_DICT_JP";
    public static final String KEY_DEFAULT_EN_BOOK = "DEFAULT_EN_BOOK";
    public static final String KEY_DEFAULT_JP_BOOK = "DEFAULT_JP_BOOK";
    public static final String KEY_FIX_PHONETIC_FONT = "FIX_PHONETIC_FONT";
    public static final String KEY_IS_PRAISE = "IS_PRAISE";
    public static final String KEY_JP_SEARCH_TYPE = "JP_SEARCH_TYPE";
    public static final String KEY_LANGUAGE = "LANGUAGE";
    public static final String KEY_LANGUAGE_FROM = "LANGUAGE_FROM";
    public static final String KEY_LANGUAGE_TO = "LANGUAGE_TO";
    public static final String KEY_LAUNCH_COUNT = "LAUNCH_COUNT";
    public static final String KEY_NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String KEY_OUTSIDE_QUERY = "OUTSIDE_QUERT";
    public static final String KEY_PHONETIC_TYPE = "PHONETIC_TYPE";
    public static final String KEY_SHOW_DICT_LOGO = "SHOE_DICT_LOGO";
    public static final String KEY_SHOW_KEYBOARD = "SHOW_KEYBOARD";
    public static final String KEY_SHOW_SEARCH_HISTORY_EXPLAIN = "SHOW_SEARCH_HISTORY_EXPLAIN";
    public static final String KEY_SHOW_SEARCH_HISTORY_TIMES = "SHOW_SEARCH_HISTORY_TIMES";
    public static final String KEY_SLOW_SOUND = "SLOW_SOUND";
    public static final String KEY_SLOW_SOUND_SPEED = "SLOW_SOUND_SPEED";
    public static final int VALUE_AUTO_COLLECT_MODE_CLICK = 1;
    public static final int VALUE_AUTO_COLLECT_MODE_QUERY = 0;
    public static final int VALUE_DICT_EN_BING = 3;
    public static final int VALUE_DICT_EN_CIBA = 2;
    public static final int VALUE_DICT_EN_DATONG = 0;
    public static final int VALUE_DICT_EN_DICT_CN = 4;
    public static final int VALUE_DICT_EN_YOUDAO = 1;
    public static final int VALUE_DICT_JP_DATONG = 0;
    public static final int VALUE_JP_SEARCH_TYPE_EXPLAIN = 3;
    public static final int VALUE_JP_SEARCH_TYPE_KANA = 1;
    public static final int VALUE_JP_SEARCH_TYPE_ROME_SOUND = 2;
    public static final int VALUE_JP_SEARCH_TYPE_WORD = 0;
    public static final int VALUE_LANGUAGE_ENGLISH = 0;
    public static final int VALUE_LANGUAGE_JAPANESE = 1;
    public static final int VALUE_PHONETIC_TYPE_UK = 1;
    public static final int VALUE_PHONETIC_TYPE_US = 0;
    private static Context context;

    public static void init(Context context2) {
        context = context2;
    }

    public static boolean isShowKeyboard() {
        return getBoolean(KEY_SHOW_KEYBOARD, false);
    }

    public static void setShowKeyboard(boolean isShow) {
        setBoolean(KEY_SHOW_KEYBOARD, isShow);
    }

    public static boolean isAllowedScreenShot() {
        return getBoolean(KEY_ALLOWED_SCREEN_SHOT, true);
    }

    public static void setAllowedScreenShot(boolean isAllowed) {
        setBoolean(KEY_ALLOWED_SCREEN_SHOT, isAllowed);
    }

    public static boolean isFixPhoneticFont() {
        return getBoolean(KEY_FIX_PHONETIC_FONT, false);
    }

    public static void setFixPhoneticFont(boolean isFix) {
        setBoolean(KEY_FIX_PHONETIC_FONT, isFix);
    }

    public static boolean isOutsideQuery() {
        return getBoolean(KEY_OUTSIDE_QUERY, false);
    }

    public static void setOutsideQuery(boolean isEnable) {
        setBoolean(KEY_OUTSIDE_QUERY, isEnable);
    }

    public static boolean isBack2Search() {
        return getBoolean(KEY_BACK_TO_SEARCH, false);
    }

    public static void setBack2Search(boolean isEnable) {
        setBoolean(KEY_BACK_TO_SEARCH, isEnable);
    }

    public static boolean isAutoCollect() {
        return getBoolean(KEY_AUTO_COLLECT, false);
    }

    public static void setAutoCollect(boolean isAutoCollect) {
        setBoolean(KEY_AUTO_COLLECT, isAutoCollect);
    }

    public static int getCollectMode() {
        return getInt(KEY_AUTO_COLLECT_MODE, 0);
    }

    public static void setCollectMode(int collectMode) {
        setInt(KEY_AUTO_COLLECT_MODE, collectMode);
    }

    public static boolean isAutoCollcectWhenClickQuery() {
        return getBoolean(KEY_AUTO_COLLECT_WHEN_CLICK_QUERY, false);
    }

    public static void setAutoCollcectWhenClickQuery(boolean isAuto) {
        setBoolean(KEY_AUTO_COLLECT_WHEN_CLICK_QUERY, isAuto);
    }

    public static boolean isAutoCollcectWhenOutsideQuery() {
        return getBoolean(KEY_AUTO_COLLECT_WHEN_OUTSIDE_QUERY, false);
    }

    public static void setAutoCollcectWhenOutsideQuery(boolean isAuto) {
        setBoolean(KEY_AUTO_COLLECT_WHEN_OUTSIDE_QUERY, isAuto);
    }

    public static int getDefaultBook() {
        if (getLanguage() == 0) {
            return getInt(KEY_DEFAULT_EN_BOOK, -1);
        }
        return getInt(KEY_DEFAULT_JP_BOOK, -1);
    }

    public static void setDefaultBook(int bookId) {
        if (getLanguage() == 0) {
            setInt(KEY_DEFAULT_EN_BOOK, bookId);
        } else {
            setInt(KEY_DEFAULT_JP_BOOK, bookId);
        }
    }

    public static boolean isAutoPlaySound() {
        return getBoolean(KEY_AUTO_PLAY_SOUND, false);
    }

    public static void setAutoPlaySound(boolean isAutoPlaySound) {
        setBoolean(KEY_AUTO_PLAY_SOUND, isAutoPlaySound);
    }

    public static boolean isSlowSound() {
        return getBoolean(KEY_SLOW_SOUND, Build.VERSION.SDK_INT >= 23);
    }

    public static void setSlowSound(boolean isSlowSound) {
        setBoolean(KEY_SLOW_SOUND, isSlowSound);
    }

    public static float getSlowSoundSpeed() {
        float f = getFloat(KEY_SLOW_SOUND_SPEED, 0.6f);
        if (f < 0.4f) {
            f = 0.4f;
        }
        if (f > 0.8f) {
            return 0.8f;
        }
        return f;
    }

    public static void setSlowSoundSpeed(float speed) {
        setFloat(KEY_SLOW_SOUND_SPEED, speed);
    }

    public static boolean isCloudSearch() {
        return getBoolean(KEY_CLOUD_SEARCH, true);
    }

    public static void setCloudSearch(boolean isCloudSearch) {
        setBoolean(KEY_CLOUD_SEARCH, isCloudSearch);
    }

    public static int getLanguage() {
        return getInt(KEY_LANGUAGE, 0);
    }

    public static void setLanguage(int value) {
        setInt(KEY_LANGUAGE, value);
    }

    public static int getDefaultDictJP() {
        return getInt(KEY_DEFAULT_DICT_JP, 0);
    }

    public static void setDefaultDictJP(int value) {
        setInt(KEY_DEFAULT_DICT_JP, value);
    }

    public static int getDefaultDictEN() {
        return getInt(KEY_DEFAULT_DICT_EN, 0);
    }

    public static void setDefaultDictEN(int value) {
        setInt(KEY_DEFAULT_DICT_EN, value);
    }

    public static void setPhoneticType(int type) {
        setInt(KEY_PHONETIC_TYPE, type);
    }

    public static int getPhoneticType() {
        return getInt(KEY_PHONETIC_TYPE, 0);
    }

    public static int getJPSearchType() {
        return getInt(KEY_JP_SEARCH_TYPE, 3);
    }

    public static void setJPSearchType(int value) {
        setInt(KEY_JP_SEARCH_TYPE, value);
    }

    public static void setShowDictLogo(boolean isShow) {
        setBoolean(KEY_SHOW_DICT_LOGO, isShow);
    }

    public static boolean isShowDictLogo() {
        return getBoolean(KEY_SHOW_DICT_LOGO, true);
    }

    public static void setShowSearchHistoryTimes(boolean isShow) {
        setBoolean(KEY_SHOW_SEARCH_HISTORY_TIMES, isShow);
    }

    public static boolean isShowSearchHistoryTimes() {
        return getBoolean(KEY_SHOW_SEARCH_HISTORY_TIMES, true);
    }

    public static void setShowSearchHistoryExplain(boolean isShow) {
        setBoolean(KEY_SHOW_SEARCH_HISTORY_EXPLAIN, isShow);
    }

    public static boolean isShowSearchHistoryExplain() {
        return getBoolean(KEY_SHOW_SEARCH_HISTORY_EXPLAIN, false);
    }

    public static void setBetaPush(boolean isBetaPush) {
        setBoolean(KEY_BETA_PUSH, isBetaPush);
    }

    public static boolean isBetaPush() {
        return getBoolean(KEY_BETA_PUSH, true);
    }

    public static void setLastNotificationId(String notificationId) {
        setStr(KEY_NOTIFICATION_ID, notificationId);
    }

    public static String getLastNotificationId() {
        return getStr(KEY_NOTIFICATION_ID, "");
    }

    public static void addLaunchCount() {
        setInt(KEY_LAUNCH_COUNT, getLaunchCount() + 1);
    }

    public static int getLaunchCount() {
        return getInt(KEY_LAUNCH_COUNT, 0);
    }

    public static void setIsPraise(boolean isPraise) {
        setBoolean(KEY_IS_PRAISE, isPraise);
    }

    public static boolean isPraise() {
        return getBoolean(KEY_IS_PRAISE, false);
    }

    public static int getLanguageFrom() {
        return getInt(KEY_LANGUAGE_FROM, -1);
    }

    public static void setLanguageFrom(int from) {
        setInt(KEY_LANGUAGE_FROM, from);
    }

    public static int getLanguageTo() {
        return getInt(KEY_LANGUAGE_TO, 0);
    }

    public static void setLanguageTo(int to) {
        setInt(KEY_LANGUAGE_TO, to);
    }

    public static String getStr(String key, String defValue) {
        return context.getSharedPreferences("SETTINGS", 0).getString(key, defValue);
    }

    public static void setStr(String key, String value) {
        context.getSharedPreferences("SETTINGS", 0).edit().putString(key, value).apply();
    }

    public static int getInt(String key, int defValue) {
        return context.getSharedPreferences("SETTINGS", 0).getInt(key, defValue);
    }

    public static void setInt(String key, int value) {
        context.getSharedPreferences("SETTINGS", 0).edit().putInt(key, value).apply();
    }

    public static float getFloat(String key, float defValue) {
        return context.getSharedPreferences("SETTINGS", 0).getFloat(key, defValue);
    }

    public static void setFloat(String key, float value) {
        context.getSharedPreferences("SETTINGS", 0).edit().putFloat(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean def) {
        return context.getSharedPreferences("SETTINGS", 0).getBoolean(key, def);
    }

    public static void setBoolean(String key, boolean value) {
        context.getSharedPreferences("SETTINGS", 0).edit().putBoolean(key, value).apply();
    }
}
