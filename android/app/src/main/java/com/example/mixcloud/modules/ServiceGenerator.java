package com.example.mixcloud.modules;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.Moshi;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ServiceGenerator {

    public static final String BASE_URL="https://api.mixcloud.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(createMoshiConverterFactory());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(
        Class<S> serviceClass, final Context context) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(context);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }

        return retrofit.create(serviceClass);
    }

    public static Converter.Factory createMoshiConverterFactory() {
        Moshi moshi = new Moshi.Builder()
                .add(MyAdapterFactory.create())
                .build();
        return MoshiConverterFactory.create(moshi);
    }
}
