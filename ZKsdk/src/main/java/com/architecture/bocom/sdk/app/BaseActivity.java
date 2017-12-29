package com.architecture.bocom.sdk.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;

/**
 * Created by Su on 2016/11/7.
 * 所有activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        initViews();
        initParams();

    }

    public static int i = 0;


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                i = 0;
                //ToastUtils.showLongToast(this, "触摸了。");
                break;
            case MotionEvent.ACTION_UP:

//                OpenTest();
                //ToastUtils.showLongToast(this, "起来了。");
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }




    /**
     * 初始化界面控件
     */
    protected abstract void initViews();

    /**
     * 初始化一些常用参数
     */
    protected abstract void initParams();


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
