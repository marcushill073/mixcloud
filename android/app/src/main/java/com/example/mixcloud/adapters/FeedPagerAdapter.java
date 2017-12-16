package com.example.mixcloud.adapters;


import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.example.mixcloud.fragments.FeedFragment;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Feed.Type;

import java.util.HashMap;
import java.util.Map;

import static com.example.mixcloud.model.Feed.Type.*;

public class FeedPagerAdapter extends FragmentPagerAdapter {
    private final FragmentManager fm;

    private static Map<Integer, FeedFragment> map;

    public FeedPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
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
        FeedFragment fragment = null;
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
        return map.get(position);
    }
}
