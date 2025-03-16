package com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.phrase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import java.util.List;

/* loaded from: classes.dex */
public class The21stDictPhraseListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<The21stDictPhraseItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_phrase_index, "field 'tvIndex'", TextView.class);
            target.tvPhrase = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_phrase_phrase, "field 'tvPhrase'", TextView.class);
            target.tvExplain = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_phrase_explain, "field 'tvExplain'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvIndex = null;
            viewHolder.tvPhrase = null;
            viewHolder.tvExplain = null;
        }
    }

    public The21stDictPhraseListAdapter(Context context, List<The21stDictPhraseItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_the21st_phrase, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_list_youdao_the21st_phrase_explain)
        TextView tvExplain;
        @BindView(R.id.tv_item_list_youdao_the21st_phrase_index)
        TextView tvIndex;
        @BindView(R.id.tv_item_list_youdao_the21st_phrase_phrase)
        TextView tvPhrase;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            The21stDictPhraseItem the21stDictPhraseItem = The21stDictPhraseListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(the21stDictPhraseItem.getIndex() + ".");
            this.tvPhrase.setText(the21stDictPhraseItem.getPhrase());
            this.tvExplain.setText(the21stDictPhraseItem.getExplain());
            this.tvExplain.setVisibility(the21stDictPhraseItem.getExplain().equals("") ? 8 : 0);
        }
    }
}
