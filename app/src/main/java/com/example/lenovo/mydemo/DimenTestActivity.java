package com.example.lenovo.mydemo;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DimenTestActivity extends AppCompatActivity {

    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.textview)
    TextView textview;
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 000:

                    break;
                default:

                    break;
            }
            return false;
        }
    });
    @BindView(R.id.d)
    TextView d;
    DisplayMetrics metrics;
    @BindView(R.id.d1)
    TextView d1;
    @BindView(R.id.d2)
    TextView d2;
    @BindView(R.id.d3)
    TextView d3;
    @BindView(R.id.d4)
    TextView d4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimen_test);
        ButterKnife.bind(this);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                int aa = bt.getWidth();
                textview.setText("按钮宽度100dp的basedp是"+aa + "");
                d.setText("dpi"+metrics.densityDpi + "");
                d1.setText("对160倍数"+metrics.density + "");
                d2.setText("屏幕宽度除以上面倍数"+((float) metrics.widthPixels / metrics.density) + "");
                d3.setText("屏幕尺寸"+getScreenInch(DimenTestActivity.this) + "");
                d4.setText("实际dpi"+(Math.sqrt(Math.pow(metrics.widthPixels, 2) + Math.pow(metrics.heightPixels, 2))) / getScreenInch(DimenTestActivity.this) + "");
            }
        }, 200);

    }

    private static double mInch = 0;

    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @return
     */
    public static double getScreenInch(Activity context) {
        if (mInch != 0.0d) {
            return mInch;
        }

        try {
            int realWidth = 0, realHeight = 0;
            Display display = context.getWindowManager().getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);
            if (Build.VERSION.SDK_INT >= 17) {
                Point size = new Point();
                display.getRealSize(size);
                realWidth = size.x;
                realHeight = size.y;
            } else if (Build.VERSION.SDK_INT < 17
                    && Build.VERSION.SDK_INT >= 14) {
                Method mGetRawH = Display.class.getMethod("getRawHeight");
                Method mGetRawW = Display.class.getMethod("getRawWidth");
                realWidth = (Integer) mGetRawW.invoke(display);
                realHeight = (Integer) mGetRawH.invoke(display);
            } else {
                realWidth = metrics.widthPixels;
                realHeight = metrics.heightPixels;
            }

            mInch = formatDouble(Math.sqrt((realWidth / metrics.xdpi) * (realWidth / metrics.xdpi) + (realHeight / metrics.ydpi) * (realHeight / metrics.ydpi)), 1);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return mInch;
    }

    /**
     * Double类型保留指定位数的小数，返回double类型（四舍五入）
     * newScale 为指定的位数
     */
    private static double formatDouble(double d, int newScale) {
        BigDecimal bd = new BigDecimal(d);
        return bd.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
