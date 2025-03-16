package com.datong.dict.module.dict.en.youdao.items.discriminate;

import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateItem;
import com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateListAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class DiscriminateFragment extends BaseFragment implements YoudaoContract.DiscriminateView {
    @BindView(R.id.card_youdao_discriminate)
    CardView cardView;
    YoudaoContract.Presenter presenter;
    @BindView(R.id.list_youdao_discriminate)
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

    public static DiscriminateFragment newInstance() {
        DiscriminateFragment discriminateFragment = new DiscriminateFragment();
        discriminateFragment.setContentView(R.layout.fragment_youdao_discriminate);
        discriminateFragment.setTitle("词语辨析");
        return discriminateFragment;
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
            this.presenter.onloadDiscriminate();
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

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.DiscriminateView
    public void setDiscriminateListAdapter(List<DiscriminateItem> discriminateItemList) {
        this.recyclerView.setAdapter(new DiscriminateListAdapter(getContext(), discriminateItemList, this.presenter));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.DiscriminateView
    public void showDiscriminateCard() {
        this.cardView.setVisibility(0);
    }
}
