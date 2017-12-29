package com.example.litepal;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.wyh.slideAdapter.ItemBind;
import com.wyh.slideAdapter.ItemView;
import com.wyh.slideAdapter.SlideAdapter;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rel)
    RecyclerView rel;
    @BindView(R.id.liji)
    Button liji;
    @BindView(R.id.yuyue)
    Button yuyue;
    List<BeanHY> data1;
    SlideAdapter slideAdapter;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SQLiteDatabase db = Connector.getDatabase();
        data1 = DataSupport.findAll(BeanHY.class);
//赵珂123
        ItemBind itemBind = new ItemBind<BeanHY>() {
            @Override
            public void onBind(ItemView itemView, final BeanHY data, final int position) {
                itemView.setText(R.id.cjr, data.getName());
                itemView.setText(R.id.sj, data.getShijian());
                itemView.setText(R.id.zhuti, data.getZhut());

                itemView.setOnLongClickListener(R.id.itemall, new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        new AlertDialog.Builder(MainActivity.this)

                                .setTitle("要删除此项吗？")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                // TODO Auto-generated method stub
                                                DataSupport.deleteAll(BeanHY.class, "name=? and shijian=?", data1.get(position).getName(), data1.get(position).getShijian());

                                                data1.remove(position);
                                                slideAdapter.notifyDataSetChanged();
                                            }
                                        }).setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //BeanHY beanHY = data1.get(position);
                                showpopup(2, position);
                            }
                        }).setNeutralButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setCancelable(false).create()
                                .show();
                        return true;
                    }
                });


            }
        };
        rel.setLayoutManager(new LinearLayoutManager(this));
        slideAdapter = SlideAdapter.load(data1)
                .item(R.layout.item_hysy) //指定布局
                .bind(itemBind)//加载数据
                .padding(10)
                .into(rel);  //填充到recyclerView中
    }

    @OnClick({R.id.liji, R.id.yuyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liji:

                break;
            case R.id.yuyue:
                showpopup(1, 0);
                break;
        }
    }

    private PopupWindow mPopWindow;
    Button quxiao;
    Button queding;
    EditText ren;
    EditText shijian;
    EditText zhuti;

    @SuppressLint("WrongConstant")
    private void showpopup(final int a, final int b) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.popupwindow, null);
        mPopWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //poupwindow跟随键盘移动
        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.setContentView(view);
        ren = (EditText) view.findViewById(R.id.etchuangjianren);
        shijian = (EditText) view.findViewById(R.id.etshijian);
        zhuti = (EditText) view.findViewById(R.id.etzhuti);
        queding = (Button) view.findViewById(R.id.queding);
        quxiao = (Button) view.findViewById(R.id.quxiao);
        if (a == 2) {
            ren.setText(data1.get(b).getName());
            shijian.setText(data1.get(b).getShijian());
            zhuti.setText(data1.get(b).getZhut());
        }
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a == 1) {
                    BeanHY beanHY = new BeanHY();
                    beanHY.setName(ren.getText().toString());
                    beanHY.setShijian(shijian.getText().toString());
                    beanHY.setZhut(zhuti.getText().toString());
                    beanHY.save();

                    mPopWindow.dismiss();
                    data1.add(beanHY);
                    slideAdapter.notifyDataSetChanged();
                } else if (a == 2) {
                    BeanHY beanHY = data1.get(b);

                    beanHY.setName(ren.getText().toString());
                    beanHY.setShijian(shijian.getText().toString());
                    beanHY.setZhut(zhuti.getText().toString());

                    DataSupport.deleteAll(BeanHY.class, "name=? and shijian=?", data1.get(b).getName(), data1.get(b).getShijian());
                    beanHY.save();
                    mPopWindow.dismiss();
                    data1.remove(b);
                    data1.add(beanHY);
                    slideAdapter.notifyDataSetChanged();
                }
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopWindow.dismiss();
            }
        });

        //mPopWindow.setBackgroundDrawable(new BitmapDrawable());//注意这里如果不设置，下面的setOutsideTouchable(true);允许点击外部消失会失效
        mPopWindow.setOutsideTouchable(false);   //设置外部点击关闭ppw窗口

        mPopWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
    }


}
