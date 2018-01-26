package com.example.rxjavaretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.rxjavaretrofit.bean.LoGinHuangS;
import com.example.rxjavaretrofit.bean.LoginParamsBean;
import com.example.rxjavaretrofit.bean.RWBeanHS;
import com.example.rxjavaretrofit.bean.UserInfo;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    retrofit2.Retrofit retrofit;
    retrofit2.Retrofit retrofitD;
    Api api;
    Api apiD;
    Map<String, String> params;
    LoginParamsBean loginParamsBean;
    RequestBody body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://10.3.242.61:9090/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofitD = new retrofit2.Retrofit.Builder()
                .baseUrl("http://10.3.242.61:9001/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
        apiD = retrofitD.create(Api.class);
        params = new HashMap<>();
        params.put("available", "1");
        params.put("delFlag", "0");
        loginParamsBean = new LoginParamsBean();
        loginParamsBean.setUsername("user");
        loginParamsBean.setPassword("123");
        String route = new Gson().toJson(loginParamsBean);
        body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);


    }

    public void zipTest(View view) {
        //zip操作符
        Observable<List<RWBeanHS>> observable1 =
                api.register(params).subscribeOn(Schedulers.io());
        Observable<LoGinHuangS> observable2 =
                apiD.loginHS(body).subscribeOn(Schedulers.io());
        Observable.zip(observable1, observable2,
                new BiFunction<List<RWBeanHS>, LoGinHuangS, UserInfo>() {
                    @Override
                    public UserInfo apply(List<RWBeanHS> baseInfo,
                                          LoGinHuangS extraInfo) throws Exception {
                        return new UserInfo(baseInfo, extraInfo);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserInfo>() {
                    @Override
                    public void accept(UserInfo userInfo) throws Exception {
                        //do something;
                        Toast.makeText(MainActivity.this, userInfo.getLoGinHuangS().getMsg(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public void flatmapTest(View view) {
        //fiatMap
        api.register(params).subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<List<RWBeanHS>>() {
                    @Override
                    public void accept(List<RWBeanHS> rwBeanHS) throws Exception {

                        Toast.makeText(getApplicationContext(), rwBeanHS.get(0).getName(), Toast.LENGTH_SHORT).show();

                    }
                }).observeOn(Schedulers.io())//io线程进行登录
                .flatMap(new Function<List<RWBeanHS>, ObservableSource<LoGinHuangS>>() {
                    @Override
                    public ObservableSource<LoGinHuangS> apply(List<RWBeanHS> rwBeanHS) throws Exception {
                        return apiD.loginHS(body);
                    }
                }).observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<LoGinHuangS>() {
                    @Override
                    public void accept(LoGinHuangS loginResponse) throws Exception {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
