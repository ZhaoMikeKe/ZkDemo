package com.architecture.bocom.sdk;

import com.architecture.bocom.sdk.http.APIService;
import com.architecture.bocom.sdk.http.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author Su
 * @version [版本号，2017/2/16 20:12]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class CommonSDK {
    /**
     * get请求
     *
     * @param xxx      参数1
     * @param params   参数2
     * @param listener 监听
     */
    public static void requestGet(String xxx,  final OnCallbackListener listener) {
        RetrofitUtils.createServiceFrom(APIService.class).requestGet(xxx).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response == null || response.body() == null) {
                    listener.success(null);
                } else {
                    listener.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                listener.failure(t);
            }
        });

    }

    /**
     * post请求
     *
     * @param xxx      参数1
     * @param params   参数2
     * @param listener 监听
     */
    public static void requestPost(String xxx, String params, final OnCallbackListener listener) {
        RetrofitUtils.createServiceFrom(APIService.class).requestPost(xxx, params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response == null || response.body() == null) {
                    listener.success(null);
                } else {
                    listener.success(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.failure(t);
            }
        });
    }
}
