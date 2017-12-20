package com.example.mixcloud.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mixcloud.BR;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.adapters.FeedAdapter;
import com.example.mixcloud.adapters.UserFeedAdapter;
import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.fragments.HomeFragment;
import com.example.mixcloud.fragments.UserFeedFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Navigation;
import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.User;
import com.example.mixcloud.model.UserFeed;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.RestServiceAPI;
import com.example.mixcloud.modules.ServiceModule;
import com.example.mixcloud.modules.ServiceModuleImpl;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements FeedAdapter.OnGetNextPageListener<Navigation>, OnPlayListener, AdapterView.OnItemClickListener, View.OnClickListener {

    @Inject
    public RestServiceAPI restServiceAPI;

    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private ViewDataBinding binding;
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar(toolbar);

        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(new ServiceModuleImpl(this)))
                .build();

        dataComponent.inject(this);
        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_holder, new HomeFragment(), Navigation.HOME.getValue())
                .commit();

        fetchUserDetails();

        toolbar.setNavigationIcon(R.drawable.ic_menu_black_logo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(this);

    }

    private void fetchUserDetails() {
        restServiceAPI.fetchUser().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                    mUser = user;
                    binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_drawer_header, null, false);
                    binding.setVariable(BR.user, mUser);
                    binding.executePendingBindings();

                    ListView listView = (ListView) findViewById(R.id.left_drawer);
                    DrawerAdapter adapter = new DrawerAdapter();
                    listView.addHeaderView(binding.getRoot());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(this);
                }, error -> {
                    error.printStackTrace();
                });

    }

    @BindingAdapter("android:src")
    public static void downloadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    @Override
    public void play(Track track) {
        Gson gson = new Gson();
        Log.d(HomeActivity.class.getSimpleName(), gson.toJson(track).toString());
    }

    private void fetchFeedDetails(Navigation nav) {

        Observable<Feed> popularFeed = restServiceAPI.fetchFeed(mUser.username(), nav.getValue());
        popularFeed.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(feed -> {
                    FeedFragment<Navigation> feedFragment = (FeedFragment) getSupportFragmentManager()
                            .findFragmentByTag(nav.getValue());
                    feedFragment.setFeed(feed);
                }, error -> {
                    error.printStackTrace();
                });

    }

    @Override
    public void onGetNextPage(Navigation nav, String url) {
        FeedFragment<Navigation> feedFragment = (FeedFragment) getSupportFragmentManager()
                .findFragmentByTag(nav.getValue());
        if (feedFragment != null) {
            try {

                feedFragment.setLoading(true);
                restServiceAPI.fetchNextFeedPage(mUser.username(), nav.getValue(), url)
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Navigation nav = Navigation.values()[position - 1];

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(nav.getValue());

        if (fragment == null) {
            if (nav == Navigation.HOME) {
                fragment = new HomeFragment();
            } else {
                fragment = FeedFragment.newInstance(nav, this);
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment, nav.getValue())
                .commit();

        if (nav != Navigation.HOME) {
            fetchFeedDetails(nav);
        }
        drawerLayout.closeDrawer(Gravity.LEFT);
    }

    @Override
    public void onClick(View v) {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }
}
