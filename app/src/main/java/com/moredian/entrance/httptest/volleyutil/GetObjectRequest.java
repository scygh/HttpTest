package com.moredian.entrance.httptest.volleyutil;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 14:31
 */
public class GetObjectRequest<T> extends Request<T> {

    private ResponseListener mListener;
    private Gson mGson;
    private Type mClazz;

    public GetObjectRequest(String url,Type type, ResponseListener listener) {
        super(Method.GET, url, listener);
        this.mListener = listener ;
        mGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create() ;
        mClazz = type ;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            T result ;
            String jsonString =
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            result = mGson.fromJson(jsonString,mClazz) ;
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 回调正确的数据
     * @param response The parsed response returned by
     */
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
