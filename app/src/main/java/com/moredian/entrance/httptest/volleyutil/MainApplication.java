package com.moredian.entrance.httptest.volleyutil;

import android.app.Application;

/**
 * description ：
 * author : scy
 * email : 1797484636@qq.com
 * date : 2019/10/10 13:41
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.initialize(this);
    }
}
