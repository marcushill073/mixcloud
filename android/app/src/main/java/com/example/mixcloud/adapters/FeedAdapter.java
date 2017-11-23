package com.example.mixcloud.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mixcloud.BR;
import com.example.mixcloud.R;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<DataBinderHolder>{

    private Feed feed;

    public FeedAdapter(Feed feed) {
        this.feed = feed;
    }

    @Override
    public DataBinderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_popular_feed, parent, false);
        return new DataBinderHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(DataBinderHolder holder, int position) {
        holder.getViewDataBinding().setVariable(BR.track, feed.data().get(position));

    }

    @Override
    public int getItemCount() {
        return feed.data().size();
    }
}
