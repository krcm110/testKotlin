package com.example.krcm110.myapplication.app.mvp.model

import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.app.net.ConnectService
import com.example.krcm110.myapplication.com.net.SchedulerUtils
import com.example.krcm110.myapplication.com.net.retrofit.RetrofitBase
import io.reactivex.Observable

class HomeModel
{
    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int): Observable<HomeBean> {
        return ConnectService.kaiyanService.getFirstHomeData(1).compose(SchedulerUtils.ioToMain())
    }
    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return ConnectService.kaiyanService.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}