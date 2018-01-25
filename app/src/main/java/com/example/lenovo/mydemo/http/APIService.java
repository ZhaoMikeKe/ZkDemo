package com.example.lenovo.mydemo.http;

import com.example.lenovo.mydemo.bean.RWBeanHS;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Su on 2016/11/11.
 * 一些公共的接口名称，放到此service文件中
 */

public interface APIService {




    public interface renwu {
        //任务列表
        @GET("tasks")
        //Call<List<RWBeanHS>> Getrenwu(@QueryMap Map<String, String> params);
        Call<List<RWBeanHS>> Getrenwu(@QueryMap Map<String, String> params);
    }
}
