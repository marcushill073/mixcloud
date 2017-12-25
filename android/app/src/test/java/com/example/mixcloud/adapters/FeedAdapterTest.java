package com.example.mixcloud.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.OnPlayListener;
import com.example.mixcloud.model.Paging;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.Type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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

    @Mock
    private Feed feed;

    private List<Track> tracks;
    @Mock
    private Paging paging;
    @Mock
    private Track track;
    private FeedAdapter sut;
    private DataBinderHolder dataBinderHolder;

    @Before
    public void setUp() throws Exception {
        sut = new FeedAdapter<>(Type.POPULAR, onGetNextPageListener, onPlayListener);

        tracks = new ArrayList<>();
        tracks.add(track);

        PowerMockito.mockStatic(LayoutInflater.class);
        PowerMockito.mockStatic(DataBindingUtil.class);

//        PowerMockito.doNothing().when(onGetNextPageListener, "onGetNextPage", any(Serializable.class), anyString());
//        PowerMockito.doNothing().when(onPlayListener, "play", any(Track.class));

        PowerMockito.when(LayoutInflater.from(any(Context.class))).thenReturn(inflater);
        PowerMockito.when(DataBindingUtil.inflate(any(LayoutInflater.class), anyInt(), any(ViewGroup.class), anyBoolean())).thenReturn(viewDataBindng);
        Mockito.when(parent.getContext()).thenReturn(context);
        Mockito.when(viewDataBindng.getRoot()).thenReturn(itemView);
        Mockito.when(feed.data()).thenReturn(tracks);
        Mockito.when(feed.paging()).thenReturn(paging);
        Mockito.when(paging.next()).thenReturn("url");
        PowerMockito.when(viewDataBindng.setVariable(anyInt(), any(Feed.class))).thenReturn(true);
    }

    @Test
    public void testFeedAdapterTypeOnCreateView() {

        dataBinderHolder = sut.onCreateViewHolder(parent, 0);

        PowerMockito.verifyStatic(DataBindingUtil.class);
        DataBindingUtil.inflate(any(LayoutInflater.class), anyInt(), any(ViewGroup.class), anyBoolean());
        PowerMockito.verifyStatic(LayoutInflater.class);
        LayoutInflater.from(any(Context.class));
        assertNotNull(dataBinderHolder);
    }

    @Test
    public void testFeedAdapterTypeOnBindViewHolder() {
        dataBinderHolder = sut.onCreateViewHolder(parent, 0);

        sut.setFeed(feed);

        sut.onBindViewHolder(dataBinderHolder, 0);
        verify(dataBinderHolder.getViewDataBinding(), times(2));

        int size = sut.getItemCount();
        assertEquals(1, size);

    }

    @Test
    public void testFeedAdapterTypeAddPage() {
        sut.addPage(feed);

        assertEquals(1, sut.getFeed().data().size());
    }

    @Test
    public void testFeedAdapterTypenotifyLastVisiblePosition() {
        sut.setFeed(feed);

        sut.notifyLastVisiblePosition(0);
        Mockito.verify(onGetNextPageListener).onGetNextPage(any(Type.class), anyString());
    }
}
