package com.datong.dict.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.datong.dict.utils.FileUtil;

/* loaded from: classes.dex */
public class BaseAppLifecycleListener implements Application.ActivityLifecycleCallbacks {
    int count = 0;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull Activity activity) {
        this.count++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
        AppConstant.IS_BACKGROUND = false;
        // from class: com.datong.dict.base.BaseAppLifecycleListener$$ExternalSyntheticLambda0
// java.lang.Runnable
        new Thread(FileUtil::decryptUserInfo).start();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
        int i = this.count - 1;
        this.count = i;
        if (i == 0) {
            AppConstant.IS_BACKGROUND = true;
            // from class: com.datong.dict.base.BaseAppLifecycleListener$$ExternalSyntheticLambda1
// java.lang.Runnable
            new Thread(FileUtil::encryptUserInfo).start();
        }
    }
}
