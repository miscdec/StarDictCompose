package com.datong.dict.module.dict.en.youdao.items.authority.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.datong.dict.R;
import com.datong.dict.module.dict.en.youdao.YoudaoContract;
import com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityListAdapter;
import com.datong.dict.utils.SoundUtil;
import com.datong.dict.utils.SpannableUtil;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class AuthorityListAdapter extends RecyclerView.Adapter<AuthorityListAdapter.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<AuthorityItem> itemList;
    YoudaoContract.Presenter presenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.haeder = Utils.findRequiredView(source, R.id.item_list_header, "field 'haeder'");
            target.footer = Utils.findRequiredView(source, R.id.item_list_footer, "field 'footer'");
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_authority_index, "field 'tvIndex'", TextView.class);
            target.tvEn = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_authority_en, "field 'tvEn'", WordTextView.class);
            target.tvFrom = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_authority_from, "field 'tvFrom'", TextView.class);
            target.imgSound = (ImageView) Utils.findRequiredViewAsType(source, R.id.img_item_list_youdao_authority_sound, "field 'imgSound'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.haeder = null;
            viewHolder.footer = null;
            viewHolder.tvIndex = null;
            viewHolder.tvEn = null;
            viewHolder.tvFrom = null;
            viewHolder.imgSound = null;
        }
    }

    public AuthorityListAdapter(Context context, List<AuthorityItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.presenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_authority, parent, false));
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
        @BindView(R.id.item_list_footer)
        View footer;
        @BindView(R.id.item_list_header)
        View haeder;
        @BindView(R.id.img_item_list_youdao_authority_sound)
        ImageView imgSound;
        @BindView(R.id.tv_item_list_youdao_authority_en)
        WordTextView tvEn;
        @BindView(R.id.tv_item_list_youdao_authority_from)
        TextView tvFrom;
        @BindView(R.id.tv_item_list_youdao_authority_index)
        TextView tvIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.imgSound.setColorFilter(AuthorityListAdapter.this.context.getResources().getColor(R.C0079color.red_datong));
            this.tvEn.setOnWordClickCallback(new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityListAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    AuthorityListAdapter.ViewHolder.this.lambda$new$0$AuthorityListAdapter$ViewHolder(str);
                }
            });
            this.imgSound.setOnClickListener(new View.OnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AuthorityListAdapter.ViewHolder.this.lambda$new$1$AuthorityListAdapter$ViewHolder(view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$AuthorityListAdapter$ViewHolder(String str) {
            AuthorityListAdapter.this.presenter.showWordSnackbar(str);
        }

        public /* synthetic */ void lambda$new$1$AuthorityListAdapter$ViewHolder(View view) {
            SoundUtil.getInstance().text(this.tvEn.getText().toString()).url("").soundType(2).languageType(1).play();
        }

        public void onBind(int position) {
            AuthorityItem authorityItem = AuthorityListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(authorityItem.getIndex() + ".");
            this.tvFrom.setText(authorityItem.getFrom());
            SpannableUtil.span(this.tvEn, authorityItem.getEn(), AuthorityListAdapter.this.presenter.getWordText(), AuthorityListAdapter.this.context.getResources().getColor(R.C0079color.red_datong));
            this.footer.setVisibility(position == AuthorityListAdapter.this.itemList.size() + (-1) ? View.VISIBLE : 8);
        }
    }
}
