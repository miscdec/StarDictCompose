package com.datong.dict.utils;

/* loaded from: classes.dex */
public class SecretUtil {
    public static String decrypt(String str, String key) {
        String str2 = str;
        for (int i = 0; i < key.length(); i++) {
            try {
                str2 = reverse(str2);
            } catch (Exception unused) {
                return str;
            }
        }
        return flipStr(str2).replace("#", "");
    }

    private static String flipStr(String s) {
        char[] charArray = s.toCharArray();
        char[] cArr = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            cArr[i] = charArray[(charArray.length - 1) - i];
        }
        return String.valueOf(cArr);
    }

    private static String reverse(String s) {
        char[] charArray = s.toCharArray();
        int length = charArray.length / 2;
        char[] cArr = new char[length];
        char[] cArr2 = new char[charArray.length / 2];
        for (int i = 0; i < charArray.length; i++) {
            if (i % 2 == 0) {
                cArr[(length - 1) - (i / 2)] = charArray[i];
            } else {
                cArr2[(i - 1) / 2] = charArray[i];
            }
        }
        return String.valueOf(cArr) + String.valueOf(cArr2);
    }
}
