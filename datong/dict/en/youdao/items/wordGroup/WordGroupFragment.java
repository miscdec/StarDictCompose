package com.datong.dict.module.dict.en.youdao.items.wordGroup;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupItem;
import com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupListAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class WordGroupFragment extends BaseFragment implements YoudaoContract.WordGroupView {
    @BindView(R.id.card_youdao_wordGroup)
    CardView cardView;
    YoudaoContract.Presenter presenter;
    @BindView(R.id.list_youdao_wordGroup)
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

    public static WordGroupFragment newInstance() {
        WordGroupFragment wordGroupFragment = new WordGroupFragment();
        wordGroupFragment.setTitle("词组短语");
        wordGroupFragment.setContentView(R.layout.fragment_youdao_word_group);
        return wordGroupFragment;
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initViews(Bundle savedInstanceState) {
        initCardView();
        initRecyclerView();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && this.recyclerView.getAdapter() == null) {
            this.presenter.onloadWordGroup();
        }
    }

    private void initCardView() {
        this.cardView.setVisibility(8);
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setHasFixedSize(true);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.WordGroupView
    public void setWordGroupListAdapter(List<WordGroupItem> groupItemList) {
        this.recyclerView.setAdapter(new WordGroupListAdapter(getContext(), groupItemList, this.presenter));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.WordGroupView
    public void showWordGroupCard() {
        this.cardView.setVisibility(0);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
