package com.moredian.entrance.httptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.moredian.entrance.httptest.Okhttputil.OKHttpUtils;
import com.moredian.entrance.httptest.volleyutil.GetToken;
import com.moredian.entrance.httptest.volleyutil.ResponseListener;
import com.moredian.entrance.httptest.volleyutil.VolleyApi;
import com.moredian.entrance.httptest.volleyutil.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private final String url = "http://dev.open.api.dt128.com/Api/Token/GetToken?account=1001&password=123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = OKHttpUtils.getOkHttp(url);
                Log.d("main", "run: "+ result);
            }
        }).start();
        //简单实现
        /*JsonObjectRequest req = new JsonObjectRequest(url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(MainActivity.this, ""+ response.getString("StatusCode"), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        VolleyUtil.getRequestQueue().add(req);*/
        /*VolleyApi.getObjectMiNongApi("scy", new ResponseListener<GetToken>() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(GetToken response) {
                Toast.makeText(MainActivity.this, ""+ response.getContent().getAccessToken(), Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
