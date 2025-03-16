package com.datong.dict.module.dict.en.youdao.items.collins.adapter;

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
import com.datong.dict.utils.FontUtil;
import com.datong.dict.utils.SettingUtil;
import java.util.List;

/* loaded from: classes.dex */
public class CollinsListAdapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<CollinsItem> itemList;
    YoudaoContract.Presenter persenter;

    /* loaded from: classes.dex */
    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target, View source) {
            this.target = target;
            target.tvTitle = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_title, "field 'tvTitle'", TextView.class);
            target.tvWord = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_word, "field 'tvWord'", TextView.class);
            target.tvPhonetic = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_phonetic, "field 'tvPhonetic'", TextView.class);
            target.tvStarCount = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_starCount, "field 'tvStarCount'", TextView.class);
            target.tvExam = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_exam, "field 'tvExam'", TextView.class);
            target.tvShape = (TextView) Utils.findRequiredViewAsType(source, R.id.tv_item_list_youdao_collins_shape, "field 'tvShape'", TextView.class);
            target.listExplain = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.list_item_list_youdao_collins_explain, "field 'listExplain'", RecyclerView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.tvTitle = null;
            viewHolder.tvWord = null;
            viewHolder.tvPhonetic = null;
            viewHolder.tvStarCount = null;
            viewHolder.tvExam = null;
            viewHolder.tvShape = null;
            viewHolder.listExplain = null;
        }
    }

    public CollinsListAdapter(Context context, List<CollinsItem> itemList, YoudaoContract.Presenter persenter) {
        this.context = context;
        this.itemList = itemList;
        this.persenter = persenter;
        this.inflater = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.item_list_youdao_collins, parent, false));
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
        @BindView(R.id.list_item_list_youdao_collins_explain)
        RecyclerView listExplain;
        @BindView(R.id.tv_item_list_youdao_collins_exam)
        TextView tvExam;
        @BindView(R.id.tv_item_list_youdao_collins_phonetic)
        TextView tvPhonetic;
        @BindView(R.id.tv_item_list_youdao_collins_shape)
        TextView tvShape;
        @BindView(R.id.tv_item_list_youdao_collins_starCount)
        TextView tvStarCount;
        @BindView(R.id.tv_item_list_youdao_collins_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_list_youdao_collins_word)
        TextView tvWord;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initRecyclerView();
        }

        private void initRecyclerView() {
            this.listExplain.setLayoutManager(new LinearLayoutManager(CollinsListAdapter.this.context, 1, false));
            this.listExplain.setItemAnimator(new DefaultItemAnimator());
            this.listExplain.setNestedScrollingEnabled(false);
            this.listExplain.setHasFixedSize(true);
        }

        public void onBind(int position) {
            CollinsItem collinsItem = CollinsListAdapter.this.itemList.get(position);
            this.tvTitle.setText(collinsItem.getTitle());
            this.tvWord.setText(collinsItem.getWord());
            this.tvStarCount.setText(collinsItem.getStarCount());
            this.tvExam.setText(collinsItem.getExam());
            this.tvShape.setText(collinsItem.getShape());
            this.listExplain.setAdapter(new CollinsExplainListAdapter(CollinsListAdapter.this.context, collinsItem.getExplainItemList(), CollinsListAdapter.this.persenter));
            if (SettingUtil.isFixPhoneticFont()) {
                FontUtil.setFont(this.tvPhonetic, R.font.lingoes, FontUtil.FONT_PATH_LINGOES);
            } else {
                this.tvPhonetic.setText(collinsItem.getPhonetic());
            }
            this.tvTitle.setVisibility(collinsItem.getTitle().equals("") ? 8 : 0);
            this.tvPhonetic.setVisibility(collinsItem.getPhonetic().equals("") ? 8 : 0);
            this.tvStarCount.setVisibility(collinsItem.getStarCount().equals("") ? 8 : 0);
            this.tvExam.setVisibility(collinsItem.getExam().equals("") ? 8 : 0);
            this.tvShape.setVisibility(collinsItem.getShape().equals("") ? 8 : 0);
        }
    }
}
