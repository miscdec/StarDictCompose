package com.datong.dict.base.objects;

import java.util.List;

/* loaded from: classes.dex */
public class Notification {
    public String btnCancelText;
    public String btnOkText;
    public String content;
    public List<Highlight> highlights;
    public String title;
    public String url;

    /* loaded from: classes.dex */
    public class Highlight {
        public String color;
        public boolean isBold;
        public String text;

        public Highlight() {
        }
    }
}
