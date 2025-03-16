package com.datong.dict.module.dict.en.youdao.items.collins;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsItem;
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsListAdapter;
import com.datong.dict.widget.base.BaseRecyclerView;
import java.util.List;

/* loaded from: classes.dex */
public class CollinsFragment extends BaseFragment implements YoudaoContract.CollinsView {
    boolean isLoaded;
    YoudaoContract.Presenter persenter;
    @BindView(R.id.list_youdao_collins)
    BaseRecyclerView recyclerView;

    @Override // com.datong.dict.base.BaseFragment
    public void handleEvents() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initVariables() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void onLoadData() {
    }

    public static CollinsFragment newInstance() {
        CollinsFragment collinsFragment = new CollinsFragment();
        collinsFragment.setContentView(R.layout.fragment_youdao_collins);
        collinsFragment.setTitle("柯林斯英汉词典");
        return collinsFragment;
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
        this.persenter.onloadCollins();
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVisibility(8);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.CollinsView
    public void setCollinsListAdapter(List<CollinsItem> collinsItemList) {
        this.recyclerView.setAdapter(new CollinsListAdapter(getContext(), collinsItemList, this.persenter));
        this.recyclerView.setVisibility(0);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }
}
