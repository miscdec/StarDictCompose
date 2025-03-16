package com.datong.dict.base;

import android.app.Activity;
import com.datong.dict.module.dict.DictActivity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ActivityManager {
    public static ActivityManager INSTANCE;
    private static List<Activity> activities;

    public static ActivityManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ActivityManager();
        }
        return INSTANCE;
    }

    private ActivityManager() {
        activities = new ArrayList();
    }

    public void add(Activity activity) {
        activities.add(activity);
    }

    public void remove(Activity activity) {
        activity.finish();
        activities.remove(activity);
    }

    public void removeAll() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }

    public void removeAllDictActivity() {
        ArrayList<Activity> arrayList = new ArrayList();
        for (Activity activity : activities) {
            if (activity instanceof DictActivity) {
                arrayList.add(activity);
            }
        }
        for (Activity activity2 : arrayList) {
            activity2.finish();
            activities.remove(activity2);
        }
        arrayList.clear();
    }
}
