package com.example.lenovo.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

public class LoggerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger_test);
        Logger.d("hello");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
