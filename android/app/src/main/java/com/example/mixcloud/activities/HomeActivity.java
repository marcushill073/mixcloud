package com.example.mixcloud.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class HomeActivity extends AppCompatActivity implements FeedAdapter.OnGetNextPageListener {

    @Inject
    public RestServiceAPI restServiceAPI;
    private ViewDataBinding binding;
    private User mUser;
    private FeedAdapter feedAdapter;
    private boolean loading;
    private RecyclerView recyclerView;


    private final ViewTreeObserver.OnScrollChangedListener listener = new ViewTreeObserver.OnScrollChangedListener() {

        @Override
        public void onScrollChanged() {

            if (!loading) {
                loading = true;
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position = linearLayoutManager.findLastVisibleItemPosition();
                feedAdapter.notifyLastVisiblePosition(position);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(new ServiceModuleImpl(this)))
                .build();

        dataComponent.inject(this);


        restServiceAPI.fetchUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((user) -> {
                    mUser = user;
                    binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_drawer_header, null, false);
                    binding.setVariable(BR.user, mUser);
                    binding.executePendingBindings();

                    ListView listView = (ListView) findViewById(R.id.left_drawer);
                    DrawerAdapter adapter = new DrawerAdapter(this);
                    listView.addHeaderView(binding.getRoot());
                    listView.setAdapter(adapter);
                });

        Observable<Feed> popularFeed = restServiceAPI.fetchPopularFeed();
        popularFeed.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(feed -> {
                    recyclerView = (RecyclerView) HomeActivity.this.findViewById(R.id.feed);
                    recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                    feedAdapter = new FeedAdapter(feed, this);
                    recyclerView.setAdapter(feedAdapter);
                    recyclerView.getViewTreeObserver().addOnScrollChangedListener(listener);

                });

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

    @Override
    public void onGetNextPage(String url) {
        try {
            recyclerView.getViewTreeObserver().removeOnScrollChangedListener(listener);
            restServiceAPI.fetchNextFeedPage(url)
            .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(next -> {
                        feedAdapter.addPage(next);
                        feedAdapter.notifyDataSetChanged();
                        loading = false;
                        recyclerView.getViewTreeObserver().addOnScrollChangedListener(listener);
                    });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notLoading() {
        loading = false;
    }
}
