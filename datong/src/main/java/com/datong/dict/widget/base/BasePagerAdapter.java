package com.datong.dict.widget.base;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.datong.dict.base.BaseFragment;
import java.util.List;

/* loaded from: classes.dex */
public class BasePagerAdapter extends FragmentPagerAdapter {
    List<BaseFragment> fragmentList;

    public BasePagerAdapter(List<BaseFragment> fragmentList, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override // androidx.fragment.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.fragmentList.get(position).getTitle();
    }
}
