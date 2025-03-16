package com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter;

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
import com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnListAdapter;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class ExpEnListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<ExpEnItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvWordClass = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_expEN_wordClass, "field 'tvWordClass'", TextView.class);
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_expEN_index, "field 'tvIndex'", TextView.class);
            target.tvExplain = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_expEN_explain, "field 'tvExplain'", WordTextView.class);
            target.tvSentence = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_expEN_sentence, "field 'tvSentence'", WordTextView.class);
            target.tvSynonyms = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_expEN_synonyms, "field 'tvSynonyms'", WordTextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvWordClass = null;
            viewHolder.tvIndex = null;
            viewHolder.tvExplain = null;
            viewHolder.tvSentence = null;
            viewHolder.tvSynonyms = null;
        }
    }

    public ExpEnListAdapter(Context context, List<ExpEnItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_exp_en, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        WordTextView.OnWordClickCallback onWordClickCallback;
        @BindView(R.id.tv_item_list_youdao_expEN_explain)
        WordTextView tvExplain;
        @BindView(R.id.tv_item_list_youdao_expEN_index)
        TextView tvIndex;
        @BindView(R.id.tv_item_list_youdao_expEN_sentence)
        WordTextView tvSentence;
        @BindView(R.id.tv_item_list_youdao_expEN_synonyms)
        WordTextView tvSynonyms;
        @BindView(R.id.tv_item_list_youdao_expEN_wordClass)
        TextView tvWordClass;

        public /* synthetic */ void lambda$new$0$ExpEnListAdapter$ViewHolder(String str) {
            ExpEnListAdapter.this.persenter.showWordSnackbar(str);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.onWordClickCallback = new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    ExpEnListAdapter.ViewHolder.this.lambda$new$0$ExpEnListAdapter$ViewHolder(str);
                }
            };
            ButterKnife.bind(this, itemView);
            handleWordClickEvent();
        }

        private void handleWordClickEvent() {
            this.tvExplain.setOnWordClickCallback(this.onWordClickCallback);
            this.tvSentence.setOnWordClickCallback(this.onWordClickCallback);
            this.tvSynonyms.setOnWordClickCallback(this.onWordClickCallback);
        }

        public void bind(int positon) {
            ExpEnItem expEnItem = ExpEnListAdapter.this.itemList.get(positon);
            this.tvWordClass.setText(expEnItem.getWordClass());
            TextView textView = this.tvIndex;
            textView.setText(expEnItem.getIndex() + ".");
            this.tvExplain.setText(expEnItem.getExplain());
            this.tvSentence.setText(expEnItem.getSentence());
            WordTextView wordTextView = this.tvSynonyms;
            wordTextView.setText("同义词 : " + expEnItem.getSynonyms());
            this.tvSentence.setVisibility(!expEnItem.getSentence().equals("") ? 0 : 8);
            this.tvSynonyms.setVisibility(expEnItem.getSynonyms().equals("") ? 8 : 0);
            if (expEnItem.getWordClass().equals("") || !expEnItem.getIndex().equals("1")) {
                this.tvWordClass.setVisibility(8);
            }
        }
    }
}
