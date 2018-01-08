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
import com.example.mixcloud.model.Home;
import com.example.mixcloud.model.Search;
import com.example.mixcloud.model.Type;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.ServiceModule;
import com.example.mixcloud.modules.ServiceModuleImpl;
import com.example.mixcloud.modules.TypeImpl;
import com.example.mixcloud.rest.RestServiceAPI;

import java.net.MalformedURLException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchFragment extends Fragment implements FeedAdapter.OnGetNextPageListener<Home>, TabLayout.OnTabSelectedListener {

    @Inject
    public RestServiceAPI restServiceAPI;
    @Inject
    public Type type;
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
        adapter = new HomePagerAdapter(getChildFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);
        setupTabViews();
        fetchFeedDetails(Home.POPULAR);
    }

    private void fetchFeedDetails(Home type) {
        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(new ServiceModuleImpl(getContext()), new TypeImpl(Search.CLOUDCAST)))
                .build();

        dataComponent.inject(this);

        Observable<Feed> popularFeed = restServiceAPI.fetchHomeFeed(type.getValue());
        popularFeed.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(feed -> {
                    FeedFragment<Home> fragment = adapter.getFeedFragment(type.ordinal());
                    fragment.setFeed(feed);
                }, error -> {
                    error.printStackTrace();
                });

    }

    @Override
    public void onGetNextPage(Home type, String url) {
        FeedFragment<Home> baseFragment = adapter.getFeedFragment(type.ordinal());
        if (baseFragment != null) {
            try {

                baseFragment.setLoading(true);
                restServiceAPI.fetchNextHomePage(type.getValue(), url)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(next -> {
                            baseFragment.addFeed(next);
                            baseFragment.setLoading(false);
                        }, error -> {
                            error.printStackTrace();
                            baseFragment.setLoading(false);
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

            imageView.setImageResource(type.getImageResource(i));
            tab.setCustomView(imageView);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if (adapter.getFeedFragment(tab.getPosition()) == null ||
                adapter.getFeedFragment(tab.getPosition()).isEmpty()) {
            Home type = Home.values()[tab.getPosition()];
            fetchFeedDetails(type);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}