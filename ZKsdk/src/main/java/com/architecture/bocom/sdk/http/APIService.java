package com.architecture.bocom.sdk.http;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Su on 2016/11/11.
 * 一些公共的接口名称，放到此service文件中
 */

public interface APIService {
    /**
     * get请求
     *
     * @param xxx    参数1
     * @param params 参数二，可能携带具体参数
     * @return 返回值
     */
    /*@GET("{xxx}")
    Call<String> requestGet(@Path("xxx") String xxx, @Query("params") String params);*/
    @GET("{xxx}")
    Call<Object> requestGet(@Path("xxx") String xxx);

    /**
     * post请求
     *
     * @param xxx    参数1
     * @param params 参数2
     * @return 返回值
     */
    @FormUrlEncoded
    @POST("{xxx}")
    //Call<String> requestPost(@Path("xxx") String xxx, @Field("params") String params);
    Call<String> requestPost(@Path("xxx") String xxx, @Field("params") String params);
    /**
     * 检查站
     */
    public interface JianChaZhanService {
        @GET("service/checkpoint")
        Call<List<String>> GetJianChaZhan();
    }
}
