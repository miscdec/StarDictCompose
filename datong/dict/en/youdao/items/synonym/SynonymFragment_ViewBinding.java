package com.datong.dict.module.dict.en.youdao.items.synonym;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.widget.base.BaseRecyclerView;

/* loaded from: classes.dex */
public class SynonymFragment_ViewBinding implements Unbinder {
    private SynonymFragment target;

    public SynonymFragment_ViewBinding(SynonymFragment target, View source) {
        this.target = target;
        target.recyclerView = (BaseRecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_synonym, "field 'recyclerView'", BaseRecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SynonymFragment synonymFragment = this.target;
        if (synonymFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        synonymFragment.recyclerView = null;
    }
}
