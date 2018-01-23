package com.example.lenovo.mydemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

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
                textview.setText(aa + "");
                d.setText(metrics.densityDpi + "");
                d1.setText(metrics.density + "");
                d2.setText(((float) metrics.widthPixels / metrics.density) + "");
                d3.setText(Math.sqrt(Math.pow(metrics.widthPixels, 2) + Math.pow(metrics.heightPixels, 2)) + "");

            }
        }, 2000);

    }
}
