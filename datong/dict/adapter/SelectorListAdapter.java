package com.datong.dict.module.dict.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.datong.dict.R;
import com.datong.dict.databinding.ItemListTranslateSelectorBinding;

import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

/* loaded from: classes.dex */
public class SelectorListAdapter extends RecyclerView.Adapter<SelectorListAdapter.ViewHolder> {
    public OnItemClickCallback callback;
    private LayoutInflater inflater;
    private List<SelectorItem> selectorItems;

    /* loaded from: classes.dex */
    public interface OnItemClickCallback {
        void callback(int position, SelectorItem item);
    }


    public SelectorListAdapter(Context context, List<SelectorItem> selectorItems) {
        this.selectorItems = selectorItems;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(this.inflater.inflate(R.layout.item_list_translate_selector, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.selectorItems.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemListTranslateSelectorBinding binding;
        CircleImageView imgLogo;
        private SelectorItem item;

        public ViewHolder(View itemView) {
            super(itemView.getRootView());
            this.binding = ItemListTranslateSelectorBinding.bind(itemView);
            imgLogo = binding.imgItemListTranslateSelectorLogo;
            this.imgLogo.setOnClickListener(view -> {
                if (SelectorListAdapter.this.callback != null) {
                    SelectorListAdapter.this.callback.callback(getLayoutPosition(), ViewHolder.this.item);
                }
            });
        }

        /* synthetic */

        public void onBind(int position) {
            SelectorItem selectorItem = SelectorListAdapter.this.selectorItems.get(position);
            this.item = selectorItem;
            this.imgLogo.setImageResource(selectorItem.getLogo());
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback callback) {
        this.callback = callback;
    }
}
