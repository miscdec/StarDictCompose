package com.datong.dict.widget;

import static com.google.android.material.snackbar.Snackbar.make;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.datong.dict.R;
import com.datong.dict.data.dictionary.en.YoudaoRepository;
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord;
import com.datong.dict.data.dictionary.en.source.YoudaoDataSource;
import com.datong.dict.databinding.LayoutWordSnackbarBinding;
import com.datong.dict.module.dict.DictActivity;
import com.datong.dict.utils.SettingUtil;
import com.datong.dict.utils.SoundUtil;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


/* loaded from: classes.dex */
public class WordSnackbar {
    public static WordSnackbar INSTANCE;
    public static Snackbar snackbar;
    private View contentView;
    public Context context;
    private String explain;
    @BindView(R.id.img_wordSnackbar_sound)
    ImageView imgSound;
    private boolean isOutSideQuery;
    private OnDismissCallback onDismissCallback;
    private int queryTimes = 0;
    private YoudaoRepository repository;
//    private SnackbarLayout snackbarLayout;
    @BindView(R.id.tv_wordSnackbar_btn)
    TextView tvBtn;
    @BindView(R.id.tv_wordSnackbar_expCN)
    TextView tvExpCN;
    @BindView(R.id.tv_wordSnackbar_phonetic)
    TextView tvPhonetic;
    @BindView(R.id.tv_wordSnackbar_word)
    TextView tvWord;
    private String word;

    /* loaded from: classes.dex */
    public interface OnDismissCallback {
        void callback();
    }

    public static WordSnackbar with(View parent) {
        WordSnackbar wordSnackbar = new WordSnackbar(parent);
        INSTANCE = wordSnackbar;
        return wordSnackbar;
    }

    public static WordSnackbar with(Activity activity) {
        WordSnackbar wordSnackbar = new WordSnackbar(activity);
        INSTANCE = wordSnackbar;
        return wordSnackbar;
    }

    public static WordSnackbar context(Context context) {
        WordSnackbar wordSnackbar = INSTANCE;
        wordSnackbar.context = context;
        return wordSnackbar;
    }

    public WordSnackbar isOutSideQuery(boolean isOutSideQuery) {
        this.isOutSideQuery = isOutSideQuery;
        return INSTANCE;
    }

    public WordSnackbar query(String keywords) {
        String lowerCase = keywords.replace(",", " ").replace("?", "").replace(".", "").replace("[", "").replace("]", "").replace(";", "").replace(":", "").trim().toLowerCase();
        this.word = lowerCase;
        if (lowerCase.equals("")) {
            return INSTANCE;
        }
        query();
        return INSTANCE;
    }

    public WordSnackbar listener(BaseTransientBottomBar.BaseCallback<Snackbar> callback) {
        snackbar.addCallback(callback);
        return INSTANCE;
    }

    public WordSnackbar onDismiss(OnDismissCallback onDismissCallback) {
        this.onDismissCallback = onDismissCallback;
        return INSTANCE;
    }

    public WordSnackbar show() {
        return INSTANCE;
    }

    public static void dismiss() {
        if (INSTANCE == null) {
            return;
        }
        snackbar.dismiss();
        INSTANCE = null;
    }

    private WordSnackbar(View rootView) {
        this.context = rootView.getContext();
        initVars(rootView);
        initView();
        handleEvents();
    }

    private WordSnackbar(Activity activity) {
        this.context = activity;
        initVars(activity.getWindow().getDecorView());
        initView();
        handleEvents();
    }

    private void initVars(View rootView) {
        Snackbar make = make(rootView, "", -2);
        snackbar = make;
        make.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() { // from class: com.datong.dict.widget.WordSnackbar.1
            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                if (WordSnackbar.this.onDismissCallback != null) {
                    WordSnackbar.this.onDismissCallback.callback();
                }
            }

            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
            public void onShown(Snackbar transientBottomBar) {
                super.onShown(transientBottomBar);
            }
        });
        this.contentView = LayoutInflater.from(this.context).inflate(R.layout.layout_word_snackbar, (ViewGroup) null);
        this.repository = YoudaoRepository.Companion.getInstance(context);
        this.isOutSideQuery = false;
        this.queryTimes = 0;
    }

    private void initView() {
        LayoutWordSnackbarBinding binding = LayoutWordSnackbarBinding.bind(this.contentView);
        ButterKnife.bind(this, this.contentView);
        this.tvWord.setTextColor(-1);
        this.tvPhonetic.setTextColor(-1);
        this.tvExpCN.setTextColor(-1);
        this.tvBtn.setTextColor(-3355444);
        this.imgSound.setColorFilter(this.context.getResources().getColor(R.color.red_datong));
    }

    private void handleEvents() {
        this.tvBtn.setOnClickListener(new View.OnClickListener() { // from class: com.datong.dict.widget.WordSnackbar$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WordSnackbar.this.lambda$handleEvents$0$WordSnackbar(view);
            }
        });
        this.imgSound.setOnClickListener(new View.OnClickListener() { // from class: com.datong.dict.widget.WordSnackbar$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WordSnackbar.this.lambda$handleEvents$1$WordSnackbar(view);
            }
        });
    }

    public /* synthetic */ void lambda$handleEvents$0$WordSnackbar(View view) {
        String charSequence = this.tvWord.getText().toString();
        Intent intent = new Intent(this.context, DictActivity.class);
        intent.putExtra("language", "en");
        intent.putExtra("word", charSequence);
        this.context.startActivity(intent);
    }

    public /* synthetic */ void lambda$handleEvents$1$WordSnackbar(View view) {
        SoundUtil.getInstance().text(this.word).url("").dictType(1).soundType(1).languageType(1).play();
    }

    private void query() {
        this.repository.getWord(this.word, new YoudaoDataSource.GetWordCallback() { // from class: com.datong.dict.widget.WordSnackbar.2
            @Override // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
            public void onLoad(YoudaoWord word) {
                if (word.word.equals(word.expCN)) {
                    WordSnackbar.this.showNotFoundSnackbar();
                    return;
                }
                WordSnackbar.this.explain = word.expCN;
                WordSnackbar.this.tvWord.setText(word.word);
                WordSnackbar.this.tvExpCN.setText(word.expCN);
                WordSnackbar.this.tvPhonetic.setText(word.getPhoneticUs());
                WordSnackbar.this.tvPhonetic.setVisibility(word.getPhoneticUs().isEmpty() ? View.GONE : View.VISIBLE);
                WordSnackbar.snackbar.show();
                if (SettingUtil.isAutoCollect()) {
                    if (SettingUtil.isAutoCollcectWhenOutsideQuery() && WordSnackbar.this.isOutSideQuery) {
                        WordSnackbar.this.collectWord(word.word);
                    } else if (SettingUtil.isAutoCollcectWhenClickQuery()) {
                        WordSnackbar.this.collectWord(word.word);
                    }
                }
            }

            @Override // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
            public void onError() {
                WordSnackbar.this.showNotFoundSnackbar();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNotFoundSnackbar() {
        this.contentView.setVisibility(View.GONE);
//        this.snackbarLayout.setBackgroundColor(Color.parseColor(MessageSnackbar.COLOR_ERROR));
        snackbar.setDuration(-1);
        Snackbar snackbar2 = snackbar;
        snackbar2.setText("未找到单词”" + this.word + "“！");
        snackbar.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collectWord(String word) {
//        BookRepository.getInstance(this.context).addWordCollect(new WordCollect(SettingUtil.getDefaultBook(), word, null), null);
    }
}
