package com.datong.dict.widget;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.appcompat.widget.ListPopupWindow;
import com.datong.dict.R;
import com.datong.dict.utils.ScreenUtil;

/* loaded from: classes.dex */
public class Menu {
    private static Menu INSTANCE;
    private Context context;
    private ListPopupWindow popupWindow;

    /* loaded from: classes.dex */
    public interface OnItemClickCallback {
        void callback(int position);
    }

    public static Menu with(Context context) {
        Menu menu = new Menu(context);
        INSTANCE = menu;
        return menu;
    }

    private Menu(Context context) {
        this.context = context;
        ListPopupWindow listPopupWindow = new ListPopupWindow(context);
        this.popupWindow = listPopupWindow;
        listPopupWindow.setWidth(ScreenUtil.dip2px(context, 180.0f));
        this.popupWindow.setHeight(-2);
        this.popupWindow.setDropDownGravity(5);
        this.popupWindow.setModal(true);
    }

    public Menu anchor(View anchorView) {
        this.popupWindow.setAnchorView(anchorView);
        return INSTANCE;
    }

    public Menu items(String[] menuItems) {
        this.popupWindow.setAdapter(new ArrayAdapter(this.context, (int) R.layout.item_menu_list_1, menuItems));
        return INSTANCE;
    }

    public Menu click(final OnItemClickCallback onItemClickCallback) {
        this.popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.datong.dict.widget.Menu$$ExternalSyntheticLambda0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                Menu.this.lambda$click$0$Menu(onItemClickCallback, adapterView, view, i, j);
            }
        });
        return INSTANCE;
    }

    public /* synthetic */ void lambda$click$0$Menu(OnItemClickCallback onItemClickCallback, AdapterView adapterView, View view, int i, long j) {
        this.popupWindow.dismiss();
        if (onItemClickCallback != null) {
            onItemClickCallback.callback(i);
        }
    }

    public void show() {
        this.popupWindow.show();
    }
}
