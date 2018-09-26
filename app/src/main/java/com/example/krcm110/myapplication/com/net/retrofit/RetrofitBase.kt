package com.example.krcm110.myapplication.com.net.retrofit

import com.example.krcm110.myapplication.com.net.tools.BasicParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory
import java.util.concurrent.TimeUnit

object  RetrofitBase
{
     fun getRetrofit(url:String):Retrofit
    {
        val baseUrl = filterURL(url)
        return Retrofit.Builder()
                .client(getOkhttp().build())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ProtoConverterFactory.create())
                .build()
    }

    private fun filterURL(url: String): String {
        var baseUrl = "htttp://www.baidu.com"
        if (url.contains("http://") || url.contains("https://")) {
            baseUrl = url
        }
        return baseUrl
    }

    private fun getOkhttp(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(baseIntercepter())
                .addInterceptor(getHTPLogInterreptor())
    }

    private fun getHTPLogInterreptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    };

    private fun baseIntercepter(): BasicParamsInterceptor {
        return BasicParamsInterceptor.Builder()
                .addHeaderParam("userName", "")//添加公共参数
                .addHeaderParam("device", "")
                .build()
    };
}