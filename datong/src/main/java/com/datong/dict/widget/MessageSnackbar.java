package com.datong.dict.widget;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.datong.dict.R;
import com.google.android.material.snackbar.Snackbar;

/* loaded from: classes.dex */
public class MessageSnackbar {
    public static final String COLOR_DARK = "#3D3D3B";
    public static final String COLOR_ERROR = "#D77A61";
    public static final String COLOR_LIGHT = "#FFFFFF";
    public static MessageSnackbar INSTANCE;
    private static Snackbar snackbar;
    private OnDismissCallback onDismissCallback;

    /* loaded from: classes.dex */
    public interface OnDismissCallback {
        void callback();
    }

    private MessageSnackbar(View view) {
        Snackbar make = Snackbar.make(view, "", -1);
        snackbar = make;
        make.addCallback(new Snackbar.Callback() { // from class: com.datong.dict.widget.MessageSnackbar.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.google.android.material.snackbar.Snackbar.Callback, com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
            public void onDismissed(Snackbar transientBottomBar, int event) {
                if (MessageSnackbar.this.onDismissCallback != null) {
                    MessageSnackbar.this.onDismissCallback.callback();
                }
                MessageSnackbar.dismiss();
            }
        });

        TextView textView = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setLineSpacing(view.getResources().getDimension(R.dimen.y10), textView.getLineSpacingMultiplier());
    }

    public static MessageSnackbar with(View view) {
        if (INSTANCE == null) {
            INSTANCE = new MessageSnackbar(view);
        }
        return INSTANCE;
    }

    public MessageSnackbar message(String message) {
        snackbar.setText(message);
        return INSTANCE;
    }

    public MessageSnackbar bgColor(String color) {
        snackbar.getView().setBackgroundColor(Color.parseColor(color));
        return INSTANCE;
    }

    public MessageSnackbar btn(String btnText, View.OnClickListener listener) {
        snackbar.setAction(btnText, listener);
        snackbar.setActionTextColor(-3355444);
        return INSTANCE;
    }

    public MessageSnackbar duration(int duration) {
        snackbar.setDuration(duration);
        return INSTANCE;
    }

    public MessageSnackbar onDismiss(OnDismissCallback onDismissCallback) {
        this.onDismissCallback = onDismissCallback;
        return INSTANCE;
    }

    public void show() {
        snackbar.show();
    }

    public static void dismiss() {
        if (INSTANCE != null) {
            snackbar.dismiss();
            INSTANCE = null;
        }
    }
}
