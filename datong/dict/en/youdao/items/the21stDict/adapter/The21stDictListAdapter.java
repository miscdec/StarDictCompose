package com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain.The21stDictExplainListAdapter;
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.phrase.The21stDictPhraseListAdapter;
import java.util.List;

/* loaded from: classes.dex */
public class The21stDictListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<The21DictItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvExplainTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explainTitle, "field 'tvExplainTitle'", TextView.class);
            target.listExplain = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_item_list_youdao_the21st_explain, "field 'listExplain'", RecyclerView.class);
            target.tvPhraseTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_phraseTitle, "field 'tvPhraseTitle'", TextView.class);
            target.listPhrase = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_item_list_youdao_the21st_phrase, "field 'listPhrase'", RecyclerView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvExplainTitle = null;
            viewHolder.listExplain = null;
            viewHolder.tvPhraseTitle = null;
            viewHolder.listPhrase = null;
        }
    }

    public The21stDictListAdapter(Context context, List<The21DictItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_the21st, parent, false));
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
        @BindView(R.id.list_item_list_youdao_the21st_explain)
        RecyclerView listExplain;
        @BindView(R.id.list_item_list_youdao_the21st_phrase)
        RecyclerView listPhrase;
        @BindView(R.id.tv_item_list_youdao_the21st_explainTitle)
        TextView tvExplainTitle;
        @BindView(R.id.tv_item_list_youdao_the21st_phraseTitle)
        TextView tvPhraseTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initRecyclerView();
        }

        private void initRecyclerView() {
            this.listExplain.setLayoutManager(new LinearLayoutManager(The21stDictListAdapter.this.context, 1, false));
            this.listExplain.setItemAnimator(new DefaultItemAnimator());
            this.listExplain.setNestedScrollingEnabled(false);
            this.listExplain.setHasFixedSize(true);
            this.listPhrase.setLayoutManager(new LinearLayoutManager(The21stDictListAdapter.this.context, 1, false));
            this.listPhrase.setItemAnimator(new DefaultItemAnimator());
            this.listPhrase.setNestedScrollingEnabled(false);
            this.listPhrase.setHasFixedSize(true);
        }

        public void onBind(int position) {
            The21DictItem the21DictItem = The21stDictListAdapter.this.itemList.get(position);
            if (the21DictItem.getExplainItemList() != null && the21DictItem.getExplainItemList().size() > 0) {
                this.tvExplainTitle.setText(the21DictItem.getExplainTitle());
                this.listExplain.setAdapter(new The21stDictExplainListAdapter(The21stDictListAdapter.this.context, the21DictItem.getExplainItemList(), The21stDictListAdapter.this.persenter));
            } else {
                this.tvExplainTitle.setVisibility(8);
                this.listExplain.setVisibility(8);
            }
            if (the21DictItem.getPhraseItemList() != null && the21DictItem.getPhraseItemList().size() > 0) {
                this.listPhrase.setAdapter(new The21stDictPhraseListAdapter(The21stDictListAdapter.this.context, the21DictItem.getPhraseItemList(), The21stDictListAdapter.this.persenter));
                return;
            }
            this.tvPhraseTitle.setVisibility(8);
            this.listPhrase.setVisibility(8);
        }
    }
}
