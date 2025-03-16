package com.datong.dict.module.dict.en.youdao.items.synonym;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.synonym.adapter.SynonymItem;
import com.datong.dict.module.dict.en.youdao.items.synonym.adapter.SynonymListAdapter;
import com.datong.dict.widget.base.BaseRecyclerView;
import java.util.List;

/* loaded from: classes.dex */
public class SynonymFragment extends BaseFragment implements YoudaoContract.SynonymView {
    YoudaoContract.Presenter presenter;
    @BindView(R.id.list_youdao_synonym)
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

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.SynonymView
    public void showSynonymCard() {
    }

    public static SynonymFragment newInstance() {
        SynonymFragment synonymFragment = new SynonymFragment();
        synonymFragment.setContentView(R.layout.fragment_youdao_synonyms);
        synonymFragment.setTitle("同近义词");
        return synonymFragment;
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initViews(Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.recyclerView.getAdapter() == null) {
            this.presenter.onloadSynonym();
        }
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.SynonymView
    public void setSynonymListAdapter(List<SynonymItem> synonymItemList) {
        this.recyclerView.setAdapter(new SynonymListAdapter(getContext(), synonymItemList, this.presenter));
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
