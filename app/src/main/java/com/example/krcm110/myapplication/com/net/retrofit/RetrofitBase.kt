package com.example.krcm110.myapplication.com.net.retrofit

import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.app.net.`interface`.IapiDouBan
import com.example.krcm110.myapplication.app.net.`interface`.IkaiYan
import com.example.krcm110.myapplication.com.net.tools.BasicParamsInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 * this is network connect base class
 *Referenced retorfit+okhttp+rxjava+logging-interceptor
 */
object  RetrofitBase
{
    private var client: OkHttpClient? = null
    private var retrofit:Retrofit? = null;
    private var baseUrl = "http://baobab.kaiyanapp.com/api/";

    /**
     * 创建Retorfit类
     */
     fun getRetrofit():Retrofit {
        if (retrofit == null)
        {
            synchronized(RetrofitBase::class.java)
            {
                if(retrofit==null)
                {
                    retrofit = Retrofit.Builder()
                            .client(getOkhttp())
                            .baseUrl(baseUrl)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加RXjava适配器
                            .addConverterFactory(GsonConverterFactory.create())//添加Gson适配器
                            //.addConverterFactory(ProtoConverterFactory.create())//天剑GoogleProtolBuffer适配器
                            .build()
                }
            }
        }
        return retrofit!!
    }

   /** private fun filterURL(url: String): String {
        var baseUrl = "htttp://www.baidu.com"
        if (url.contains("http://") || url.contains("https://")) {
            baseUrl = url
        }
        return baseUrl
    }**/

    /**
     * 创建一个OkHttp
     */
    private fun getOkhttp(): OkHttpClient {
        if(client==null)
        {
            synchronized(RetrofitBase::class.java)
            {
                if(client==null)
                {
                    //创建缓存文件
                    val cacheFile = File(MyApplication.context.cacheDir, "cache")
                    //设置缓存文件的大小
                    val cache = Cache(cacheFile, 1024 * 1024 * 50) //50Mb 缓存的大小
                    //构建一个Okhttp
                    client = OkHttpClient.Builder()
                            .connectTimeout(3, TimeUnit.SECONDS)//链接超时
                            .readTimeout(3, TimeUnit.SECONDS)//读取数据超时
                            .writeTimeout(3, TimeUnit.SECONDS)//写入数据超时
                            .retryOnConnectionFailure(true)//网络出现错误时重写链接
                            .cache(cache)  //添加缓存文件
                            .addInterceptor(baseIntercepter())//添加网络请求拦截器
                            .addInterceptor(getHTPLogInterreptor()).//添加网络请求拦截器设置Log信息
                                    build();//构建Okhttp
                }
            }
        }
        return client!!;
    }

    private fun getHTPLogInterreptor(): HttpLoggingInterceptor {
        //添加一个log拦截器,打印所有的log
        val logInterceptor = HttpLoggingInterceptor()
        //可以设置请求过滤的水平,body,basic,headers
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    };

    /**
     * 添加公共参数也就是头信息
     */
    private fun baseIntercepter(): BasicParamsInterceptor {
        return BasicParamsInterceptor.Builder()
                .addHeaderParam("userName", "")//添加公共参数
                .addHeaderParam("device", "")
                .build()
    };
}