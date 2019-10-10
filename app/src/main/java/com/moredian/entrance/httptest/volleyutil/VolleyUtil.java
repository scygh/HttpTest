package com.moredian.entrance.httptest.volleyutil;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 13:41
 */
public class VolleyUtil {
    private static RequestQueue mRequestQueue ;

    public static void initialize(Context context){
        if (mRequestQueue == null){
            synchronized (VolleyUtil.class){
                if (mRequestQueue == null){
                    mRequestQueue = Volley.newRequestQueue(context) ;
                }
            }
        }
        mRequestQueue.start();
    }

    public static RequestQueue getRequestQueue(){
        if (mRequestQueue == null)
            throw new RuntimeException("请先初始化mRequestQueue") ;
        return mRequestQueue ;
    }

}
