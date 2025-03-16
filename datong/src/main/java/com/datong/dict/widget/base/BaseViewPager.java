package com.datong.dict.widget.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

/* loaded from: classes.dex */
public class BaseViewPager extends ViewPager {
    private boolean isScrollEnable;

    public BaseViewPager(Context context) {
        super(context);
        this.isScrollEnable = true;
        init();
    }

    public BaseViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isScrollEnable = true;
        init();
    }

    public void init() {
        setOverScrollMode(2);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.isScrollEnable) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.isScrollEnable) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setScrollEnable(boolean scrollEnable) {
        this.isScrollEnable = scrollEnable;
    }
}
