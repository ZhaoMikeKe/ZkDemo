package com.architecture.bocom.sdk.http;

import com.architecture.bocom.sdk.ConfigValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Su on 2016/11/11.
 * 访问网络请求Retrofit请求工具类
 */

public class RetrofitUtils {


    private static Retrofit mRetrofit;

    /**
     * 获取Retrofit对象
     *
     * @return Retrofit对象
     */
    public static Retrofit getRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (mRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder().
                    connectTimeout(60, TimeUnit.MINUTES).
                    readTimeout(60, TimeUnit.MINUTES).
                    writeTimeout(60, TimeUnit.MINUTES).build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ConfigValue.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public static <T> T createServiceFrom(Class<T> serviceClass) {
        return getRetrofit().create(serviceClass);
    }
}
