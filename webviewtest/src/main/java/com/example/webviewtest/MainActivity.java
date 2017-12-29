package com.example.webviewtest;

        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webview = (WebView) findViewById(R.id.webview);
      //Test
        webview.loadUrl("http://www.baidu.com");
    }
}
