package com.datong.dict.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {
    private int layoutResId;

    private String title = "";

    public abstract void handleEvents();

    public abstract void initVariables();

    public abstract void initViews(Bundle savedInstanceState);

    public abstract void onLoadData();


    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.layoutResId, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariables();
        initViews(savedInstanceState);
        handleEvents();
        onLoadData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
    }

    public void setContentView(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
