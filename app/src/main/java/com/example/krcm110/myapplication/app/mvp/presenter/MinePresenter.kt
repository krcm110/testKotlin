package com.example.krcm110.myapplication.app.mvp.presenter

import com.example.krcm110.myapplication.app.mvp.contract.MineContract
import com.example.krcm110.myapplication.app.mvp.model.HomeModel
import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.com.net.ExceptionHandle
import com.example.krcm110.myapplication.com.view.mvp.BasePresenter

class MinePresenter:BasePresenter<MineContract.View>(), MineContract.Presenter {
    private var vidoHomeBean: HomeBean? = null //最终生成的数据

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    private val homeModel: HomeModel by lazy { HomeModel() }//最终一个数据生成

    override fun requestHomeData(num: Int) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.get()?.showLoading()
        val disposable = homeModel.requestHomeData(num)
                .flatMap({ homeBean ->
                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val bannerItemList = homeBean.issueList[0].itemList
                    //过滤Type为banner2或者为horizontalScrollCard的数据**/
                    filterArrayData(bannerItemList);
                    vidoHomeBean = homeBean //记录第一页是当做 banner 数据
                    //根据 nextPageUrl 请求下一页数据
                    homeModel.loadMoreData(homeBean.nextPageUrl)
                })
                .subscribe({ homeBean->
            mRootView?.get()?.apply {
                getView()?.dismissLoading()
                nextPageUrl = homeBean.nextPageUrl
                //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                val newBannerItemList = homeBean.issueList[0].itemList
                filterArrayData(newBannerItemList);
                // 重新赋值 Banner 长度
                vidoHomeBean!!.issueList[0].count = vidoHomeBean!!.issueList[0].itemList.size

                //赋值过滤后的数据 + banner 数据
                vidoHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)
                getView()?.setHomeData(vidoHomeBean!!)
            }
        }, { t ->
            mRootView?.get()?.apply {
                getView()?.dismissLoading()
                getView()?.showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
            }
        })
        addSubscription(disposable)
    }

    /**
     * 过滤数据
     */
    private fun filterArrayData(arrayList:ArrayList<HomeBean.Issue.Item>):ArrayList<HomeBean.Issue.Item>
    {
        arrayList.filter { item ->
            item.type=="banner2"|| item.type=="horizontalScrollCard"
        }.forEach{ item ->
            //移除 item
            arrayList.remove(item)
        }//过滤Type为banner2或者为horizontalScrollCard的数据
        return arrayList;
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    override fun loadMoreData() {
        val disposable = nextPageUrl?.let {
            homeModel.loadMoreData(it)
                    .subscribe({ homeBean->
                        mRootView?.apply {
                            //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                            val newItemList = homeBean.issueList[0].itemList
                            newItemList.filter { item ->
                                item.type=="banner2"||item.type=="horizontalScrollCard"
                            }.forEach{ item ->
                                //移除 item
                                newItemList.remove(item)
                            }

                            nextPageUrl = homeBean.nextPageUrl
                            getView()?.setMoreData(newItemList)
                        }

                    },{ t ->
                        mRootView?.apply {
                            getView()?.showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })
        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}