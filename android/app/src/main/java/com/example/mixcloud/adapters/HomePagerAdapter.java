package com.example.mixcloud.adapters;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Type;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private static Map<Integer, FeedFragment> map;

    public HomePagerAdapter(FragmentManager fm, Map<Integer, FeedFragment> map) {
        super(fm);
        this.map = map;
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
        FeedFragment fragment;
        if (map.get(position) == null) {
            fragment = new FeedFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(FeedFragment.FEED_TYPE, Type.values()[position]);
            fragment.setArguments(bundle);
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

    public FeedFragment getFeedFragment(int position) {
        if(map != null) {
            return map.get(position);
        } else {
            return null;
        }
    }

    public Map<Integer, FeedFragment> getCurrentFeed() {
        return map;
    }
}
