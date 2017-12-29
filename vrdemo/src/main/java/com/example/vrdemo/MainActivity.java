package com.example.vrdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private VrPanoramaView vrPanoramaView;
    private ImageTask imageTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //全景图片的浏览功能
        //步骤一。下载github上google开源 vr-sdk
        //1.1.导入到我们的工作空间 common,commonwidget  panowidget
        //1.2.依赖到我们的项目中
        //1.3.依赖sdk中找不到的api
        //1.4.准备一些测试素材 放置在assets目录下面 例:assets/a.jpg
        //1.5.开启内存设置  android:largeHeap="true"尽可能使应用使用最大内存
        //步骤二。将全景图片加载到内存中，再显示在控件
        //2.1.布局全景图片显示控件
        vrPanoramaView = (VrPanoramaView) findViewById(R.id.vr_pv);
        //删除不需要连接
        vrPanoramaView.setInfoButtonEnabled(false);
        //隐藏全屏按钮
        vrPanoramaView.setFullscreenButtonEnabled(false);
        //2.2.所有的图片在内存表示成Bitmap
        imageTask = new ImageTask();
        imageTask.execute();
        //vrPanoramaView.loadImageFromBitmap(bitmap);
    }
    //2.3.AsyncTask异步加载
    private class ImageTask extends AsyncTask<Void, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                InputStream inputStream = getAssets().open("sky2.jpg");
                //2.4.使用BitmapFactory 可以sd ,byte[] inputstream-->Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                //loadImageFromBitmap加载bitmap到显示控件 参1.bitmap 参2 显示参数的封装
                VrPanoramaView.Options option = new VrPanoramaView.Options();
                //立体图片:上半张显示在左眼，下半张显示在右眼
                option.inputType = VrPanoramaView.Options.TYPE_MONO;
                VrPanoramaEventListener listener=new VrPanoramaEventListener(){
                    @Override
                    public void onLoadSuccess() {
                        super.onLoadSuccess();
                        //成功的情况提示下现在要进行全景图片的展示
                        Toast.makeText(MainActivity.this, "进入vr图片", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onLoadError(String errorMessage) {
                        super.onLoadError(errorMessage);
                        //处理加载失败的情况
                        Toast.makeText(MainActivity.this, "E:"+errorMessage, Toast.LENGTH_SHORT).show();
                    }
                };
                //2.5.增加加载出错的业务逻辑处理
                vrPanoramaView.setEventListener(listener);
                //2.6.全屏展示
                vrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_MONO);
                //2.4.加载bitmap到控件上显示
                vrPanoramaView.loadImageFromBitmap(bitmap, option);
            }
        }
    }
    //步骤三。优化程序细节 ，页面退到后台,暂停显示 ，页面显示在屏幕 恢复显示。销毁页面,释放全景图片
    //3.1 页面退到后台,暂停显示
    @Override
    protected void onPause() {
        super.onPause();
        if(vrPanoramaView!=null)
        {
            vrPanoramaView.pauseRendering();
        }
    }
    //3.2 页面显示在屏幕 恢复显示
    @Override
    protected void onResume() {
        super.onResume();
        if(vrPanoramaView!=null)
        {
            vrPanoramaView.resumeRendering();
        }
    }
    //3.3.销毁页面,释放全景图片
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vrPanoramaView != null) {
            vrPanoramaView.shutdown();
        }
        if (imageTask != null && !imageTask.isCancelled()) {
            imageTask.cancel(true);
            imageTask = null;
        }
    }
}
