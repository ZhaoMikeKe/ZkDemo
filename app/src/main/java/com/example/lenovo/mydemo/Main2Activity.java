package com.example.lenovo.mydemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.architecture.bocom.sdk.app.BaseActivity;

import java.text.DecimalFormat;

public class Main2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    protected void initViews() {
        //dimen生成
        DecimalFormat df = new DecimalFormat("0.00");
        for (int i = 1; i < 2001; i++) {
            String aa1 = "<dimen name=" + "\"y" + i + "\"" + ">";
            String aa2 = "</dimen>";
            String aa3 = df.format(((float) 1 / 3) * i) + "dp";
            Log.d("KKK", aa1 + aa3 + aa2);
        }
    }

    @Override
    protected void initParams() {
        DisplayMetrics metric = Resources.getSystem().getDisplayMetrics();
        int DMwidth = metric.widthPixels;  // 屏幕宽度（px）
        int DMheight = metric.heightPixels;  // 屏幕高度（px）
        float DMdensity = metric.density;  // 屏幕密度（/ 1.0 / 1.5/ 2.0）
        int DMdensityDpi = metric.densityDpi;  // 屏幕密度DPI（160 / 240/ 320）
        Log.e("metric1", "屏幕宽度=" + DMwidth + " 屏幕高度=" + DMheight + " 屏幕密度=" + DMdensity + " 屏幕密度DPI" + DMdensityDpi);


    }
}
