package com.datong.dict.base;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.datong.dict.databinding.StatusbarBinding;
import com.datong.dict.databinding.ToolbarBinding;
import com.datong.dict.module.dict.DictActivity;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.widget.WordSnackbar;
import com.datong.dict.widget.base.StatusBar;
import com.datong.dict.widget.base.Toolbar;

/* loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity {

    public StatusBar statusBar;

    public Toolbar toolbar;

    public abstract void handleEvents();

    public abstract void initVariables();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void onLoadData();



    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBar = StatusbarBinding.inflate(getLayoutInflater()).statusbar;
        toolbar = ToolbarBinding.inflate(getLayoutInflater()).toolbar;
        setNoActionBar();
        setTranslucentStatusBar();
        setIsAllowScreenShot();
        initVariables();
        initViews(savedInstanceState);
        handleEvents();
        onLoadData();
        ActivityManager.getInstance().add(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        WordSnackbar.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().remove(this);
    }

    private void setTranslucentStatusBar() {
        try {
            Window window = getWindow();
            window.addFlags(128);
            if (Build.VERSION.SDK_INT >= 21) {
                window.addFlags(Integer.MIN_VALUE);
                window.getDecorView().setSystemUiVisibility(9216);
                window.setStatusBarColor(0);
            } else {
                window.setFlags(1024, 1024);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNoActionBar() {
        try {
            getActionBar().hide();
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setIsAllowScreenShot() {
        if (!(this instanceof DictActivity) || SettingUtil.isAllowedScreenShot()) {
            return;
        }
        getWindow().setFlags(8192, 8192);
    }
}
