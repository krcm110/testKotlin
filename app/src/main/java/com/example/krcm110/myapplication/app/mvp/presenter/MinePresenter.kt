package com.example.krcm110.myapplication.app.mvp.presenter

import com.example.krcm110.myapplication.app.mvp.contract.HomeContract
import com.example.krcm110.myapplication.app.mvp.model.HomeModel
import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.com.net.ExceptionHandle
import com.example.krcm110.myapplication.com.view.mvp.BasePresenter

class MinePresenter:BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    private val homeModel: HomeModel by lazy {
        HomeModel()
    }

    override fun requestHomeData(num: Int) {
        // 检测是否绑定 View
       // checkViewAttached()
        getView()?.showLoading()
        val disposable = homeModel.requestHomeData(num)
                .flatMap({ homeBean ->
                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val bannerItemList = homeBean.issueList[0].itemList
                    bannerItemList.filter { item ->
                        item.type=="banner2"|| item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        bannerItemList.remove(item)
                    }
                    bannerHomeBean = homeBean //记录第一页是当做 banner 数据
                    //根据 nextPageUrl 请求下一页数据
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                })
                .subscribe({ homeBean->
                    mRootView?.apply {
                        getView()?.dismissLoading()
                        nextPageUrl = homeBean.nextPageUrl
                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                        val newBannerItemList = homeBean.issueList[0].itemList
                        newBannerItemList.filter { item ->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach{ item ->
                            //移除 item
                            newBannerItemList.remove(item)
                        }
                        // 重新赋值 Banner 长度
                        bannerHomeBean!!.issueList[0].count = bannerHomeBean!!.issueList[0].itemList.size

                        //赋值过滤后的数据 + banner 数据
                        bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)
                        getView()?.setHomeData(bannerHomeBean!!)
                    }

                }, { t ->
                    mRootView?.apply {
                        getView()?.dismissLoading()
                        getView()?.showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    // fun checkViewAttached() {
       // if (!isViewAttached) throw MvpViewNotAttachedException()
  //  }

    override fun loadMoreData() {
    }
}