package com.example.mixcloud.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mixcloud.BR;
import com.example.mixcloud.R;
import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.UserData;
import com.example.mixcloud.model.UserFeed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserFeedAdapter<T extends Serializable> extends RecyclerView.Adapter<DataBinderHolder> {

    private final OnGetNextPageListener<T> onGetNextPageListener;
    private final T type;
    private final OnPlayListener onPlayListener;
    private List<Track> feed;
    private String nextPath;
    private final int pageSize = 20;

    public UserFeedAdapter(T type, OnGetNextPageListener<T> onGetNextPageListener, OnPlayListener onPlayListener) {
        this.type = type;
        this.feed = new ArrayList<>();
        this.onGetNextPageListener = onGetNextPageListener;
        this.onPlayListener = onPlayListener;
    }

    @Override
    public DataBinderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_popular_feed, parent, false);
        return new DataBinderHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(DataBinderHolder holder, int position) {
        holder.getViewDataBinding().setVariable(BR.track, feed.get(position));
        holder.getViewDataBinding().setVariable(BR.listener, onPlayListener);

    }

    @Override
    public int getItemCount() {
        return feed.size();
    }

    public void addPage(UserFeed nextFeed) {
        nextPath = nextFeed.paging().next();
        for(UserData userData: nextFeed.data()) {
            if(userData.cloudCasts() != null) {
                List<Track> tracks = new ArrayList<>(userData.cloudCasts());
                this.feed.addAll(tracks);
            }
        }
    }

    public void notifyLastVisiblePosition(int position) {
        if ((feed.size() - position) <= pageSize && nextPath != null) {
            onGetNextPageListener.onGetNextPage(type, nextPath);
        }
    }

    public void setFeed(UserFeed feed) {
        for(UserData userData: feed.data()) {
            if(userData.cloudCasts() != null) {
                List<Track> tracks = new ArrayList<>(userData.cloudCasts());
                this.feed.addAll(tracks);
            }
        }
        if(feed.paging() != null) {
            nextPath = feed.paging().next();
        }
    }

    public interface OnGetNextPageListener<T extends Serializable> {
        void onGetNextPage(T type, String url);
    }
}
