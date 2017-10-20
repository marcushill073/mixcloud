package com.example.mixcloud.utils;

import android.content.Context;

import com.example.mixcloud.R;
import com.example.mixcloud.model.User;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpUtils {

    private static final String BASE_URL = "www.mixcloud.com";
    private static final String OAUTH = "oauth";
    private static final String TOKEN = "access_token";
    private static final String MEDIA_TYPE_TEXT = "text/plain; charset=utf-8";

    private static String getJSON(String userUrl) throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(userUrl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static User authenticate(Context context, String code) throws IOException {
        OkHttpClient client = new OkHttpClient();

        HttpUrl url = new HttpUrl.Builder()
                .host(BASE_URL)
                .addPathSegment(OAUTH)
                .addPathSegment(TOKEN)
                .addEncodedQueryParameter("client_id", context.getString(R.string.client_id))
                .addEncodedQueryParameter("client_secret", context.getString(R.string.client_secret))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        ResponseBody body = response.body();

        return null;
    }


}
