package com.example.mixcloud.async;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AsyncFetchToken extends AsyncTaskLoader<String> {

    private static final String APP_ID = "yZ9nmaQrwR8vaYNN8V";
    private static final String APP_SECRET = "upPbSmSfmWkm8Z7bUA28pxG3FBydvTcx";
    private static final String AUTH_URL = "https://www.mixcloud.com/oauth/access_token?client_id=" + Base64.encodeToString(APP_ID.getBytes(),Base64.DEFAULT)
            + "&redirect_uri=http://www.example.com&client_secret=" + Base64.encode(APP_SECRET.getBytes(),Base64.DEFAULT) + "&code=";


    private static final String GRANT_TYPE = "grant_type";
    private static final String CREDENTIAL = "client_credentials";

    private static final String BASIC = "Authorization";
    private final String code;

    public AsyncFetchToken(Context context, String code) {
        super(context);
        this.code = code;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        RequestBody requestBody = new FormBody.Builder()
                .add(GRANT_TYPE, CREDENTIAL)
                .build();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .post(requestBody)
                .url(AUTH_URL + Base64.encode(code.getBytes(), Base64.DEFAULT))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String body = response.body().string();
            Log.d(AsyncFetchToken.class.getName(), "response: " + body);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
        Log.d("result", data);
    }
}

