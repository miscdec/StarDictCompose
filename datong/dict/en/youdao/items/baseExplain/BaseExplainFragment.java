package com.datong.dict.module.dict.en.youdao.items.baseExplain;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.datong.dict.R;
import com.datong.dict.base.BaseFragment;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnItem;
import com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnListAdapter;
import com.datong.dict.utils.FontUtil;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.utils.SoundUtil;
import com.datong.dict.utils.TextUtil;
import java.util.List;

/* loaded from: classes.dex */
public class BaseExplainFragment extends BaseFragment implements YoudaoContract.BaseExplainView {
    @BindView(R.id.card_youdao_baseExplain)
    CardView cardBaseExplain;
    @BindView(R.id.card_youdao_expEN)
    CardView cardExpEn;
    @BindView(R.id.img_youdao_baseExplain_phoneticUk)
    ImageView imgPhoneticUk;
    @BindView(R.id.img_youdao_baseExplain_phoneticUs)
    ImageView imgPhoneticUs;
    @BindView(R.id.list_youdao_baseExplain_expEN)
    RecyclerView listExpEN;
    YoudaoContract.Presenter presenter;
    @BindView(R.id.tv_youdao_baseExplain_expCN)
    TextView tvExpCN;
    @BindView(R.id.tv_youdao_baseExplain_phoneticUk)
    TextView tvPhoneticUk;
    @BindView(R.id.tv_youdao_baseExplain_phoneticUs)
    TextView tvPhoneticUs;
    @BindView(R.id.tv_youdao_baseExplain_shape)
    TextView tvShape;
    @BindView(R.id.tv_youdao_baseExplain_word)
    TextView tvWord;

    @Override // com.datong.dict.base.BaseFragment
    public void handleEvents() {
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initVariables() {
    }

    public static BaseExplainFragment newInstance() {
        BaseExplainFragment baseExplainFragment = new BaseExplainFragment();
        baseExplainFragment.setContentView(R.layout.fragment_youdao_base_explain);
        baseExplainFragment.setTitle("基本释义");
        return baseExplainFragment;
    }

    @Override // com.datong.dict.base.BaseFragment
    public void initViews(Bundle savedInstanceState) {
        initExplainEnList();
        this.imgPhoneticUs.setColorFilter(getResources().getColor(R.C0079color.red_datong));
        this.imgPhoneticUk.setColorFilter(getResources().getColor(R.C0079color.red_datong));
        this.cardBaseExplain.setVisibility(View.GONE);
        this.cardExpEn.setVisibility(View.GONE);
        this.tvShape.setVisibility(View.GONE);
        if (SettingUtil.isFixPhoneticFont()) {
            FontUtil.setFont(this.tvPhoneticUk, R.font.lingoes, FontUtil.FONT_PATH_LINGOES);
            FontUtil.setFont(this.tvPhoneticUs, R.font.lingoes, FontUtil.FONT_PATH_LINGOES);
        }
    }

    @Override // com.datong.dict.base.BaseFragment
    public void onLoadData() {
        this.presenter.onloadBaseExplain();
        this.presenter.onloadExplainEN();
    }

    private void initExplainEnList() {
        this.listExpEN.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.listExpEN.setItemAnimator(new DefaultItemAnimator());
        this.listExpEN.setNestedScrollingEnabled(false);
        this.listExpEN.setHasFixedSize(true);
    }

    @OnClick({R.id.img_youdao_baseExplain_phoneticUs})
    public void handleSoundUsEvent() {
        SoundUtil.getInstance().text(this.tvWord.getText().toString()).url("").dictType(2).soundType(1).languageType(1).play();
    }

    @OnClick({R.id.img_youdao_baseExplain_phoneticUk})
    public void handleSoundUkEvent() {
        SoundUtil.getInstance().text(this.tvWord.getText().toString()).url("").dictType(2).soundType(1).languageType(2).play();
    }

    @OnClick({R.id.tv_youdao_baseExplain_word})
    public void handleTitleWordCllick() {
        this.presenter.copyToClipboard(this.tvWord.getText().toString());
    }

    @OnClick({R.id.tv_youdao_baseExplain_expCN})
    public void handleOnExpCNClick() {
        this.presenter.copyToClipboard(this.tvExpCN.getText().toString());
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setWord(String word) {
        this.tvWord.setText(word);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setPhoneticUs(String phoneticUs) {
        this.tvPhoneticUs.setText(TextUtil.htmlString(phoneticUs));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setPhoneticUk(String phoneticUk) {
        this.tvPhoneticUk.setText(TextUtil.htmlString(phoneticUk));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setExpCN(String expCN) {
        this.tvExpCN.setText(expCN);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setShape(String shape) {
        this.tvShape.setText(shape);
        this.tvShape.setVisibility(shape.equals("") ? 8 : 0);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void showBaseExplainCard() {
        this.cardBaseExplain.setVisibility(0);
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void setExpEnListAdapter(List<ExpEnItem> itemList) {
        this.listExpEN.setAdapter(new ExpEnListAdapter(getContext(), itemList, this.presenter));
    }

    @Override // com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
    public void showExplainEnCard() {
        this.cardExpEn.setVisibility(0);
    }

    @Override // com.datong.dict.base.BaseView
    public void setPresenter(YoudaoContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
