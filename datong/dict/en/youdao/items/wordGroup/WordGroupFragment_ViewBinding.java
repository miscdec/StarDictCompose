package com.datong.dict.module.dict.en.youdao.items.wordGroup;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class WordGroupFragment_ViewBinding implements Unbinder {
    private WordGroupFragment target;

    public WordGroupFragment_ViewBinding(WordGroupFragment target, View source) {
        this.target = target;
        target.cardView = (CardView) Utils.findRequiredViewAsType(source, R.id.card_youdao_wordGroup, "field 'cardView'", CardView.class);
        target.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_wordGroup, "field 'recyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WordGroupFragment wordGroupFragment = this.target;
        if (wordGroupFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        wordGroupFragment.cardView = null;
        wordGroupFragment.recyclerView = null;
    }
}
