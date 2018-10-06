package com.example.krcm110.myapplication.app.net

import com.example.krcm110.myapplication.app.net.`interface`.IkaiYan
import com.example.krcm110.myapplication.com.net.retrofit.RetrofitBase

object ConnectService
{
    //使用RetrofitBase链接网络
    val kaiyanService:IkaiYan by lazy { RetrofitBase.getRetrofit().create(IkaiYan::class.java) }
}