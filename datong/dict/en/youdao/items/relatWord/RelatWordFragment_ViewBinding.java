package com.datong.dict.module.dict.en.youdao.items.relatWord;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class RelatWordFragment_ViewBinding implements Unbinder {
    private RelatWordFragment target;

    public RelatWordFragment_ViewBinding(RelatWordFragment target, View source) {
        this.target = target;
        target.cardView = (CardView) Utils.findRequiredViewAsType(source, R.id.card_youdao_relatWord, "field 'cardView'", CardView.class);
        target.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_relatWord, "field 'recyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RelatWordFragment relatWordFragment = this.target;
        if (relatWordFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        relatWordFragment.cardView = null;
        relatWordFragment.recyclerView = null;
    }
}
