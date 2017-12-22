package com.example.mixcloud.modules;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.example.mixcloud.model.Feed;
import com.squareup.moshi.Moshi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Method;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;

import static com.example.mixcloud.modules.ServiceGenerator.createMoshiConverterFactory;

public class OfflineMockInterceptor implements Interceptor {
    private static final MediaType MEDIA_JSON = MediaType.parse("application/json");
    private static final String MOCK = "/mocks/";

    private final Context context;

    public OfflineMockInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        String path = request.url().encodedPath();


        Response response = null;
        Log.d("Asset", path);
        InputStream is = context.getAssets().open(path);
        String json = parseStream(is);

        response = new Response.Builder()
                .code(200)
                .message(json)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MEDIA_JSON, json.getBytes()))
                .addHeader("content-type", "application/json")
                .build();


        return response;
    }

    private String parseStream(InputStream stream) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(stream);
        StringBuilder builder = new StringBuilder();
        byte[] buffer = new byte[024];
        while (bis.available() > 0) {
            bis.read(buffer);
            builder.append(buffer);
        }
        return builder.toString();
    }
}
