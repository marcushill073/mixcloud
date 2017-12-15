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
import com.example.mixcloud.model.Paging;
import com.example.mixcloud.model.Track;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<DataBinderHolder> {

    private final OnGetNextPageListener listener;
    private final Feed.Type type;
    private Feed feed;
    private String nextPath;
    private final int pageSize = 20;

    public FeedAdapter(Feed.Type type, OnGetNextPageListener listener) {
        this.type = type;
        this.feed = Feed.builder().data(new ArrayList<>())
                .paging(Paging.builder().next("").build()).build();
        this.listener = listener;
        nextPath  = feed.paging().next();
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

    public void addPage(Feed nextFeed) {
        nextPath = nextFeed.paging().next();
            List<Track> tracks = new ArrayList<>(nextFeed.data());
        this.feed.data().addAll(tracks);

    }

    public void notifyLastVisiblePosition(int position) {
        if ((feed.data().size() - position) <= pageSize && feed.paging().next() != null) {
            listener.onGetNextPage(type, nextPath);
        }
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
        nextPath = feed.paging().next();
    }

    public interface OnGetNextPageListener {
        void onGetNextPage(Feed.Type type, String url);
    }
}
