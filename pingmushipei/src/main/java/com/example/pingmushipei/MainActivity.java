package com.example.pingmushipei;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.architecture.bocom.sdk.Utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final float BASE_SCREEN_WIDTH_FLOAT = 1080;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int widthPixels = outMetrics.widthPixels;
        float scale = (float) widthPixels / BASE_SCREEN_WIDTH_FLOAT;
        View rootView = findViewById(android.R.id.content);
        Utils.scaleViewSize(this, rootView, scale);
//对于TextView，不但要缩放其尺寸，还需要对其字体进行缩放：
        /**
         * 原创作者:
         * 谷哥的小弟
         *
         * 博客地址:
         * http://blog.csdn.net/lfdfhl
         */
       /* Object isScale = textView.getTag(R.id.is_scale_font_tag);
        if (!(isScale instanceof Boolean) || !((Boolean) isScale).booleanValue()) {
            float size = textView.getTextSize();
            size *= scale;
            textView.setTextSize(0, size);
        }*/
//除此以外，还要考虑到对TextView的CompoundDrawable进行缩放
        /**
         * 原创作者:
         * 谷哥的小弟
         *
         * 博客地址:
         * http://blog.csdn.net/lfdfhl
         */
       /* public static Drawable scaleDrawableBounds(Drawable drawable) {
            int right=getScaleValue(drawable.getIntrinsicWidth());
            int bottom=getScaleValue(drawable.getIntrinsicHeight());
            drawable.setBounds(0, 0, right, bottom);
            return drawable;
        }*/

    }


}
