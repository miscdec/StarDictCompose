package com.datong.dict.module.dict.en.youdao.items.originalSound;

import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.originalSound.adapter.OriginalSoundItem;
import com.datong.dict.module.dict.en.youdao.items.originalSound.adapter.OriginalSoundListAdapter;
import com.datong.dict.widget.base.BaseRecyclerView;
import java.util.List;

/* loaded from: classes.dex */
public class OriginalSoundFragment extends BaseFragment implements YoudaoContract.OriginalSoundView {
    boolean isLoaded;
    YoudaoContract.Presenter persenter;
    @BindView(R.id.list_youdao_originaSound)
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

    public static OriginalSoundFragment newInstance() {
        OriginalSoundFragment originalSoundFragment = new OriginalSoundFragment();
        originalSoundFragment.setContentView(R.layout.fragment_youdao_original_sound);
        originalSoundFragment.setTitle("原声例句");
        return originalSoundFragment;
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
        this.persenter.onlaodOriginalSound();
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.OriginalSoundView
    public void setOriginalSoundListAdapter(List<OriginalSoundItem> originalSoundItemList) {
        this.recyclerView.setAdapter(new OriginalSoundListAdapter(getContext(), originalSoundItemList, this.persenter));
        this.recyclerView.setVisibility(0);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }
}
