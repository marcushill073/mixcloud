package com.example.mixcloud.modules;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.JsonReader;

import com.example.mixcloud.BuildConfig;
import com.example.mixcloud.model.Pictures;
import com.example.mixcloud.model.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.List;

import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

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

    private void setupOfflineFile(String path) throws IOException{
        is = getFileFromPath(this, path);
        Mockito.doReturn(assetManager).when(context).getAssets();
        Mockito.doReturn(is).when(assetManager).open(anyString());

        sut = new ServiceModuleImpl(context);
    }
}
