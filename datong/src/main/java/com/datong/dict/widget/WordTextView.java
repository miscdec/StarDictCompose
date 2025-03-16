package com.datong.dict.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatTextView;
import com.datong.dict.R;

/* loaded from: classes.dex */
public class WordTextView extends AppCompatTextView {
    private OnWordClickCallback callback;
    private long t1;
    private long t2;

    /* loaded from: classes.dex */
    public interface OnWordClickCallback {
        void callback(String wordText);
    }

    public WordTextView(Context context, AttributeSet attrs) {
        super(new ContextThemeWrapper(context, (int) R.style.MyUIStyle), attrs, 0);
        setTextIsSelectable(true);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            this.t1 = System.currentTimeMillis();
        } else if (event.getAction() == 1) {
            long currentTimeMillis = System.currentTimeMillis();
            this.t2 = currentTimeMillis;
            if (currentTimeMillis - this.t1 < 400) {
                String charSequence = getText().toString();
                int offsetForPosition = getOffsetForPosition(event.getX(), event.getY());
                String lowerCase = charSequence.substring(charSequence.lastIndexOf(" ", offsetForPosition) == -1 ? 0 : charSequence.lastIndexOf(" ", offsetForPosition), charSequence.indexOf(" ", offsetForPosition) == -1 ? charSequence.length() : charSequence.indexOf(" ", offsetForPosition)).toLowerCase();
                if (lowerCase.length() <= 0 || lowerCase.trim().equals("")) {
                    return super.onTouchEvent(event);
                }
                OnWordClickCallback onWordClickCallback = this.callback;
                if (onWordClickCallback != null) {
                    onWordClickCallback.callback(removeSpecialChar(lowerCase));
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private String removeSpecialChar(String str) {
        char[] charArray;
        for (char c : str.toCharArray()) {
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                str = str.replace(c + "", "");
            }
        }
        return str;
    }

    public void setOnWordClickCallback(OnWordClickCallback callback) {
        this.callback = callback;
    }
}
