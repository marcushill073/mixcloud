package com.example.mixcloud.modules;

import android.content.Context;

import com.squareup.moshi.Moshi;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public final class ServiceGenerator {

    private ServiceGenerator(){
        //empty constructor
    }

    public static final String BASE_URL="https://api.mixcloud.com/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(createMoshiConverterFactory());

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass);
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

    public static <S> S createService(
            Class<S> serviceClass, OfflineMockInterceptor interceptor) {

        if (!httpClient.interceptors().contains(interceptor)) {
            httpClient.addNetworkInterceptor(interceptor);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }

        return retrofit.create(serviceClass);
    }
}
