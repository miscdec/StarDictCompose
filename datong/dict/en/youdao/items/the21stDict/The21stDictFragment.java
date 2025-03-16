package com.datong.dict.module.dict.en.youdao.items.the21stDict;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.The21DictItem;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.The21stDictListAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class The21stDictFragment extends BaseFragment implements YoudaoContract.The21stDictView {
    boolean isLoaded;
    YoudaoContract.Presenter persenter;
    @BindView(R.id.list_youdao_the21stDict)
    RecyclerView recyclerView;

    @Override // com.datong.dict.base.BaseFragment
    public void handleEvents() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initVariables() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void onLoadData() {
    }

    public static The21stDictFragment newInstance() {
        The21stDictFragment the21stDictFragment = new The21stDictFragment();
        the21stDictFragment.setContentView(R.layout.fragment_youdao_21st);
        the21stDictFragment.setTitle("21世纪英汉词典");
        return the21stDictFragment;
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
        this.persenter.onloadThe21stDict();
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVisibility(8);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.The21stDictView
    public void setThe21stDictListAdapter(List<The21DictItem> the21DictList) {
        this.recyclerView.setAdapter(new The21stDictListAdapter(getContext(), the21DictList, this.persenter));
        this.recyclerView.setVisibility(0);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }
}
