package com.example.skintest;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.constraint.app.SkinConstraintViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by zhaoke on 2017/9/29.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
                //.addStrategy(new CustomSDCardLoader())                  // 自定义加载策略，指定SDCard路径[可选]
                //.addHookInflater(new SkinHookAutoLayoutViewInflater())  // hongyangAndroid/AndroidAutoLayout[可选]
                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
                //.addInflater(new SkinCircleImageViewInflater())         // hdodenhof/CircleImageView[可选]
                //.addInflater(new SkinFlycoTabLayoutInflater())          // H07000223/FlycoTabLayout[可选]
                .setSkinStatusBarColorEnable(false)                     // 关闭状态栏换肤，默认打开[可选]
                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
                .loadSkin();
    }
}
