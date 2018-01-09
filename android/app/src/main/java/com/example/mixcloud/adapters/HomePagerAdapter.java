package com.example.mixcloud.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Type;

import java.util.HashMap;
import java.util.Map;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private static Map<Integer, FeedFragment> map;
    private final FeedAdapter.OnGetNextPageListener listener;
    private final Type type;

    public HomePagerAdapter(FragmentManager fm, Type type,  FeedAdapter.OnGetNextPageListener listener) {
        super(fm);
        this.listener = listener;
        this.type = type;
    }

    @Override
    public int getCount() {
        return type.getValues().length;
    }

    @Override
    public Fragment getItem(int position) {
        if (map == null) {
            map = new HashMap<>();
        }
        FeedFragment fragment;
        if (map.get(position) == null) {
            Type typeOf = this.type.getValues()[position];
            fragment = FeedFragment.newInstance(typeOf);
            map.put(position, fragment);
        } else {
            fragment = map.get(position);
        }
        return fragment;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return type.getValues()[position].getValue();
    }

    public FeedFragment getFeedFragment(int position) {
        if(map != null) {
            return map.get(position);
        } else {
            return null;
        }
    }

}
