package com.example.krcm110.myapplication.app.net.`interface`

import com.example.krcm110.myapplication.app.net.netData.MovieSubject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface IapiDouBan
{
    @GET("top250")
    abstract fun getTop250(@Query("start") start: Int, @Query("count") count: Int): Call<MovieSubject>

    @FormUrlEncoded
    @POST("top250")
    abstract fun postTop250(@Field("start") start: Int, @Field("count") count: Int): Call<MovieSubject>

    @GET("top250")
    abstract fun getRXTop250(@Query("start") start: Int, @Query("count") count: Int): Observable<MovieSubject>

    @FormUrlEncoded
    @POST("top250")
    abstract fun postRXTop250(@Field("start") start: Int, @Field("count") count: Int): Observable<MovieSubject>
}
