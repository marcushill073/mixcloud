package com.example.mixcloud.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Home;

import java.util.HashMap;
import java.util.Map;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private static Map<Integer, FeedFragment<Home>> map;
    private final FeedAdapter.OnGetNextPageListener<Home> listener;

    public HomePagerAdapter(FragmentManager fm, FeedAdapter.OnGetNextPageListener<Home> listener) {
        super(fm);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return Home.values().length;
    }

    @Override
    public Fragment getItem(int position) {
        if (map == null) {
            map = new HashMap<>();
        }
        FeedFragment<Home> fragment;
        if (map.get(position) == null) {
            Home type = Home.values()[position];
            fragment = FeedFragment.newInstance(type);
            map.put(position, fragment);
        } else {
            fragment = map.get(position);
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Home.values()[position].getValue();
    }

    public FeedFragment<Home> getFeedFragment(int position) {
        if(map != null) {
            return map.get(position);
        } else {
            return null;
        }
    }

}
