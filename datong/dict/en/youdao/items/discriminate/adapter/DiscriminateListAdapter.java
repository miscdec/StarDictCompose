package com.datong.dict.module.dict.en.youdao.items.discriminate.adapter;

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
import com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateListAdapter;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class DiscriminateListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<DiscriminateItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_discriminate_index, "field 'tvIndex'", TextView.class);
            target.tvTitle = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_discriminate_title, "field 'tvTitle'", WordTextView.class);
            target.tvSubTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_discriminate_subTitle, "field 'tvSubTitle'", TextView.class);
            target.tvContent = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_discriminate_content, "field 'tvContent'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvIndex = null;
            viewHolder.tvTitle = null;
            viewHolder.tvSubTitle = null;
            viewHolder.tvContent = null;
        }
    }

    public DiscriminateListAdapter(Context context, List<DiscriminateItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_discriminate, parent, false));
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
        @BindView(R.id.tv_item_list_youdao_discriminate_content)
        TextView tvContent;
        @BindView(R.id.tv_item_list_youdao_discriminate_index)
        TextView tvIndex;
        @BindView(R.id.tv_item_list_youdao_discriminate_subTitle)
        TextView tvSubTitle;
        @BindView(R.id.tv_item_list_youdao_discriminate_title)
        WordTextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.tvTitle.setOnWordClickCallback(new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    DiscriminateListAdapter.ViewHolder.this.lambda$new$0$DiscriminateListAdapter$ViewHolder(str);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$DiscriminateListAdapter$ViewHolder(String str) {
            DiscriminateListAdapter.this.persenter.showWordSnackbar(str);
        }

        public void onBind(int position) {
            DiscriminateItem discriminateItem = DiscriminateListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(discriminateItem.getIndex() + ".");
            this.tvTitle.setText(discriminateItem.getTitle().replace(",", "/"));
            this.tvSubTitle.setText(discriminateItem.getSubTitle());
            this.tvContent.setText(discriminateItem.getCotent());
            this.tvSubTitle.setVisibility(discriminateItem.getSubTitle().equals("") ? 8 : 0);
        }
    }
}
