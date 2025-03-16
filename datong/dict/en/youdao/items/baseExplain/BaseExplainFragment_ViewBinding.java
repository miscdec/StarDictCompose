package com.datong.dict.module.dict.en.youdao.items.baseExplain;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class BaseExplainFragment_ViewBinding implements Unbinder {
    private BaseExplainFragment target;
    private View view7f090153;
    private View view7f090154;
    private View view7f09045d;
    private View view7f090461;

    public BaseExplainFragment_ViewBinding(final BaseExplainFragment target, View source) {
        this.target = target;
        target.cardBaseExplain = (CardView) Utils.findRequiredViewAsType(source, R.id.card_youdao_baseExplain, "field 'cardBaseExplain'", CardView.class);
        View findRequiredView = Utils.findRequiredView(source, R.id.tv_youdao_baseExplain_word, "field 'tvWord' and method 'handleTitleWordCllick'");
        target.tvWord = (TextView) Utils.castView(findRequiredView, R.id.tv_youdao_baseExplain_word, "field 'tvWord'", TextView.class);
        this.view7f090461 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.baseExplain.BaseExplainFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View p0) {
                target.handleTitleWordCllick();
            }
        });
        target.tvPhoneticUs = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_youdao_baseExplain_phoneticUs, "field 'tvPhoneticUs'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(source, R.id.img_youdao_baseExplain_phoneticUs, "field 'imgPhoneticUs' and method 'handleSoundUsEvent'");
        target.imgPhoneticUs = (ImageView) Utils.castView(findRequiredView2, R.id.img_youdao_baseExplain_phoneticUs, "field 'imgPhoneticUs'", ImageView.class);
        this.view7f090154 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.baseExplain.BaseExplainFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View p0) {
                target.handleSoundUsEvent();
            }
        });
        target.tvPhoneticUk = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_youdao_baseExplain_phoneticUk, "field 'tvPhoneticUk'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(source, R.id.img_youdao_baseExplain_phoneticUk, "field 'imgPhoneticUk' and method 'handleSoundUkEvent'");
        target.imgPhoneticUk = (ImageView) Utils.castView(findRequiredView3, R.id.img_youdao_baseExplain_phoneticUk, "field 'imgPhoneticUk'", ImageView.class);
        this.view7f090153 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.baseExplain.BaseExplainFragment_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View p0) {
                target.handleSoundUkEvent();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(source, R.id.tv_youdao_baseExplain_expCN, "field 'tvExpCN' and method 'handleOnExpCNClick'");
        target.tvExpCN = (TextView) Utils.castView(findRequiredView4, R.id.tv_youdao_baseExplain_expCN, "field 'tvExpCN'", TextView.class);
        this.view7f09045d = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.baseExplain.BaseExplainFragment_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View p0) {
                target.handleOnExpCNClick();
            }
        });
        target.tvShape = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_youdao_baseExplain_shape, "field 'tvShape'", TextView.class);
        target.cardExpEn = (CardView) Utils.findRequiredViewAsType(source, R.id.card_youdao_expEN, "field 'cardExpEn'", CardView.class);
        target.listExpEN = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_baseExplain_expEN, "field 'listExpEN'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BaseExplainFragment baseExplainFragment = this.target;
        if (baseExplainFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        baseExplainFragment.cardBaseExplain = null;
        baseExplainFragment.tvWord = null;
        baseExplainFragment.tvPhoneticUs = null;
        baseExplainFragment.imgPhoneticUs = null;
        baseExplainFragment.tvPhoneticUk = null;
        baseExplainFragment.imgPhoneticUk = null;
        baseExplainFragment.tvExpCN = null;
        baseExplainFragment.tvShape = null;
        baseExplainFragment.cardExpEn = null;
        baseExplainFragment.listExpEN = null;
        this.view7f090461.setOnClickListener(null);
        this.view7f090461 = null;
        this.view7f090154.setOnClickListener(null);
        this.view7f090154 = null;
        this.view7f090153.setOnClickListener(null);
        this.view7f090153 = null;
        this.view7f09045d.setOnClickListener(null);
        this.view7f09045d = null;
    }
}
