package com.example.mixcloud.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.mixcloud.R;
import com.example.mixcloud.adapters.FeedAdapter;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.Type;

public class FeedFragment extends Fragment {

    public static final String FEED = ".feed";
    private static final String ON_NEXT_PAGE_LISTENER = ".onNextPageListener";
    private FeedAdapter feedAdapter;
    Type type;

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    private boolean loading;
    private RecyclerView recyclerView;

    private final ViewTreeObserver.OnScrollChangedListener listener = new ViewTreeObserver.OnScrollChangedListener() {

        @Override
        public void onScrollChanged() {

            if (!loading && feedAdapter != null && feedAdapter.getItemCount() != 0) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearLayoutManager.findLastVisibleItemPosition();
                feedAdapter.notifyLastVisiblePosition(position);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);
        type = (Type) getArguments().getSerializable(FEED);

        FeedAdapter.OnGetNextPageListener listener = (FeedAdapter.OnGetNextPageListener) getParentFragment();

        feedAdapter = new FeedAdapter(type, listener, (OnPlayListener) getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.getViewTreeObserver().addOnScrollChangedListener(listener);
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView.getViewTreeObserver().removeOnScrollChangedListener(listener);
    }

    public void addFeed(@NonNull Feed feed) {

        feedAdapter.addPage(feed);
        feedAdapter.notifyDataSetChanged();

    }

    public void setFeed(Feed feed) {
        feedAdapter.setFeed(feed);
        feedAdapter.notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return feedAdapter.getItemCount() == 0;
    }

    public Type getType() {
        return getArguments().getParcelable(FEED);
    }

    public static FeedFragment newInstance(Type type) {
        FeedFragment fragment = new FeedFragment();
        Bundle args = new Bundle();
        args.putParcelable(FEED, type);
        fragment.setArguments(args);
        return fragment;
    }
}
 