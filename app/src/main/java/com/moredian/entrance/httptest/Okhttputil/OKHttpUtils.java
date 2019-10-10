package com.moredian.entrance.httptest.Okhttputil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 14:51
 */
public class OKHttpUtils {

    public static String getOkHttp(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url)
                    .addHeader("AccessToken","")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "faild";
    }

    public static String postOkHttp(String url, String json) {
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        try {
            RequestBody body = RequestBody.create(JSON, json);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "faild";
    }

    public static String postkeyvalueOkHttp(String url, String json) {
        try {
            RequestBody body = new FormBody.Builder()
                    .add("a", "b")
                    .add("b", "c")
                    .build();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).post(body).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "faild";
    }

    /**
     * descirption:同步get
     */
    public static String getSyncOkHttp(String url) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Headers headers = response.headers();
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "faild";
    }

    /**
     * descirption:异步get
     */
    public static void getAsyncOkHttp(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                Headers responseHeaders = response.headers();
                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().string());
            }
        });

    }
}
