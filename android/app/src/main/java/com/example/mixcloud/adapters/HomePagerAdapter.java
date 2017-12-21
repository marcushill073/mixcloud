package com.example.mixcloud.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Type;

import java.util.HashMap;
import java.util.Map;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private static Map<Integer, FeedFragment<Type>> map;
    private final FeedAdapter.OnGetNextPageListener<Type> listener;

    public HomePagerAdapter(FragmentManager fm, FeedAdapter.OnGetNextPageListener<Type> listener) {
        super(fm);
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return Type.values().length;
    }

    @Override
    public Fragment getItem(int position) {
        if (map == null) {
            map = new HashMap<>();
        }
        FeedFragment<Type> fragment;
        if (map.get(position) == null) {
            Type type = Type.values()[position];
            fragment = FeedFragment.newInstance(type, listener);
            map.put(position, fragment);
        } else {
            fragment = map.get(position);
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return Type.values()[position].getValue();
    }

    public FeedFragment<Type> getFeedFragment(int position) {
        if(map != null) {
            return map.get(position);
        } else {
            return null;
        }
    }

}
