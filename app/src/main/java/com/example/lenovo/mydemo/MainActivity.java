package com.example.lenovo.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.c1)
    ConstraintLayout c1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);




    }



}