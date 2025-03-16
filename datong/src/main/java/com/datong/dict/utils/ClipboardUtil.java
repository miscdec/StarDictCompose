package com.datong.dict.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/* loaded from: classes.dex */
public class ClipboardUtil {
    public static void copy(Context context, String content) {
        ((ClipboardManager) context.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("text/plain", content));
    }
}
