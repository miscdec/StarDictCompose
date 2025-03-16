package com.datong.dict.module.dict.en.youdao.items.bilingual;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualItem;
import com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualListAdapter;
import com.datong.dict.widget.base.BaseRecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class BilingualFragment extends BaseFragment implements YoudaoContract.BilingualView {
    List<BilingualItem> bilingualItems;
    boolean isLoaded = false;
    YoudaoContract.Presenter persenter;
    @BindView(R.id.list_youdao_bilingual)
    BaseRecyclerView recyclerView;

    @Override // com.datong.dict.base.BaseFragment
    public void handleEvents() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void onLoadData() {
    }

    public static BilingualFragment newInstance() {
        BilingualFragment bilingualFragment = new BilingualFragment();
        bilingualFragment.setContentView(R.layout.fragment_youdao_bilingual);
        bilingualFragment.setTitle("双语例句");
        return bilingualFragment;
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initVariables() {
        this.bilingualItems = new ArrayList();
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initViews(Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser || this.isLoaded) {
            return;
        }
        this.isLoaded = true;
        this.persenter.onlaodBilingual();
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BilingualView
    public void setBilingualListAdapter(List<BilingualItem> bilingualItemList) {
        this.recyclerView.setAdapter(new BilingualListAdapter(getContext(), bilingualItemList, this.persenter));
        this.recyclerView.setVisibility(0);
    }
}
