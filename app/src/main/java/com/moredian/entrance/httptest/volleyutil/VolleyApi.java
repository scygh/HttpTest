package com.moredian.entrance.httptest.volleyutil;

import com.android.volley.Request;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 14:38
 */
public class VolleyApi {

    public static void getObjectMiNongApi(String value,ResponseListener listener){
        String url ="http://dev.open.api.dt128.com/Api/Token/GetToken?account=1001&password=123456";
        Request request = new GetObjectRequest(url,new TypeToken<GetToken>(){}.getType(),listener) ;
        VolleyUtil.getRequestQueue().add(request) ;
    }


}
