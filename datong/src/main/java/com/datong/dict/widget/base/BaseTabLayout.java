package com.datong.dict.widget.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;
import com.google.android.material.tabs.TabLayout;

/* loaded from: classes.dex */
public class BaseTabLayout extends TabLayout {
    public BaseTabLayout(Context context) {
        super(context);
        init();
    }

    public BaseTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
//        setTabTextColors(-12303292, ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // android.view.View
    public void setZ(float z) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setZ(z);
        }
    }
}
