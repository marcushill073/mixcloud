package com.example.mixcloud.modules;

import android.content.Context;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

@RunWith(MockitoJUnitRunner.class)
public class ServiceModuleImplTest {

    private static RestService mRestService;
    private static Context context;

    @Before
    public void setUp() throws Exception {

    }

    public static RestService getClient() {
        if(mRestService == null) {
            final OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new OfflineMockInterceptor(context));

            final Retrofit retrofit = new Retrofit.Builder()
                    // Using custom Jackson Converter to parse JSON
                    // Add dependencies:
                    // com.squareup.retrofit:converter-jackson:2.0.0-beta2
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(ServiceGenerator.createMoshiConverterFactory())
                        .client(client)
                    .build();

            mRestService = retrofit.create(RestService.class);
        }
        return mRestService;
    }
}
