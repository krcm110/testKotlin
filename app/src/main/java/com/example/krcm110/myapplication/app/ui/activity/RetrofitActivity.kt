package com.example.krcm110.myapplication.app.ui.activity

import android.util.Log
import com.example.krcm110.myapplication.app.net.`interface`.IapiDouBan
import com.example.krcm110.myapplication.app.net.netData.MovieSubject
import com.example.krcm110.myapplication.com.net.retrofit.RetrofitBase
import com.example.krcm110.myapplication.com.view.SuperActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class RetrofitActivity:SuperActivity()
{
    public val baseURL:String = "https://api.douban.com/v2/movie/"

    override fun onDestroy() {
        super.onDestroy()

    }

    fun connetDouBan()
    {
        var retrofit = RetrofitBase.getRetrofit(baseURL);
        var iapiDouBan = retrofit.create(IapiDouBan::class.java)
        var requestData = iapiDouBan.getTop250(1,20).enqueue(object :Callback<MovieSubject>{

            override fun onResponse(call: Call<MovieSubject>?, response: Response<MovieSubject>?) {
                var movieSubject1 = response?.body();
                Log.e("krcm110...", "subjects"+movieSubject1?.subjects?.size);
            }

            override fun onFailure(call: Call<MovieSubject>?, t: Throwable?) {
                Log.e("krcm111", "net work bad")
            }
        })
    }

    fun rxConnetDouban()
    {
        var retorfit:Retrofit = RetrofitBase.getRetrofit(baseURL);
        var iapiDouBan = retorfit.create(IapiDouBan::class.java)
        iapiDouBan.postRXTop250(1,20).observeOn(Schedulers.io()).subscribe(object: Consumer<MovieSubject> {
            override fun accept(t: MovieSubject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }


    fun rxConcatDouban()
    {
        var retorfit:Retrofit = RetrofitBase.getRetrofit(baseURL);
        var iapiDouBan = retorfit.create(IapiDouBan::class.java)
        iapiDouBan.postRXTop250(1,5).observeOn(Schedulers.io()).subscribe(object: Observer<MovieSubject>
        {
            override fun onComplete() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onSubscribe(d: Disposable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: MovieSubject) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        )
    }
}
