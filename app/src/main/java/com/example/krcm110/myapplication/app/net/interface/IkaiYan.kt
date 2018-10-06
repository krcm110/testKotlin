package com.example.krcm110.myapplication.app.net.`interface`

import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IkaiYan
{
    /**
     * 首页精选
     */
    @GET("v2/feed")
    fun getFirstHomeData(@Query("num") num:Int): Observable<HomeBean>

    /**
     * 根据 nextPageUrl 请求数据下一页数据
     */
    @GET
    fun getMoreHomeData(@Url url: String): Observable<HomeBean>
}