package com.example.mixcloud.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mixcloud.R;
import com.example.mixcloud.adapters.FeedAdapter;
import com.example.mixcloud.adapters.HomePagerAdapter;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Type;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.RestServiceAPI;
import com.example.mixcloud.modules.ServiceModule;
import com.example.mixcloud.modules.ServiceModuleImpl;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends Fragment implements FeedAdapter.OnGetNextPageListener, TabLayout.OnTabSelectedListener {

    private static final String HOME_FEED = ".home_feed";
    @Inject
    public RestServiceAPI restServiceAPI;
    private HomePagerAdapter adapter;

    @BindView(R.id.feed)
    public ViewPager viewPager;
    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Map<Integer, FeedFragment> map = null;
        if (savedInstanceState == null) {
            fetchFeedDetails(Type.POPULAR);
        } else {
            map = new HashMap<>();
            for (int i = 0; i < Type.values().length; i++) {
                map.put(i, savedInstanceState.getParcelable(Type.values()[i].getValue()));
            }
        }
        adapter = new HomePagerAdapter(getChildFragmentManager(), map);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
        setupTabViews();

    }

    private void fetchFeedDetails(Type type) {
        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(new ServiceModuleImpl(getContext())))
                .build();

        dataComponent.inject(this);

        Observable<Feed> popularFeed = restServiceAPI.fetchHomeFeed(type.getValue());
        popularFeed.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(feed -> {
                    FeedFragment feedFragment = adapter.getFeedFragment(type.ordinal());
                    feedFragment.setFeed(feed);
                }, error -> {
                    error.printStackTrace();
                });

    }

    @Override
    public void onGetNextPage(Object obj, String url) {
        Type type = (Type) obj;
        FeedFragment feedFragment = adapter.getFeedFragment(type.ordinal());
        if (feedFragment != null) {
            try {

                feedFragment.setLoading(true);
                restServiceAPI.fetchNextHomePage(type.getValue(), url)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(next -> {
                            feedFragment.addFeed(next);
                            feedFragment.setLoading(false);
                        }, error -> {
                            error.printStackTrace();
                            feedFragment.setLoading(false);
                        });

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupTabViews() {

        for (int i = 0; i < tabLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null);
            AppCompatImageView imageView = (AppCompatImageView) view.findViewById(R.id.tab_image);

            imageView.setImageResource(Type.getImageResource(i));
            tab.setCustomView(imageView);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if (adapter.getFeedFragment(tab.getPosition()) == null ||
                adapter.getFeedFragment(tab.getPosition()).isEmpty()) {
            Type type = Type.values()[tab.getPosition()];
            fetchFeedDetails(type);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Map<Integer, FeedFragment> map = adapter.getCurrentFeed();
        Iterator<FeedFragment> values = map.values().iterator();
        while (values.hasNext()) {
            FeedFragment feedFragment = values.next();
            outState.putParcelable(feedFragment.getType().getValue(), feedFragment.getFeed());
        }

    }


}
