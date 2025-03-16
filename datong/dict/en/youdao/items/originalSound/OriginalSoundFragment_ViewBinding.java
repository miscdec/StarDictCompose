package com.datong.dict.module.dict.en.youdao.items.originalSound;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.widget.base.BaseRecyclerView;

/* loaded from: classes.dex */
public class OriginalSoundFragment_ViewBinding implements Unbinder {
    private OriginalSoundFragment target;

    public OriginalSoundFragment_ViewBinding(OriginalSoundFragment target, View source) {
        this.target = target;
        target.recyclerView = (BaseRecyclerView) Utils.findRequiredViewAsType(source, R.id.list_youdao_originaSound, "field 'recyclerView'", BaseRecyclerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        OriginalSoundFragment originalSoundFragment = this.target;
        if (originalSoundFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        originalSoundFragment.recyclerView = null;
    }
}
