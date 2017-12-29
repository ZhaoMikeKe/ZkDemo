package com.example.lenovo.mydemo;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by lenovo on 2017/9/5.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
