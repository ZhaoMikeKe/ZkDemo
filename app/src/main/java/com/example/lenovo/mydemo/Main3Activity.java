package com.example.lenovo.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.view)
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        view.post(new Runnable() {
            @Override
            public void run() {
                double a = tv.getHeight();
                double b = tv.getWidth();
                double vv = Math.toDegrees(Math.atan2(a, b));
                Animation an = new RotateAnimation(0.0f, -((float) vv), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1f);
                an.setDuration(1);               // duration in ms
                an.setRepeatCount(0);                // -1 = infinite repeated
                an.setRepeatMode(Animation.REVERSE); // reverses each repeat
                an.setFillAfter(true);               // keep rotation after animation
                view.setAnimation(an);
            }
        });

    }

    @OnClick({R.id.tv, R.id.view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv:
                break;
            case R.id.view:
                break;
        }
    }
}
