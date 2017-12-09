package com.example.mixcloud.modules;


import android.content.Context;

import com.example.mixcloud.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthenticationInterceptor implements Interceptor {


    private final Context context;

    public AuthenticationInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        if(!original.url().toString().contains("access_token")) {

            String token = context.getSharedPreferences(context.getResources().getString(R.string.app_name), Context.MODE_PRIVATE)
                    .getString(context.getResources().getString(R.string.token), null);

            Request.Builder builder;
            if(original.url().toString().contains("?")) {
                builder= original.newBuilder().url(original.url().toString().replace("?", "?access_token=" + token + "&"));
            } else {
                builder = original.newBuilder().url(original.url() + "?access_token=" + token);
            }
            original = builder.build();
        }
        return chain.proceed(original);
    }
}
