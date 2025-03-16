package com.datong.dict.module.dict.en.youdao.items.collins.adapter;

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
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsExplainListAdapter;
import com.datong.dict.utils.SpannableUtil;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class CollinsExplainListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<CollinsExplainItem> itemList;
    YoudaoContract.Presenter presenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_index, "field 'tvIndex'", TextView.class);
            target.tvExplain = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_explain, "field 'tvExplain'", WordTextView.class);
            target.tvEn1 = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_en_1, "field 'tvEn1'", WordTextView.class);
            target.tvCn1 = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_cn_1, "field 'tvCn1'", TextView.class);
            target.tvEn2 = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_en_2, "field 'tvEn2'", WordTextView.class);
            target.tvCn2 = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_explain_cn_2, "field 'tvCn2'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvIndex = null;
            viewHolder.tvExplain = null;
            viewHolder.tvEn1 = null;
            viewHolder.tvCn1 = null;
            viewHolder.tvEn2 = null;
            viewHolder.tvCn2 = null;
        }
    }

    public CollinsExplainListAdapter(Context context, List<CollinsExplainItem> itemList, YoudaoContract.Presenter presenter) {
        this.context = context;
        this.itemList = itemList;
        this.presenter = presenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_collins_explain, parent, false));
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
        @BindView(R.id.tv_item_list_youdao_collins_explain_cn_1)
        TextView tvCn1;
        @BindView(R.id.tv_item_list_youdao_collins_explain_cn_2)
        TextView tvCn2;
        @BindView(R.id.tv_item_list_youdao_collins_explain_en_1)
        WordTextView tvEn1;
        @BindView(R.id.tv_item_list_youdao_collins_explain_en_2)
        WordTextView tvEn2;
        @BindView(R.id.tv_item_list_youdao_collins_explain_explain)
        WordTextView tvExplain;
        @BindView(R.id.tv_item_list_youdao_collins_explain_index)
        TextView tvIndex;

        public /* synthetic */ void lambda$new$0$CollinsExplainListAdapter$ViewHolder(String str) {
            CollinsExplainListAdapter.this.presenter.showWordSnackbar(str);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            this.onWordClickCallback = new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsExplainListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    CollinsExplainListAdapter.ViewHolder.this.lambda$new$0$CollinsExplainListAdapter$ViewHolder(str);
                }
            };
            ButterKnife.bind(this, itemView);
            this.tvExplain.setOnWordClickCallback(this.onWordClickCallback);
            this.tvEn1.setOnWordClickCallback(this.onWordClickCallback);
            this.tvEn2.setOnWordClickCallback(this.onWordClickCallback);
        }

        public void onBind(int position) {
            CollinsExplainItem collinsExplainItem = CollinsExplainListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(collinsExplainItem.getIndex() + ".");
            this.tvExplain.setText(collinsExplainItem.getExplain());
            this.tvCn1.setText(collinsExplainItem.getCn1());
            this.tvCn2.setText(collinsExplainItem.getCn2());
            SpannableUtil.span(this.tvEn1, collinsExplainItem.getEn1(), CollinsExplainListAdapter.this.presenter.getWordText(), this.tvEn1.getTextColors().getDefaultColor());
            SpannableUtil.span(this.tvEn2, collinsExplainItem.getEn2(), CollinsExplainListAdapter.this.presenter.getWordText(), this.tvEn1.getTextColors().getDefaultColor());
            this.tvEn1.setVisibility(collinsExplainItem.getEn1().equals("") ? 8 : 0);
            this.tvCn1.setVisibility(collinsExplainItem.getCn1().equals("") ? 8 : 0);
            this.tvEn2.setVisibility(collinsExplainItem.getEn2().equals("") ? 8 : 0);
            this.tvCn2.setVisibility(collinsExplainItem.getCn2().equals("") ? 8 : 0);
        }
    }
}
