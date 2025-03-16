package com.datong.dict.base;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.webkit.WebView;
import com.datong.dict.BuildConfig;
import com.datong.dict.utils.NetworkUtil;
import com.datong.dict.utils.PathUtil;
import com.datong.dict.utils.SettingUtil;

/* loaded from: classes.dex */
public class BaseApplication extends Application {
    private int copyFlag = 0;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        initUtils();
        registerActivityLifecycleCallbacks(new BaseAppLifecycleListener());
        setOutsideQueryListener();
        initWebViewDataPath();
    }

    private void initWebViewDataPath() {
        if (Build.VERSION.SDK_INT >= 28 && !BuildConfig.APPLICATION_ID.equals(getProcessName())) {
            WebView.setDataDirectorySuffix(BuildConfig.APPLICATION_ID);
        }
    }

    private void initUtils() {
        NetworkUtil.init(this);
        SettingUtil.init(this);
//        DictKeyUtil.init(this);
        PathUtil.init(this);
//        LogUtils.init(this);
    }





    private void setOutsideQueryListener() {
        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.addPrimaryClipChangedListener(new ClipboardManager.OnPrimaryClipChangedListener() { // from class: com.datong.dict.base.BaseApplication$$ExternalSyntheticLambda0
            @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
            public final void onPrimaryClipChanged() {
                int i = BaseApplication.this.copyFlag + 1;
                if (i > 1) {
                    BaseApplication.this.copyFlag = 0;
                    return;
                }
                BaseApplication.this.copyFlag = 0;
                if (AppConstant.IS_BACKGROUND && SettingUtil.isOutsideQuery()) {
                    try {
                        Intent intent = new Intent(getBaseContext(), OutsideQueryActivity.class);
                        intent.putExtra("word", clipboardManager.getPrimaryClip().getItemAt(0).getText().toString().trim());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* synthetic */
}
