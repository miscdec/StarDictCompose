package com.datong.dict.module.dict.en.youdao.items.authority;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.databinding.FragmentYoudaoAuthorityBinding;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityItem;
import com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityListAdapter;
import com.datong.dict.widget.base.BaseRecyclerView;

import java.util.List;

/* loaded from: classes.dex */
public class AuthorityFragment extends BaseFragment implements YoudaoContract.AuthorityView {
    boolean isLoaded;
    YoudaoContract.Presenter persenter;

    FragmentYoudaoAuthorityBinding binding;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYoudaoAuthorityBinding.inflate(inflater, container, false);
        recyclerView = binding.listYoudaoAuthority;
        return binding.getRoot();
    }

    public static AuthorityFragment newInstance() {
        AuthorityFragment authorityFragment = new AuthorityFragment();
        authorityFragment.setContentView(R.layout.fragment_youdao_authority);
        authorityFragment.setTitle("权威例句");
        return authorityFragment;
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
        this.persenter.onlaodAuthority();
    }

    private void initRecyclerView() {
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setVisibility(View.GONE);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.AuthorityView
    public void setAuthorityListAdapter(List<AuthorityItem> authorityItemList) {
        this.recyclerView.setAdapter(new AuthorityListAdapter(getContext(), authorityItemList, this.persenter));
        this.recyclerView.setVisibility(View.VISIBLE);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }
}
