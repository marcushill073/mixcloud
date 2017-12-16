package com.example.mixcloud.adapters;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

public class DataBinderHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding viewDataBinding;

    public DataBinderHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.viewDataBinding = viewDataBinding;
    }

    public ViewDataBinding getViewDataBinding() {
        return viewDataBinding;
    }
}
