package com.example.rxjavaretrofit;

import com.example.rxjavaretrofit.bean.LoGinHuangS;
import com.example.rxjavaretrofit.bean.RWBeanHS;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by zhaoke on 2018/1/26.
 */

public interface Api {


    @GET("tasks")
    Observable<List<RWBeanHS>> register(@QueryMap Map<String, String> params);

    @POST("auth/users/validation")
    Observable<LoGinHuangS> loginHS(@Body RequestBody body);

}
