package com.example.mixcloud.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mixcloud.BR;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.adapters.FeedAdapter;
import com.example.mixcloud.adapters.FeedPagerAdapter;
import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.RestServiceAPI;
import com.example.mixcloud.modules.ServiceModule;
import com.example.mixcloud.modules.ServiceModuleImpl;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class HomeActivity extends AppCompatActivity implements FeedAdapter.OnGetNextPageListener, TabLayout.OnTabSelectedListener {

    @Inject
    public RestServiceAPI restServiceAPI;

    @BindView(R.id.feed)
    public ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private ViewDataBinding binding;
    private User mUser;
    private FeedPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(new ServiceModuleImpl(this)))
                .build();

        dataComponent.inject(this);
        ButterKnife.bind(this);
        adapter = new FeedPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        setupTabViews();

        restServiceAPI.fetchUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    mUser = user;
                    binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_drawer_header, null, false);
                    binding.setVariable(BR.user, mUser);
                    binding.executePendingBindings();

                    ListView listView = (ListView) findViewById(R.id.left_drawer);
                    DrawerAdapter adapter = new DrawerAdapter(this);
                    listView.addHeaderView(binding.getRoot());
                    listView.setAdapter(adapter);
                }, error -> {
                    error.printStackTrace();
                });

        fetchFeedDetails(Feed.Type.POPULAR);
        findViewById(R.id.menu_button).setOnClickListener(view -> {
            DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                drawerLayout.closeDrawer(Gravity.LEFT);
            } else {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }

    @BindingAdapter("android:src")
    public static void downloadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    private void fetchFeedDetails(Feed.Type type) {
        Observable<Feed> popularFeed = restServiceAPI.fetchPopularFeed(type.getValue());
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
    public void onGetNextPage(Feed.Type type, String url) {
        FeedFragment feedFragment = adapter.getFeedFragment(type.ordinal());
        if (feedFragment != null) {
            try {

                feedFragment.setLoading(true);
                restServiceAPI.fetchNextFeedPage(type.getValue(), url)
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
            View view = LayoutInflater.from(this).inflate(R.layout.item_tab, null);
            AppCompatImageView imageView = (AppCompatImageView) view.findViewById(R.id.tab_image);
            switch (i) {
                case 0:
                    imageView.setImageResource(R.drawable.ic_iconmonstr_whats_hot_1);
                    break;
                case 1:
                    imageView.setImageResource(R.drawable.ic_iconmonstr_favorite_2);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_iconmonstr_star_5);
                    break;
            }

            tab.setCustomView(imageView);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        if (adapter.getFeedFragment(tab.getPosition()) == null ||
                adapter.getFeedFragment(tab.getPosition()).isEmpty()) {
            Feed.Type type = Feed.Type.values()[tab.getPosition()];
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
