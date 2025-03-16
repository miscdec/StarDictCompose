package com.datong.dict.module.dict.en.youdao;

import android.content.Context;
import android.view.View;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.base.BasePresenter;
import com.datong.dict.base.BaseView;
import com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityItem;
import com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnItem;
import com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualItem;
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsItem;
import com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateItem;
import com.datong.dict.module.dict.en.youdao.items.originalSound.adapter.OriginalSoundItem;
import com.datong.dict.module.dict.en.youdao.items.relatWord.adapter.RelatWordItem;
import com.datong.dict.module.dict.en.youdao.items.synonym.adapter.SynonymItem;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.The21DictItem;
import com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupItem;
import java.util.List;

/* loaded from: classes.dex */
public class YoudaoContract {

    /* loaded from: classes.dex */
    public interface AuthorityView extends BaseView<Presenter> {
        void setAuthorityListAdapter(List<AuthorityItem> authorityItemList);
    }

    /* loaded from: classes.dex */
    public interface BaseExplainView extends BaseView<Presenter> {
        void setExpCN(String expCN);

        void setExpEnListAdapter(List<ExpEnItem> itemList);

        void setPhoneticUk(String phoneticUk);

        void setPhoneticUs(String phoneticUs);

        void setShape(String shape);

        void setWord(String word);

        void showBaseExplainCard();

        void showExplainEnCard();
    }

    /* loaded from: classes.dex */
    public interface BilingualView extends BaseView<Presenter> {
        void setBilingualListAdapter(List<BilingualItem> bilingualItemList);
    }

    /* loaded from: classes.dex */
    public interface CollinsView extends BaseView<Presenter> {
        void setCollinsListAdapter(List<CollinsItem> collinsItemList);
    }

    /* loaded from: classes.dex */
    public interface DiscriminateView extends BaseView<Presenter> {
        void setDiscriminateListAdapter(List<DiscriminateItem> discriminateItemList);

        void showDiscriminateCard();
    }

    /* loaded from: classes.dex */
    public interface OriginalSoundView extends BaseView<Presenter> {
        void setOriginalSoundListAdapter(List<OriginalSoundItem> originalSoundItemList);
    }

    /* loaded from: classes.dex */
    public interface Presenter extends BasePresenter {
        void copyToClipboard(String word);

        String getWordText();

        void onlaodAuthority();

        void onlaodBilingual();

        void onlaodOriginalSound();

        void onloadBaseExplain();

        void onloadCollins();

        void onloadDiscriminate();

        void onloadExplainEN();

        void onloadPagerTitles(List<BaseFragment> fragmentList);

        void onloadRelatWord();

        void onloadSynonym();

        void onloadThe21stDict();

        void onloadWordGroup();

        void showWordSnackbar(String word);
    }

    /* loaded from: classes.dex */
    public interface RelatWordView extends BaseView<Presenter> {
        void setRelatWordListAdapter(List<RelatWordItem> relatWordItemList);

        void showRelatWordCard();
    }

    /* loaded from: classes.dex */
    public interface SynonymView extends BaseView<Presenter> {
        void setSynonymListAdapter(List<SynonymItem> synonymItemList);

        void showSynonymCard();
    }

    /* loaded from: classes.dex */
    public interface The21stDictView extends BaseView<Presenter> {
        void setThe21stDictListAdapter(List<The21DictItem> the21DictItemList);
    }

    /* loaded from: classes.dex */
    public interface WordGroupView extends BaseView<Presenter> {
        void setWordGroupListAdapter(List<WordGroupItem> groupItemList);

        void showWordGroupCard();
    }

    /* loaded from: classes.dex */
    public interface YoudaoView extends BaseView<Presenter> {
        Context getContext();

        View getRootView();

        void hideDictSelector();

        void hideRefreshView();

        void showDictSelector();

        void showMessageSnackbar(String msg);

        void showRefreshView();

        void showWordSnackbar(String word);

        void updatePager();
    }
}
