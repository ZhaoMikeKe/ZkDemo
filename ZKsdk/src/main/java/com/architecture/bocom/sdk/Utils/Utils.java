package com.architecture.bocom.sdk.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author Ke
 * @version [版本号，2017/7/11 16:04]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class Utils {
    private static Toast mToast;
    private static float scale1;

    /**
     * 弹吐司
     */
    public static void showToast(Context context, String ss) {
        if (mToast == null) {
            mToast = Toast.makeText(context, ss, Toast.LENGTH_SHORT);
        } else mToast.setText(ss);
        mToast.show();
    }

    /**
     * 获取Mac地址
     */
    public static String getMacAddr(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        String mac = wm.getConnectionInfo().getMacAddress();
        return mac == null ? "" : mac;
    }

    //跳转activity
    public static void jumpActivity(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    //dimen生成
    public static void dimenS() {

        DecimalFormat df = new DecimalFormat("0.00");
        for (float i = 1; i < 2001; i++) {
            String aa1 = "<dimen name=" + "\"base" + (int) i + "" + "dp\"" + ">";
            String aa2 = "</dimen>";
            String aa3 = df.format(((float) 640 / 360) * i) + "dp";
            Log.d("KKK", aa1 + aa3 + aa2);
        }
    }

    public static void scaleViewSize(Context context, View view, float scale) {
        scale1 = scale;
        if (null != view) {
            int paddingLeft = getScaleValue(view.getPaddingLeft());
            int paddingTop = getScaleValue(view.getPaddingTop());
            int paddingRight = getScaleValue(view.getPaddingRight());
            int paddingBottom = getScaleValue(view.getPaddingBottom());
            view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);

            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

            if (null != layoutParams) {

                if (layoutParams.width > 0) {
                    layoutParams.width = getScaleValue(layoutParams.width);
                }

                if (layoutParams.height > 0) {
                    layoutParams.height = getScaleValue(layoutParams.height);
                }

                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    int topMargin = getScaleValue(marginLayoutParams.topMargin);
                    int leftMargin = getScaleValue(marginLayoutParams.leftMargin);
                    int bottomMargin = getScaleValue(marginLayoutParams.bottomMargin);
                    int rightMargin = getScaleValue(marginLayoutParams.rightMargin);
                    marginLayoutParams.topMargin = topMargin;
                    marginLayoutParams.leftMargin = leftMargin;
                    marginLayoutParams.bottomMargin = bottomMargin;
                    marginLayoutParams.rightMargin = rightMargin;
                }
            }
            view.setLayoutParams(layoutParams);
        }
    }


    private static int getScaleValue(int paddingLeft) {

        return (int) (paddingLeft / scale1);
    }
}
