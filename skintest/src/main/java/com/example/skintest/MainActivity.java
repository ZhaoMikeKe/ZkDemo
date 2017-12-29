package com.example.skintest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import skin.support.SkinCompatManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt)
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void hehe(View view) {
        switch (view.getId()) {
            case R.id.bt:
                //SkinCompatManager.getInstance().loadSkin("haha", SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN);
                //SkinCompatManager.getInstance().restoreDefaultTheme();
                SkinCompatManager.getInstance().loadSkin("night", SkinCompatManager.SKIN_LOADER_STRATEGY_ASSETS);
                break;
        }
    }


}
