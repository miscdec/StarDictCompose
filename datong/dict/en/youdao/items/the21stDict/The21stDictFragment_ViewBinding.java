package com.datong.dict.module.dict.en.youdao.items.the21stDict;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class The21stDictFragment_ViewBinding implements Unbinder {
    private The21stDictFragment target;

    public The21stDictFragment_ViewBinding(The21stDictFragment target, View source) {
        this.target = target;
        target.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_the21stDict, "field 'recyclerView'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        The21stDictFragment the21stDictFragment = this.target;
        if (the21stDictFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        the21stDictFragment.recyclerView = null;
    }
}
