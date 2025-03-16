package com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter;

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
import com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupListAdapter;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class WordGroupListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<WordGroupItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_wordGroup_index, "field 'tvIndex'", TextView.class);
            target.tvWordGroup = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_wordGroup_wordGroup, "field 'tvWordGroup'", WordTextView.class);
            target.tvExplain = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_wordGroup_explain, "field 'tvExplain'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvIndex = null;
            viewHolder.tvWordGroup = null;
            viewHolder.tvExplain = null;
        }
    }

    public WordGroupListAdapter(Context context, List<WordGroupItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_word_group, parent, false));
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
        @BindView(R.id.tv_item_list_youdao_wordGroup_explain)
        TextView tvExplain;
        @BindView(R.id.tv_item_list_youdao_wordGroup_index)
        TextView tvIndex;
        @BindView(R.id.tv_item_list_youdao_wordGroup_wordGroup)
        WordTextView tvWordGroup;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.tvWordGroup.setOnWordClickCallback(new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    WordGroupListAdapter.ViewHolder.this.lambda$new$0$WordGroupListAdapter$ViewHolder(str);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$WordGroupListAdapter$ViewHolder(String str) {
            WordGroupListAdapter.this.persenter.showWordSnackbar(str);
        }

        public void onBind(int position) {
            WordGroupItem wordGroupItem = WordGroupListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(wordGroupItem.getIndex() + ".");
            this.tvWordGroup.setText(wordGroupItem.getWordGroup());
            this.tvExplain.setText(wordGroupItem.getExplain());
        }
    }
}
