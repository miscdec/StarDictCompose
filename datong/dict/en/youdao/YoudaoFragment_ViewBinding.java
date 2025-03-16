package com.datong.dict.module.dict.en.youdao;

import android.view.View;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.widget.base.BaseTabLayout;
import com.datong.dict.widget.base.BaseViewPager;
import com.google.android.material.appbar.AppBarLayout;
import de.hdodenhof.circleimageview.CircleImageView;

/* loaded from: classes.dex */
public class YoudaoFragment_ViewBinding implements Unbinder {
    private YoudaoFragment target;

    public YoudaoFragment_ViewBinding(YoudaoFragment target, View source) {
        this.target = target;
        target.appBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(source, R.id.appBar_dict_youdao, "field 'appBarLayout'", AppBarLayout.class);
        target.tabLayout = (BaseTabLayout) Utils.findRequiredViewAsType(source, R.id.tabLayout_dict_youdao, "field 'tabLayout'", BaseTabLayout.class);
        target.imgLogo = (CircleImageView) Utils.findRequiredViewAsType(source, R.id.img_dict_youdao_logo, "field 'imgLogo'", CircleImageView.class);
        target.tvDictName = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_dict_youdao_name, "field 'tvDictName'", TextView.class);
        target.refreshLayout = (SwipeRefreshLayout) Utils.findRequiredViewAsType(source, R.id.refesh_dict_youdao, "field 'refreshLayout'", SwipeRefreshLayout.class);
        target.viewPager = (BaseViewPager) Utils.findRequiredViewAsType(source, R.id.pager_dict_youdao, "field 'viewPager'", BaseViewPager.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        YoudaoFragment youdaoFragment = this.target;
        if (youdaoFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        youdaoFragment.appBarLayout = null;
        youdaoFragment.tabLayout = null;
        youdaoFragment.imgLogo = null;
        youdaoFragment.tvDictName = null;
        youdaoFragment.refreshLayout = null;
        youdaoFragment.viewPager = null;
    }
}
