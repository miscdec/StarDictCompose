package com.datong.dict.widget.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.datong.dict.R;
import com.datong.dict.base.BaseActivity;
import com.datong.dict.databinding.ToolbarBinding;
import com.datong.dict.databinding.ToolbarContentBinding;

/* loaded from: classes.dex */
public class Toolbar extends RelativeLayout {
    Context context;

    ToolbarContentBinding binding;
    public ImageView imgAction1;

    public ImageView imgAction2;

    public ImageView imgBack;

    public TextView menuAnchor;

    public TextView tvTitle;

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        binding = ToolbarContentBinding.inflate(LayoutInflater.from(context), this, true);
        imgAction1 = binding.imgToolbarAction1;
        imgAction2 = binding.imgToolbarAction2;
        imgBack = binding.imgToolbarBack;
        menuAnchor = binding.toolbarMenuAnchor;
        tvTitle = binding.tvToolbarTitle;
        imgBack.setOnClickListener(view ->
                this.finishActivity()
        );
        init();
    }

    private void init() {
        setTitle("");
        setIconColor(-1);
        setTitleColor(-1);
        setBgColor(0);
        setAction1Visibility(true);
        setAction2Visibility(true);
    }

    public void setIconColor(int color) {
        this.imgBack.setColorFilter(color);
        this.imgAction2.setColorFilter(color);
        this.imgAction1.setColorFilter(color);
    }

    public void setIconColor(ImageView img, int color) {
        img.setColorFilter(color);
    }

    public void setIcon(ImageView imgView, int resid) {
        imgView.setImageResource(resid);
        imgView.setClickable(true);
    }

    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setTitleColor(int color) {
        this.tvTitle.setTextColor(color);
    }

    public void setTitleVisibility(int visibility) {
        this.tvTitle.setVisibility(visibility);
    }

    public int getTitleVisibility() {
        return this.tvTitle.getVisibility();
    }

    public void setBgColor(int color) {
        setBackgroundColor(color);
    }

    public void setAction1Visibility(boolean visibility) {
        this.imgAction1.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    public void setAction2Visibility(boolean visibility) {
        this.imgAction2.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    @Override // android.view.View
    public void setZ(float z) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setZ(z);
        }
    }

    @Override // android.view.View
    public void setElevation(float elevation) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(elevation);
        }
    }

    public void finishActivity() {
        ((BaseActivity) this.context).finish();
    }
}
