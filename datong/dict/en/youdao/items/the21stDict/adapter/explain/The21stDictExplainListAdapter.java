package com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain;

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
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain.The21stDictExplainListAdapter;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class The21stDictExplainListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<The21stDictExplainItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_wordClass, "field 'tvTitle'", TextView.class);
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_index, "field 'tvIndex'", TextView.class);
            target.tvExplain = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_explain, "field 'tvExplain'", TextView.class);
            target.tvEn1 = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_en_1, "field 'tvEn1'", WordTextView.class);
            target.tvCn1 = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_cn_1, "field 'tvCn1'", TextView.class);
            target.tvEn2 = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_en_2, "field 'tvEn2'", WordTextView.class);
            target.tvCn2 = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_the21st_explain_cn_2, "field 'tvCn2'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvTitle = null;
            viewHolder.tvIndex = null;
            viewHolder.tvExplain = null;
            viewHolder.tvEn1 = null;
            viewHolder.tvCn1 = null;
            viewHolder.tvEn2 = null;
            viewHolder.tvCn2 = null;
        }
    }

    public The21stDictExplainListAdapter(Context context, List<The21stDictExplainItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_the21st_explain, parent, false));
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
        WordTextView.OnWordClickCallback onWordClickCallback;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_cn_1)
        TextView tvCn1;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_cn_2)
        TextView tvCn2;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_en_1)
        WordTextView tvEn1;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_en_2)
        WordTextView tvEn2;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_explain)
        TextView tvExplain;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_index)
        TextView tvIndex;
        @BindView(R.id.tv_item_list_youdao_the21st_explain_wordClass)
        TextView tvTitle;

        public /* synthetic */ void lambda$new$0$The21stDictExplainListAdapter$ViewHolder(String str) {
            The21stDictExplainListAdapter.this.persenter.showWordSnackbar(str);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.onWordClickCallback = new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain.The21stDictExplainListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    The21stDictExplainListAdapter.ViewHolder.this.lambda$new$0$The21stDictExplainListAdapter$ViewHolder(str);
                }
            };
            ButterKnife.bind(this, itemView);
            this.tvEn1.setOnWordClickCallback(this.onWordClickCallback);
            this.tvEn2.setOnWordClickCallback(this.onWordClickCallback);
        }

        public void onBind(int position) {
            The21stDictExplainItem the21stDictExplainItem = The21stDictExplainListAdapter.this.itemList.get(position);
            this.tvTitle.setText(the21stDictExplainItem.getWordClass());
            TextView textView = this.tvIndex;
            textView.setText(the21stDictExplainItem.getIndex() + ".");
            this.tvExplain.setText(the21stDictExplainItem.getExplain());
            this.tvEn1.setText(the21stDictExplainItem.getEn1());
            this.tvCn1.setText(the21stDictExplainItem.getCn1());
            this.tvEn2.setText(the21stDictExplainItem.getEn2());
            this.tvCn2.setText(the21stDictExplainItem.getCn2());
            this.tvTitle.setVisibility(the21stDictExplainItem.getIndex().equals("1") ? 0 : 8);
            this.tvIndex.setVisibility(the21stDictExplainItem.getExplain().equals("") ? 8 : 0);
            this.tvExplain.setVisibility(the21stDictExplainItem.getExplain().equals("") ? 8 : 0);
            this.tvEn1.setVisibility(the21stDictExplainItem.getEn1().equals("") ? 8 : 0);
            this.tvCn1.setVisibility(the21stDictExplainItem.getCn1().equals("") ? 8 : 0);
            this.tvEn2.setVisibility(the21stDictExplainItem.getEn2().equals("") ? 8 : 0);
            this.tvCn2.setVisibility(the21stDictExplainItem.getCn2().equals("") ? 8 : 0);
        }
    }
}
