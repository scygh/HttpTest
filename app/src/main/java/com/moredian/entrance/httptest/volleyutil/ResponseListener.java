package com.moredian.entrance.httptest.volleyutil;

import com.android.volley.Response;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 14:33
 */
public interface ResponseListener<T> extends Response.ErrorListener,Response.Listener<T> {
}
