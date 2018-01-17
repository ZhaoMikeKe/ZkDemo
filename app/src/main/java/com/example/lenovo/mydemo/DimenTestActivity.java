package com.example.lenovo.mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DimenTestActivity extends AppCompatActivity {

    @BindView(R.id.bt)
    Button bt;
    @BindView(R.id.textview)
    TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimen_test);
        ButterKnife.bind(this);
        int aa = bt.getWidth();
        textview.setText(aa + "");
        //ToastUtils.showShort(getApplicationContext(), );
    }
}
