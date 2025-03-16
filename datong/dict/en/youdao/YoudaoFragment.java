package com.datong.dict.module.dict.en.youdao;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.data.dictionary.en.YoudaoRepository;
import com.datong.dict.databinding.FragmentYoudaoBinding;
import com.datong.dict.module.dict.DictActivity;
import com.datong.dict.module.dict.en.youdao.items.authority.AuthorityFragment;
import com.datong.dict.module.dict.en.youdao.items.baseExplain.BaseExplainFragment;
import com.datong.dict.module.dict.en.youdao.items.bilingual.BilingualFragment;
import com.datong.dict.module.dict.en.youdao.items.collins.CollinsFragment;
import com.datong.dict.module.dict.en.youdao.items.discriminate.DiscriminateFragment;
import com.datong.dict.module.dict.en.youdao.items.originalSound.OriginalSoundFragment;
import com.datong.dict.module.dict.en.youdao.items.relatWord.RelatWordFragment;
import com.datong.dict.module.dict.en.youdao.items.synonym.SynonymFragment;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.The21stDictFragment;
import com.datong.dict.module.dict.en.youdao.items.wordGroup.WordGroupFragment;
import com.datong.dict.utils.AnimatorUtil;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.widget.MessageSnackbar;
import com.datong.dict.widget.WordSnackbar;
import com.datong.dict.widget.base.BasePagerAdapter;
import com.datong.dict.widget.base.BaseTabLayout;
import com.datong.dict.widget.base.BaseViewPager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class YoudaoFragment extends BaseFragment implements YoudaoContract.YoudaoView {

    FragmentYoudaoBinding binding;

    AppBarLayout appBarLayout;
    AuthorityFragment authorityFragment;
    BaseExplainFragment baseExplainFragment;
    BilingualFragment bilingualFragment;
    CollinsFragment collinsFragment;
    DiscriminateFragment discriminateFragment;
    List<BaseFragment> fragmentList;
    CircleImageView imgLogo;
    boolean isLoaded;
    OriginalSoundFragment originalSoundFragment;
    YoudaoContract.Presenter persenter;
    SwipeRefreshLayout refreshLayout;
    RelatWordFragment relatWordFragment;
    SynonymFragment synonymsFragment;
    BaseTabLayout tabLayout;
    The21stDictFragment the21stDictFragment;
    TextView tvDictName;
    BaseViewPager viewPager;
    String word;
    WordGroupFragment wordGroupFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentYoudaoBinding.inflate(inflater,container,false);
        appBarLayout = binding.appBarDictYoudao;
        imgLogo = binding.imgDictYoudaoLogo;
        tvDictName = binding.tvDictYoudaoName;
        refreshLayout = binding.refeshDictYoudao;
        tabLayout = binding.tabLayoutDictYoudao;
        viewPager = binding.pagerDictYoudao;
        return binding.getRoot();
    }

    public static YoudaoFragment newInstance(String word) {
        YoudaoFragment youdaoFragment = new YoudaoFragment();
        youdaoFragment.setContentView(R.layout.fragment_youdao);
        youdaoFragment.word = word;
        return youdaoFragment;
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initVariables() {
        initFragmentItems();
        initFragmentList();
        initPersenter();
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initViews(Bundle savedInstanceState) {
        initRefreshLayout();
        initTabLayout();
        initViewPager();
        this.appBarLayout.setExpanded(SettingUtil.isShowDictLogo());
    }

    @Override // com.datong.dict.base.BaseFragment
    public void handleEvents() {
        handleAppBarEvents();
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden || this.isLoaded) {
            return;
        }
        this.isLoaded = true;
        this.persenter.onloadPagerTitles(this.fragmentList);
    }

    private void initFragmentItems() {
        this.baseExplainFragment = BaseExplainFragment.newInstance();
        this.collinsFragment = CollinsFragment.newInstance();
        this.the21stDictFragment = The21stDictFragment.newInstance();
        this.wordGroupFragment = WordGroupFragment.newInstance();
        this.synonymsFragment = SynonymFragment.newInstance();
        this.relatWordFragment = RelatWordFragment.newInstance();
        this.discriminateFragment = DiscriminateFragment.newInstance();
        this.bilingualFragment = BilingualFragment.newInstance();
        this.originalSoundFragment = OriginalSoundFragment.newInstance();
        this.authorityFragment = AuthorityFragment.newInstance();
    }

    private void initFragmentList() {
        ArrayList<BaseFragment> arrayList = new ArrayList<>();
        this.fragmentList = arrayList;
        arrayList.add(this.baseExplainFragment);
        this.fragmentList.add(this.collinsFragment);
        this.fragmentList.add(this.the21stDictFragment);
        this.fragmentList.add(this.wordGroupFragment);
        this.fragmentList.add(this.synonymsFragment);
        this.fragmentList.add(this.relatWordFragment);
        this.fragmentList.add(this.discriminateFragment);
        this.fragmentList.add(this.bilingualFragment);
        this.fragmentList.add(this.originalSoundFragment);
        this.fragmentList.add(this.authorityFragment);
    }

    private void initPersenter() {
        new YoudaoPresenter(this.word, this, this.baseExplainFragment, this.collinsFragment, this.the21stDictFragment, this.wordGroupFragment, this.synonymsFragment, this.relatWordFragment, this.discriminateFragment, this.bilingualFragment, this.originalSoundFragment, this.authorityFragment, YoudaoRepository.getInstance(getContext()));
    }

    private void initRefreshLayout() {
        this.refreshLayout.setColorSchemeColors(getResources().getColor(R.color.red_datong));
        this.refreshLayout.setEnabled(false);
    }

    private void initViewPager() {
        this.viewPager.setOffscreenPageLimit(this.fragmentList.size());
    }

    private void initTabLayout() {
        this.tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        this.tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.red_datong));
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    private void handleAppBarEvents() {
        // from class: com.datong.dict.module.dict.en.youdao.YoudaoFragment$$ExternalSyntheticLambda1
// com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        this.appBarLayout.addOnOffsetChangedListener((appBarLayout, i) -> {
            float f = -(((i * 1.0f) / appBarLayout.getTotalScrollRange()));
            setAppBarElevation(f);
            setAppBarScrollEffect(f);
        });
    }

    /* synthetic */

    private void setAppBarElevation(float value) {
        if (Build.VERSION.SDK_INT <= 21) {
            return;
        }
        if (value > 0.85f) {
            this.appBarLayout.setElevation(getResources().getDimension(R.dimen.y15));
        } else {
            this.appBarLayout.setElevation(0.0f);
        }
    }

    private void setAppBarScrollEffect(float value) {
        float f = 1.0f - (1.5f * value);
        this.imgLogo.setAlpha(f);
        this.tvDictName.setAlpha(f);
        if (value > 0.9f) {
            if (this.toolbar.getTitleVisibility() == 0) {
                return;
            }
            this.toolbar.setTitleVisibility(0);
            AnimatorUtil.setObjectAnimator(this.toolbar.tvTitle, "Alpha", 200L, null, 0.0f, 1.0f);
        } else if (this.toolbar.getTitleVisibility() == 8) {
        } else {
            this.toolbar.setTitleVisibility(8);
            AnimatorUtil.setObjectAnimator(this.toolbar.tvTitle, "Alpha", 200L, null, 1.0f, 0.0f);
        }
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void showRefreshView() {
        if (this.refreshLayout.isRefreshing()) {
            return;
        }
        this.refreshLayout.setRefreshing(true);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void hideRefreshView() {
        if (this.refreshLayout.isRefreshing()) {
            this.refreshLayout.setRefreshing(false);
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    /* renamed from: showDictSelector */

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void hideDictSelector() {
        try {
            ((DictActivity) getActivity()).hideDictSelector();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void updatePager() {
        this.viewPager.setAdapter(new BasePagerAdapter(this.fragmentList, getChildFragmentManager()));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void showWordSnackbar(String word) {
        hideDictSelector();
        WordSnackbar.with(getView()).query(word).show();
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public void showMessageSnackbar(String msg) {
        hideDictSelector();
        MessageSnackbar.with(getView()).message(msg).duration(-1).onDismiss(new MessageSnackbar.OnDismissCallback() { // from class: com.datong.dict.module.dict.en.youdao.YoudaoFragment$$ExternalSyntheticLambda0
            @Override // com.datong.dict.widget.MessageSnackbar.OnDismissCallback
            public final void callback() {
                try {
                    ((DictActivity) getActivity()).lambda$showMessageSnackbar$8$DictActivity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).show();
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
    public View getRootView() {
        return getView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.fragmentList.clear();
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.persenter = presenter;
    }
}
