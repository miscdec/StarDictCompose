package com.datong.dict.module.dict.en.youdao.items.bilingual.adapter;

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
import com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualListAdapter;
import com.datong.dict.utils.SoundUtil;
import com.datong.dict.utils.SpannableUtil;
import com.datong.dict.widget.WordTextView;
import java.util.List;

/* loaded from: classes.dex */
public class BilingualListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<BilingualItem> itemList;
    YoudaoContract.Presenter presenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.haeder = Utils.findRequiredView(source, R.id.item_list_header, "field 'haeder'");
            target.footer = Utils.findRequiredView(source, R.id.item_list_footer, "field 'footer'");
            target.tvIndex = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_bilingual_index, "field 'tvIndex'", TextView.class);
            target.tvEn = (WordTextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_bilingual_en, "field 'tvEn'", WordTextView.class);
            target.tvCn = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_bilingual_cn, "field 'tvCn'", TextView.class);
            target.tvFrom = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_bilingual_from, "field 'tvFrom'", TextView.class);
            target.imgSound = (ImageView) Utils.findRequiredViewAsType(source, R.id.img_item_list_youdao_bilingual_sound, "field 'imgSound'", ImageView.class);
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
            viewHolder.tvCn = null;
            viewHolder.tvFrom = null;
            viewHolder.imgSound = null;
        }
    }

    public BilingualListAdapter(Context context, List<BilingualItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.presenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_bilingual, parent, false));
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
        @BindView(R.id.img_item_list_youdao_bilingual_sound)
        ImageView imgSound;
        @BindView(R.id.tv_item_list_youdao_bilingual_cn)
        TextView tvCn;
        @BindView(R.id.tv_item_list_youdao_bilingual_en)
        WordTextView tvEn;
        @BindView(R.id.tv_item_list_youdao_bilingual_from)
        TextView tvFrom;
        @BindView(R.id.tv_item_list_youdao_bilingual_index)
        TextView tvIndex;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.imgSound.setColorFilter(BilingualListAdapter.this.context.getResources().getColor(R.C0079color.red_datong));
            this.tvEn.setOnWordClickCallback(new WordTextView.OnWordClickCallback() { // from class: com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualListAdapter$ViewHolder$$ExternalSyntheticLambda1
                @Override // com.datong.dict.widget.WordTextView.OnWordClickCallback
                public final void callback(String str) {
                    BilingualListAdapter.ViewHolder.this.lambda$new$0$BilingualListAdapter$ViewHolder(str);
                }
            });
            this.imgSound.setOnClickListener(new View.OnClickListener() { // from class: com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualListAdapter$ViewHolder$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BilingualListAdapter.ViewHolder.this.lambda$new$1$BilingualListAdapter$ViewHolder(view);
                }
            });
        }

        public /* synthetic */ void lambda$new$0$BilingualListAdapter$ViewHolder(String str) {
            BilingualListAdapter.this.presenter.showWordSnackbar(str);
        }

        public /* synthetic */ void lambda$new$1$BilingualListAdapter$ViewHolder(View view) {
            SoundUtil.getInstance().text(this.tvEn.getText().toString()).url("").soundType(2).languageType(1).play();
        }

        public void onBind(int position) {
            BilingualItem bilingualItem = BilingualListAdapter.this.itemList.get(position);
            TextView textView = this.tvIndex;
            textView.setText(bilingualItem.getIndex() + ".");
            this.tvCn.setText(bilingualItem.getCn());
            this.tvFrom.setText(bilingualItem.getFrom());
            SpannableUtil.span(this.tvEn, bilingualItem.getEn(), BilingualListAdapter.this.presenter.getWordText(), BilingualListAdapter.this.context.getResources().getColor(R.C0079color.red_datong));
            this.footer.setVisibility(position == BilingualListAdapter.this.itemList.size() + (-1) ? 0 : 8);
        }
    }
}
