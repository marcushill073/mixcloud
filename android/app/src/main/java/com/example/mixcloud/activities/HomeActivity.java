package com.example.mixcloud.activities;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.mixcloud.BR;
import com.example.mixcloud.R;
import com.example.mixcloud.adapters.DrawerAdapter;
import com.example.mixcloud.model.User;
import com.example.mixcloud.modules.DaggerDataComponent;
import com.example.mixcloud.modules.DataComponent;
import com.example.mixcloud.modules.ServiceModule;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import rx.Observable;
import rx.schedulers.Schedulers;
import rx.android.schedulers.AndroidSchedulers;

public class HomeActivity extends AppCompatActivity {

    @Inject
    public Observable<User> userSubsrciption;
    public User mUser;
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataComponent dataComponent = DaggerDataComponent.builder()
                .serviceModule(new ServiceModule(this))
                .build();

        dataComponent.inject(this);

        userSubsrciption.subscribeOn(Schedulers.newThread())
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

    }

    @BindingAdapter("android:src")
    public static void downloadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }
}
