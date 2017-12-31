package com.example.mixcloud.modules;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public  class OfflineMockInterceptor implements Interceptor {

    private static final MediaType MEDIA_JSON = MediaType.parse("application/json");

    private final Context context;

    public OfflineMockInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String path = request.url().encodedPathSegments().get(request.url().encodedPathSegments().size() - 1);

        Response response = null;
        InputStream is = context.getAssets().open(path + ".json");
        String json = parseStream(is);

        chain.proceed(request);

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

    public static synchronized String parseStream(InputStream is) {
        Reader reader = new InputStreamReader(is);
         BufferedReader br = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        try {
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                br.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }

}
