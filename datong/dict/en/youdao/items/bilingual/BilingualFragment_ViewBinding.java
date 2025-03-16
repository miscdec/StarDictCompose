package com.datong.dict.module.dict.en.youdao.items.bilingual;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.widget.base.BaseRecyclerView;

/* loaded from: classes.dex */
public class BilingualFragment_ViewBinding implements Unbinder {
    private BilingualFragment target;

    public BilingualFragment_ViewBinding(BilingualFragment target, View source) {
        this.target = target;
        target.recyclerView = (BaseRecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_bilingual, "field 'recyclerView'", BaseRecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BilingualFragment bilingualFragment = this.target;
        if (bilingualFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bilingualFragment.recyclerView = null;
    }
}
