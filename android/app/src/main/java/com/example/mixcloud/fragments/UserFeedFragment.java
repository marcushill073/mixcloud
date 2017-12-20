package com.example.mixcloud.fragments;

import android.os.Bundle;
import android.os.Parcelable;
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
import com.example.mixcloud.adapters.UserFeedAdapter;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Navigation;
import com.example.mixcloud.model.Type;
import com.example.mixcloud.model.UserFeed;

import static com.example.mixcloud.fragments.FeedFragment.FEED_NAV;

public class UserFeedFragment extends Fragment {


    private UserFeedAdapter adapter;

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    private boolean loading;
    private RecyclerView recyclerView;

    private static final String TYPE = ".type";
    private final ViewTreeObserver.OnScrollChangedListener listener = new ViewTreeObserver.OnScrollChangedListener() {

        @Override
        public void onScrollChanged() {

            if (!loading && adapter != null && adapter.getItemCount() != 0) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearLayoutManager.findLastVisibleItemPosition();
                adapter.notifyLastVisiblePosition(position);
            }

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        Navigation nav = (Navigation) getArguments().getSerializable(TYPE);
        adapter = new UserFeedAdapter(nav, (UserFeedAdapter.OnGetNextPageListener) getActivity(), (UserFeedAdapter.OnPlayListener) getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return view;

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

    public void addFeed(@NonNull UserFeed feed) {

        adapter.addPage(feed);
        adapter.notifyDataSetChanged();

    }

    public void setFeed(UserFeed feed) {
        adapter.setFeed(feed);
        adapter.notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return adapter.getItemCount() == 0;
    }

    public Type getType() {
        return getArguments().getParcelable(TYPE);
    }

}
 