package com.example.cbshowhide;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button button;
    private List list;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        button = findViewById(R.id.button);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    /**
     * 为列表添加测试数据
     */
    private void initData() {
        File directory = Environment.getExternalStorageDirectory();
        File[] files = directory.listFiles();
        list = new ArrayList<>();
        for (File file : files) {
            list.add(file.getName());
        }
        myAdapter = new MyAdapter(list, this);
        myAdapter.setRecyclerViewOnItemClickListener(new MyAdapter.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                myAdapter.setSelectItem(position);
            }

            @Override
            public boolean onItemLongClickListener(View view, int position) {

                //长按事件
                myAdapter.setShowBox();
                //设置选中的项
                myAdapter.setSelectItem(position);
                myAdapter.notifyDataSetChanged();

                return true;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
