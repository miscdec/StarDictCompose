package com.datong.dict.module.dict.en.youdao.items.collins;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.widget.base.BaseRecyclerView;

/* loaded from: classes.dex */
public class CollinsFragment_ViewBinding implements Unbinder {
    private CollinsFragment target;

    public CollinsFragment_ViewBinding(CollinsFragment target, View source) {
        this.target = target;
        target.recyclerView = (BaseRecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_collins, "field 'recyclerView'", BaseRecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CollinsFragment collinsFragment = this.target;
        if (collinsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        collinsFragment.recyclerView = null;
    }
}
