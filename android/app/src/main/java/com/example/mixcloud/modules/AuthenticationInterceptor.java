package com.example.mixcloud.modules;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthenticationInterceptor implements Interceptor {


    private final String authToken;

    public AuthenticationInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder().url(original.url() + "?access_token=" + authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
