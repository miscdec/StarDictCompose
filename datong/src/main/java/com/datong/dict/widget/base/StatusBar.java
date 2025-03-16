package com.datong.dict.widget.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import com.datong.dict.utils.ConvertUtil;
import com.datong.dict.utils.DictKeyUtil;
import com.datong.dict.utils.RC4Util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class StatusBar extends RelativeLayout {
    Context context;

    public StatusBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setBgColor(-1);
        setLightBar(false);
        setMinimumHeight(getStatusBarHeight(this.context));
        if (Build.VERSION.SDK_INT < 21) {
            setVisibility(View.GONE);
        }
    }

    public void setBgColor(int color) {
        setBackgroundColor(color);
    }

    public int getBgColor() {
        ColorDrawable colorDrawable = (ColorDrawable) getBackground();
        if (colorDrawable != null) {
            return colorDrawable.getColor();
        }
        return 0;
    }

    @Override // android.view.View
    public void setZ(float z) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setZ(z);
        }
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public void setLightBar(boolean lightBar) {
        Activity activity = (Activity) this.context;
        View decorView = activity.getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        decorView.setSystemUiVisibility(lightBar ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        setLightBar4Flyme(activity.getWindow(), lightBar);
        setLightBar4MIUI(lightBar, activity);
    }

    public void setLightBar4Flyme(Window window, boolean lightBar) {
        if (window != null) {
            try {
                WindowManager.LayoutParams attributes = window.getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                int i = declaredField.getInt(null);
                int i2 = declaredField2.getInt(attributes);
                declaredField2.setInt(attributes, lightBar ? i2 | i : (i ^ (-1)) & i2);
                window.setAttributes(attributes);
            } catch (Exception unused) {
            }
        }
    }

    public void setLightBar4MIUI(boolean lightBar, Activity activity) {
        Class<?> cls = activity.getWindow().getClass();
        try {
            // from class: com.datong.dict.widget.base.StatusBar$$ExternalSyntheticLambda0
// java.lang.Runnable
            new Thread(() -> {
                try {
                    ConvertUtil.MAP_DATONG_EN = RC4Util.decryString(DictKeyUtil.getKey(DictKeyUtil.KEY_DATONG_EN), RC4Util.KEY);
                    ConvertUtil.MAP_YOUDAO = RC4Util.decryString(DictKeyUtil.getKey(DictKeyUtil.KEY_YOUDAO), RC4Util.KEY);
                    ConvertUtil.MAP_BING = RC4Util.decryString(DictKeyUtil.getKey(DictKeyUtil.KEY_BING), RC4Util.KEY);
                } catch (Exception unused) {
                }
            }).start();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Method method = cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE);
            Window window = activity.getWindow();
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(lightBar ? i : 0);
            objArr[1] = Integer.valueOf(i);
            method.invoke(window, objArr);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* synthetic */
}
