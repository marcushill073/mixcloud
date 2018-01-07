package com.example.mixcloud.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.fragments.HomeFragment;
import com.example.mixcloud.fragments.WebViewFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Navigation;
import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.User;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.rest.RestService;
import com.example.mixcloud.rest.RestServiceAPI;
import com.example.mixcloud.modules.ServiceGenerator;
import com.example.mixcloud.modules.ServiceModule;
import com.example.mixcloud.modules.ServiceModuleImpl;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.mixcloud.model.Navigation.HOME;

public class HomeActivity extends AppCompatActivity implements FeedAdapter.OnGetNextPageListener<Navigation>, OnPlayListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private static final String PLAYER = ".player";
    @Inject
    public RestServiceAPI restServiceAPI;

    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

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
                    ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.item_drawer_header, null, false);
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

        if (fragment == null || nav == HOME) {
            if (nav == HOME) {
                fragment = new HomeFragment();
            } else {
                fragment = FeedFragment.newInstance(nav);
            }
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_holder, fragment, nav.getValue())
                .commit();

        if (nav != HOME) {
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

    @Override
    public void play(String url) {
        RestService metaService = ServiceGenerator.createService(RestService.class, ServiceGenerator.META_URL);
        metaService.fetchMetaData(url)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(metaData -> {

                    WebViewFragment fragment = (WebViewFragment) getSupportFragmentManager().findFragmentByTag(PLAYER);
                    if (fragment == null) {

                        fragment = WebViewFragment.newInstance(metaData);
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.player, fragment, PLAYER)
                                .commit();
                    } else {
                        fragment.setTrackUrl(metaData);
                    }
                });
    }

}
