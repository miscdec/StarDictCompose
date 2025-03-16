package com.datong.dict.module.dict;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datong.dict.R;
import com.datong.dict.base.BaseActivity;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.databinding.ActivityDictBinding;
import com.datong.dict.module.dict.adapter.SelectorItem;
import com.datong.dict.module.dict.adapter.SelectorListAdapter;
import com.datong.dict.utils.AnimatorUtil;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.utils.SoundUtil;
import com.datong.dict.widget.Menu;
import com.datong.dict.widget.MessageSnackbar;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DictActivity extends BaseActivity implements DictContract.View {

    ActivityDictBinding binding;
//    private BingFragment bingFragment;
//    private CibaFragment cibaFragment;
    private BaseFragment currentFragment;
//    private DatongEnFragment datongEnFragment;
//    private DatongJpFragment datongJpFragment;
//    private DictCnFragment dictCnFragment;
    private List<BaseFragment> fragments;


    FrameLayout frameLayout;
    private String language;
    private BaseFragment lastFragment;

    RecyclerView listDictSelector;
    private DictContract.Presenter presenter;
    RelativeLayout rootView;
    private List<SelectorItem> selectorItems;
    private SelectorListAdapter selectorListAdapter;
    private String word;
    private String wordId;
    private YoudaoFragment youdaoFragment;
    private boolean isSelectorShowing = true;
    private boolean isSelectorMoving = false;

    @Override // com.datong.dict.module.dict.DictContract.View
    public Context getContext() {
        return this;
    }

    @Override // com.datong.dict.base.BaseActivity
    public void initVariables() {
        new DictPresenter(this);
        initVarsFromIntent();
        initFragments();
        this.selectorItems = new ArrayList<>();
        this.selectorListAdapter = new SelectorListAdapter(this, this.selectorItems);
    }

    @Override // com.datong.dict.base.BaseActivity
    public void initViews(Bundle savedInstanceState) {
        binding  = ActivityDictBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        frameLayout = binding.frameDictContainer;
        listDictSelector = binding.listDictSelector;
        rootView = binding.relatDictRootView;
        initStatusBar();
        initToolBar();
        initDictSelector();
        setDictSelectorColor(Color.parseColor("#e79686"));
        if (this.language.equals("jp")) {
            this.listDictSelector.setVisibility(View.GONE);
        }
    }

    @Override // com.datong.dict.base.BaseActivity
    public void handleEvents() {
        handleOnBackClick();
        handleCollectClick();
        handleCollectLongClick();
        handleOnSelectorItemClick();
    }

    @Override // com.datong.dict.base.BaseActivity
    public void onLoadData() {
        getSupportFragmentManager()
                .beginTransaction()
//                .add(R.id.frame_dict_container, this.datongEnFragment)
                .add(R.id.frame_dict_container, this.youdaoFragment)
//                .add(R.id.frame_dict_container, this.cibaFragment)
//                .add(R.id.frame_dict_container, this.bingFragment)
//                .add(R.id.frame_dict_container, this.dictCnFragment)
//                .add(R.id.frame_dict_container, this.datongJpFragment)
//                .hide(this.datongEnFragment)
                .hide(this.youdaoFragment)
//                .hide(this.cibaFragment)
//                .hide(this.bingFragment)
//                .hide(this.dictCnFragment)
//                .hide(this.datongJpFragment)
                .commit();
        this.presenter.setCurrentFragment(this.language);
        showFragment();
        this.presenter.onloadSelectorItems();
        this.presenter.addSearchHistory(this.word, this.wordId);
        this.presenter.autoPlaySound(this.word);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.presenter.collectWhenQuery(this.word, this.wordId);
        this.presenter.updateCollectState();
    }

    private void initVarsFromIntent() {
        this.wordId = getIntent().getStringExtra("wordId");
        this.word = getIntent().getStringExtra("word");
        this.language = getIntent().getStringExtra("language");
    }

    private void initFragments() {
//        this.datongEnFragment = DatongEnFragment.newInstance(this.word);
        this.youdaoFragment = YoudaoFragment.newInstance(this.word);
//        this.cibaFragment = CibaFragment.newInstance(this.word);
//        this.bingFragment = BingFragment.newInstance(this.word);
//        this.dictCnFragment = DictCnFragment.newInstance(this.word);
//        this.datongJpFragment = DatongJpFragment.newInstance(this.wordId);
        ArrayList<BaseFragment> arrayList = new ArrayList<>();
        arrayList.add(this.youdaoFragment);
//        arrayList.add(this.cibaFragment);
//        arrayList.add(this.bingFragment);
//        arrayList.add(this.datongEnFragment);
//        arrayList.add(this.dictCnFragment);
        this.fragments = arrayList;
    }

    private void initStatusBar() {

    }

    private void initToolBar() {
    }

    private void initDictSelector() {
        this.listDictSelector.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        this.listDictSelector.setAdapter(this.selectorListAdapter);
    }

    private void handleOnBackClick() {
        if (SettingUtil.isBack2Search()) {
            // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda3
// android.view.View.OnClickListener
//            this.toolbar.imgBack.setOnClickListener((View.OnClickListener) view ->
//                    ActivityManager.getInstance().removeAllDictActivity()
//            );
        }
    }

    private void handleCollectClick() {
        // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda1
// android.view.View.OnClickListener
        this.toolbar.imgAction2.setOnClickListener(view -> DictActivity.this.presenter.collectWhenClick(DictActivity.this.word, DictActivity.this.wordId));
    }

    /* synthetic */

    private void handleCollectLongClick() {
        // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda4
// android.view.View.OnLongClickListener
//        this.toolbar.imgAction2.setOnLongClickListener((View.OnLongClickListener) view ->
//                DictActivity.this.lambda$handleCollectLongClick$4$DictActivity(view)
//        );
    }

    /* synthetic */

    /* synthetic */

    private void handleOnSelectorItemClick() {
        // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda5
// com.datong.dict.module.dict.adapter.SelectorListAdapter.OnItemClickCallback
        this.selectorListAdapter.setOnItemClickCallback((i, selectorItem) ->
                DictActivity.this.presenter.handleOnSelectorItemClick(selectorItem)
        );
    }

    /* synthetic */

    @Override // com.datong.dict.module.dict.DictContract.View
    public void setToolbarTitle(String title) {
        this.toolbar.setTitle(title);
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void setDictSelectorColor(int color) {
        GradientDrawable gradientDrawable = (GradientDrawable) this.listDictSelector.getBackground();
        gradientDrawable.mutate();
        gradientDrawable.setColor(color);
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void setCollectState(boolean isCollected) {
        if (isCollected) {
            this.toolbar.setIcon(this.toolbar.imgAction2, R.drawable.ic_star_full);
            this.toolbar.setIconColor(this.toolbar.imgAction2, Color.parseColor("#faa03b"));
            return;
        }
        this.toolbar.setIcon(this.toolbar.imgAction2, R.drawable.ic_star_null);
        this.toolbar.setIconColor(this.toolbar.imgAction2, ViewCompat.MEASURED_STATE_MASK);
    }

//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void setCurrentFragmentIsDatongEn() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.datongEnFragment;
//    }
//
//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void setCurrentFragmentIsDatongJp() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.datongJpFragment;
//    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void setCurrentFragmentIsYoudao() {
        this.lastFragment = this.currentFragment;
        this.currentFragment = this.youdaoFragment;
    }

//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void setCurrentFragmentIsCiba() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.cibaFragment;
//    }
//
//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void setCurrentFragmentIsBing() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.bingFragment;
//    }

//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void setCurrentFragmentIsDictCn() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.dictCnFragment;
//    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void showDictMenu() {
        final String[] menuItems = this.presenter.getMenuItems(this.currentFragment);
        // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda6
// com.datong.dict.widget.Menu.OnItemClickCallback
        Menu.with(this).anchor(this.toolbar.menuAnchor).items(menuItems).click(i -> DictActivity.this.presenter.handleMenuItemClick(menuItems[i])).show();
    }

    @Override
    public void showDictSelector() {

    }

    /* synthetic */

//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void showDatongEnFragment() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.datongEnFragment;
//        showFragment();
//    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void showYoudaoFragment() {
        this.lastFragment = this.currentFragment;
        this.currentFragment = this.youdaoFragment;
        showFragment();
    }

//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void showCibaFragment() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.cibaFragment;
//        showFragment();
//    }
//
//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void showBingFragment() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.bingFragment;
//        showFragment();
//    }
//
//    @Override // com.datong.dict.module.dict.DictContract.View
//    public void showDictCnFragment() {
//        this.lastFragment = this.currentFragment;
//        this.currentFragment = this.dictCnFragment;
//        showFragment();
//    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public void showCollectBottomSheet() {
//        CollectBtmSheet.with(this).collect(this.word, this.wordId).onDismiss(new DialogInterface.OnDismissListener() { // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda0
//            @Override // android.content.DialogInterface.OnDismissListener
//            public final void onDismiss(DialogInterface dialogInterface) {
//                DictActivity.this.presenter.updateCollectState();
//            }
//        }).show();
    }

    /* synthetic */

    @Override // com.datong.dict.module.dict.DictContract.View
    public void showMessageSnackbar(String msg) {
        hideDictSelector();
        // from class: com.datong.dict.module.dict.DictActivity$$ExternalSyntheticLambda8
// com.datong.dict.widget.MessageSnackbar.OnDismissCallback
        MessageSnackbar.with(this.rootView).message(msg).onDismiss(() -> {
            if (DictActivity.this.isSelectorShowing || DictActivity.this.isSelectorMoving) {
                return;
            }
            DictActivity.this.isSelectorMoving = true;
            DictActivity.this.isSelectorShowing = true;
            AnimatorUtil.setObjectAnimator(DictActivity.this.listDictSelector, "TranslationY", 300L, new AnimatorUtil.Callback() { // from class: com.datong.dict.module.dict.DictActivity.1
                @Override // com.datong.dict.utils.AnimatorUtil.Callback, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    DictActivity.this.isSelectorMoving = false;
                }
            }, 0.0f);
        }).show();
    }

    // com.datong.dict.module.dict.DictContract.View
    /* renamed from: showDictSelector */

    @Override // com.datong.dict.module.dict.DictContract.View
    public void hideDictSelector() {
        if (!this.isSelectorShowing || this.isSelectorMoving) {
            return;
        }
        this.isSelectorShowing = false;
        this.isSelectorMoving = true;
        AnimatorUtil.setObjectAnimator(this.listDictSelector, "TranslationY", 300L, new AnimatorUtil.Callback() { // from class: com.datong.dict.module.dict.DictActivity.2
            @Override // com.datong.dict.utils.AnimatorUtil.Callback, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                DictActivity.this.isSelectorMoving = false;
            }
        }, 0.0f, 300.0f);
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public List<SelectorItem> getSelectorItems() {
        return this.selectorItems;
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public View getRootView() {
        return this.rootView;
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public String getWord() {
        return this.word;
    }

    @Override // com.datong.dict.module.dict.DictContract.View
    public String getWordId() {
        return this.wordId;
    }

    private void showFragment() {
        if (this.currentFragment == this.lastFragment) {
            return;
        }
        FragmentTransaction customAnimations = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        BaseFragment baseFragment = this.lastFragment;
        if (baseFragment != null) {
            customAnimations.hide(baseFragment);
        }
        customAnimations.show(this.currentFragment).commitAllowingStateLoss();
    }

    @Override // com.datong.dict.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        SoundUtil.getInstance().destroy();
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(DictContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * Called when pointer capture is enabled or disabled for the current window.
     *
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
