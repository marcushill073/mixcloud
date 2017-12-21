package com.example.mixcloud.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.Serializable;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;


@PrepareForTest({LayoutInflater.class, DataBindingUtil.class})
@RunWith(PowerMockRunner.class)
public class FeedAdapterTest {

    @Mock
    private FeedAdapter.OnGetNextPageListener<Type> onGetNextPageListener;

    @Mock
    private OnPlayListener onPlayListener;

    @Mock
    private ViewGroup parent;

    @Mock
    private LayoutInflater inflater;

    @Mock
    private Context context;

    @Mock
    private ViewDataBinding viewDataBindng;

    @Mock
    private View itemView;

    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(LayoutInflater.class);
        PowerMockito.mockStatic(DataBindingUtil.class);

//        PowerMockito.doNothing().when(onGetNextPageListener, "onGetNextPage", any(Serializable.class), anyString());
//        PowerMockito.doNothing().when(onPlayListener, "play", any(Track.class));

        PowerMockito.when(LayoutInflater.from(any(Context.class))).thenReturn(inflater);
        PowerMockito.when(DataBindingUtil.inflate(any(LayoutInflater.class), anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(viewDataBindng);
        PowerMockito.when(parent.getContext()).thenReturn(context);
        PowerMockito.when(viewDataBindng.getRoot()).thenReturn(itemView);

    }

    @Test
    public void testFeedAdapterTypePopular() {
        FeedAdapter<Type> sut = new FeedAdapter<>(Type.POPULAR, onGetNextPageListener, onPlayListener);

        sut.onCreateViewHolder(parent, 0);
        PowerMockito.verifyStatic(times(2), DataBindingUtil.inflate());
        Utils.randomDistance(1);
        PowerMockito.verifyStatic(DataBindingUtil.inflate(any(LayoutInflater.class), anyInt(), any(ViewGroup.class), anyBoolean()));
        PowerMockito.verifyStatic(LayoutInflater.class);

    }


}
