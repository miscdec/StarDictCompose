package com.datong.dict.module.dict.en.youdao.items.discriminate;

import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class DiscriminateFragment_ViewBinding implements Unbinder {
    private DiscriminateFragment target;

    public DiscriminateFragment_ViewBinding(DiscriminateFragment target, View source) {
        this.target = target;
        target.cardView = (CardView) Utils.findRequiredViewAsType(source, R.id.card_youdao_discriminate, "field 'cardView'", CardView.class);
        target.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_discriminate, "field 'recyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DiscriminateFragment discriminateFragment = this.target;
        if (discriminateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        discriminateFragment.cardView = null;
        discriminateFragment.recyclerView = null;
    }
}
