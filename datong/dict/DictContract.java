package com.datong.dict.module.dict;

import android.content.Context;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.base.BasePresenter;
import com.datong.dict.base.BaseView;
import com.datong.dict.module.dict.adapter.SelectorItem;
import java.util.List;

/* loaded from: classes.dex */
public class DictContract {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Presenter extends BasePresenter {
        void addSearchHistory(String word, String wordId);

        void autoPlaySound(String word);

        void collectWhenClick(String word, String wordId);

        void collectWhenQuery(String word, String wordId);

        void deleteAllCollected(String word);

        String[] getMenuItems(BaseFragment currentFragment);

        void handleMenuItemClick(String item);

        void handleOnSelectorItemClick(SelectorItem item);

        void onloadSelectorItems();

        void setCurrentFragment(String dictType);

        void updateCollectState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface View extends BaseView<Presenter> {
        Context getContext();

        android.view.View getRootView();

        List<SelectorItem> getSelectorItems();

        String getWord();

        String getWordId();

        void hideDictSelector();

        void setCollectState(boolean isCollected);

//        void setCurrentFragmentIsBing();
//
//        void setCurrentFragmentIsCiba();
//
//        void setCurrentFragmentIsDatongEn();
//
//        void setCurrentFragmentIsDatongJp();
//
//        void setCurrentFragmentIsDictCn();

        void setCurrentFragmentIsYoudao();

        void setDictSelectorColor(int color);

        void setToolbarTitle(String title);

//        void showBingFragment();
//
//        void showCibaFragment();

        void showCollectBottomSheet();

//        void showDatongEnFragment();
//
//        void showDictCnFragment();

        void showDictMenu();

        void showDictSelector();

        void showMessageSnackbar(String msg);

        void showYoudaoFragment();
    }
}
