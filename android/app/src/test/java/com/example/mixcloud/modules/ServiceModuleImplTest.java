package com.example.mixcloud.modules;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.mixcloud.BuildConfig;
import com.example.mixcloud.model.Feed;
import com.example.mixcloud.model.Paging;
import com.example.mixcloud.model.Pictures;
import com.example.mixcloud.model.Track;
import com.example.mixcloud.model.Home;
import com.example.mixcloud.model.User;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@PrepareForTest({BuildConfig.class})
@RunWith(MockitoJUnitRunner.class)
public class ServiceModuleImplTest {

    private ServiceModuleImpl sut;

    @Spy
    private Context context;
    @Spy
    private AssetManager assetManager;

    private String USER = "{\"username\":\"marcushill073\",\"favorite_count\":0,\"name\":\"marcushill073\",\"following_count\":0,\"url\":\"https://www.mixcloud.com/marcushill073/\",\"pictures\":{\"medium\":\"https://thumbnailer.mixcloud.com/unsafe/100x100/defaults/users/6.png\",\"320wx320h\":\"https://thumbnailer.mixcloud.com/unsafe/320x320/defaults/users/6.png\",\"extra_large\":\"https://thumbnailer.mixcloud.com/unsafe/600x600/defaults/users/6.png\",\"large\":\"https://thumbnailer.mixcloud.com/unsafe/300x300/defaults/users/6.png\",\"640wx640h\":\"https://thumbnailer.mixcloud.com/unsafe/640x640/defaults/users/6.png\",\"medium_mobile\":\"https://thumbnailer.mixcloud.com/unsafe/80x80/defaults/users/6.png\",\"small\":\"https://thumbnailer.mixcloud.com/unsafe/25x25/defaults/users/6.png\",\"thumbnail\":\"https://thumbnailer.mixcloud.com/unsafe/50x50/defaults/users/6.png\"},\"is_current_user\":true,\"listen_count\":2,\"updated_time\":\"2017-07-28T18:34:52Z\",\"is_premium\":false,\"follower_count\":0,\"follower\":false,\"biog\":\"\",\"key\":\"/marcushill073/\",\"following\":false,\"created_time\":\"2017-07-28T18:34:52Z\",\"is_pro\":false,\"cloudcast_count\":0}";
    private JSONObject jsonObject;
    private InputStream is;

    @Before
    public void setUp() throws Exception {
        Field field = BuildConfig.class.getDeclaredField("BUILD_TYPE");
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(String.class, "mock");
    }

    @Test
    public void testFetchUserFeed() throws IOException {
        setupOfflineFile("me.json");
        TestSubscriber<User> testSubscriber = new TestSubscriber<>();
        sut.fetchUser().subscribe(testSubscriber);


        List<User> users = testSubscriber.getOnNextEvents();
        assertEquals(1, users.size());
        User user = users.get(0);

        User mock = getMockUser();
        assertEquals(mock.username(), user.username());
        assertEquals(mock.favoriteCount(), user.favoriteCount());
        assertEquals(mock.name(), user.name());
        assertEquals(mock.followingCount(), user.followingCount());
        assertEquals(mock.followerCount(), user.followingCount());
        assertEquals(mock.url(), user.url());

        assertEquals(mock.pictures().medium(), user.pictures().medium());
        assertEquals(mock.pictures().extraLarge(), user.pictures().extraLarge());
        assertEquals(mock.pictures().large(), user.pictures().large());
        assertEquals(mock.pictures().mediumMobile(), user.pictures().mediumMobile());
        assertEquals(mock.pictures().small(), user.pictures().small());
        assertEquals(mock.pictures().thumbnail(), user.pictures().thumbnail());
        assertEquals(mock.isPremium(), user.isPremium());

    }

    @Test
    public void testFetchPopularFeed() throws IOException {
        setupOfflineFile("popular.json");
        TestSubscriber<Feed> testSubscriber = new TestSubscriber<>();
        sut.fetchHomeFeed(Home.POPULAR.getValue())
                .subscribe(testSubscriber);


        List<Feed> feedList = testSubscriber.getOnNextEvents();
        assertEquals(1, feedList.size());

        Feed feed = feedList.get(0);
        Feed mock = getMockPopularFeed();

        assertEquals(feed.paging(), mock.paging());
        assertEquals(feed.data().get(0).audioLength(), mock.data().get(0).audioLength());
        assertEquals(feed.data().get(0).commentCount(), mock.data().get(0).commentCount());
        assertEquals(feed.data().get(0).createdTime(), mock.data().get(0).createdTime());
        assertEquals(feed.data().get(0).audioLength(), mock.data().get(0).audioLength());


    }

    private static InputStream getFileFromPath(Object obj, String fileName) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        try {
            return new FileInputStream(new File(resource.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User getMockUser() {
        Pictures pictures = Pictures.builder()
                .medium("https://thumbnailer.mixcloud.com/unsafe/100x100/defaults/users/6.png")
                .extraLarge("https://thumbnailer.mixcloud.com/unsafe/600x600/defaults/users/6.png")
                .large("https://thumbnailer.mixcloud.com/unsafe/300x300/defaults/users/6.png")
                .mediumMobile("https://thumbnailer.mixcloud.com/unsafe/80x80/defaults/users/6.png")
                .small("https://thumbnailer.mixcloud.com/unsafe/25x25/defaults/users/6.png")
                .thumbnail("https://thumbnailer.mixcloud.com/unsafe/50x50/defaults/users/6.png")
                .build();
        return User.builder()
                .username("marcushill073")
                .favoriteCount(0)
                .name("marcushill073")
                .followingCount(0)
                .url("https://www.mixcloud.com/marcushill073/")
                .pictures(pictures)
                .followerCount(0)
                .key("/marcushill073/")
                .isPremium(false)
                .cloudcastCount(0)
                .build();
    }

    private Feed getMockPopularFeed() {
        List<Track> tracks = new ArrayList<>();

        Pictures userPictures = Pictures.builder()
                .medium("https://thumbnailer.mixcloud.com/unsafe/100x100/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .extraLarge("https://thumbnailer.mixcloud.com/unsafe/600x600/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .large("https://thumbnailer.mixcloud.com/unsafe/300x300/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .mediumMobile("https://thumbnailer.mixcloud.com/unsafe/80x80/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .small("https://thumbnailer.mixcloud.com/unsafe/25x25/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .thumbnail("https://thumbnailer.mixcloud.com/unsafe/50x50/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .build();

        User user = User.builder()
                .username("NovaSPORFM946")
                .name("\\u03a3\\u03a0\\u039f\\u03a1 FM 94.6")
                .url("https://www.mixcloud.com/NovaSPORFM946/")
                .pictures(userPictures)
                .key("/NovaSPORFM946/")
                .build();

        Pictures trackPicture = Pictures.builder()
                .medium("https://thumbnailer.mixcloud.com/unsafe/100x100/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .extraLarge("https://thumbnailer.mixcloud.com/unsafe/600x600/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .large("https://thumbnailer.mixcloud.com/unsafe/300x300/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .mediumMobile("https://thumbnailer.mixcloud.com/unsafe/80x80/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .small("https://thumbnailer.mixcloud.com/unsafe/25x25/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .thumbnail("https://thumbnailer.mixcloud.com/unsafe/50x50/profile/3/3/c/9/077d-6a7c-47c4-b176-57a4710165fe")
                .build();

        Track track = Track.builder()
                .tags(null)
                .user(user)
                .key("/NovaSPORFM946/%CE%BA%CE%B5%CF%84%CF%83%CE%B5%CF%84%CE%B6%CF%8C%CE%B3%CE%BB%CE%BF%CF%85-%CF%83%CF%84%CE%BF%CE%BD-%CF%83%CF%80%CE%BF%CF%81-fm-%CE%AD%CE%BB%CE%B5%CE%BF%CF%82-%CF%84%CE%BF-%CE%AD%CF%87%CE%BF%CF%85%CE%BC%CE%B5-%CF%84%CF%81%CE%B1%CE%B2%CE%AE%CE%BE%CE%B5%CE%B9-%CE%BC%CE%B5-%CF%84%CE%BF%CE%BD-%CE%B1%CF%81%CE%B1%CE%BF%CF%8D%CF%87%CE%BF/")
                .audioLength(458)
                .slug("\\u03ba\\u03b5\\u03c4\\u03c3\\u03b5\\u03c4\\u03b6\\u03cc\\u03b3\\u03bb\\u03bf\\u03c5-\\u03c3\\u03c4\\u03bf\\u03bd-\\u03c3\\u03c0\\u03bf\\u03c1-fm-\\u03ad\\u03bb\\u03b5\\u03bf\\u03c2-\\u03c4\\u03bf-\\u03ad\\u03c7\\u03bf\\u03c5\\u03bc\\u03b5-\\u03c4\\u03c1\\u03b1\\u03b2\\u03ae\\u03be\\u03b5\\u03b9-\\u03bc\\u03b5-\\u03c4\\u03bf\\u03bd-\\u03b1\\u03c1\\u03b1\\u03bf\\u03cd\\u03c7\\u03bf")
                .favoriteCount(0)
                .listenerCount(33)
                .name("\\u039a\\u03b5\\u03c4\\u03c3\\u03b5\\u03c4\\u03b6\\u03cc\\u03b3\\u03bb\\u03bf\\u03c5 \\u03c3\\u03c4\\u03bf\\u03bd \\u03a3\\u03a0\\u039f\\u03a1 FM: \\u00ab\\u0388\\u03bb\\u03b5\\u03bf\\u03c2. \\u03a4\\u03bf \\u03ad\\u03c7\\u03bf\\u03c5\\u03bc\\u03b5 \\u03c4\\u03c1\\u03b1\\u03b2\\u03ae\\u03be\\u03b5\\u03b9 \\u03bc\\u03b5 \\u03c4\\u03bf\\u03bd \\u0391\\u03c1\\u03b1\\u03bf\\u03cd\\u03c7\\u03bf\\u00bb")
                .url("https://www.mixcloud.com/NovaSPORFM946/%CE%BA%CE%B5%CF%84%CF%83%CE%B5%CF%84%CE%B6%CF%8C%CE%B3%CE%BB%CE%BF%CF%85-%CF%83%CF%84%CE%BF%CE%BD-%CF%83%CF%80%CE%BF%CF%81-fm-%CE%AD%CE%BB%CE%B5%CE%BF%CF%82-%CF%84%CE%BF-%CE%AD%CF%87%CE%BF%CF%85%CE%BC%CE%B5-%CF%84%CF%81%CE%B1%CE%B2%CE%AE%CE%BE%CE%B5%CE%B9-%CE%BC%CE%B5-%CF%84%CE%BF%CE%BD-%CE%B1%CF%81%CE%B1%CE%BF%CF%8D%CF%87%CE%BF/")
                .pictures(trackPicture)
                .repostCount(0)
                .commentCount(0)
                .build();

        tracks.add(track);

        return Feed.builder()
                .paging(Paging.builder().build())
                .data(tracks)
                .build();
    }

    private void setupOfflineFile(String path) throws IOException{
        is = getFileFromPath(this, path);
        Mockito.doReturn(assetManager).when(context).getAssets();
        Mockito.doReturn(is).when(assetManager).open(anyString());

        sut = new ServiceModuleImpl(context);
    }
}
