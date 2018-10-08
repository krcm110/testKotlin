package com.example.krcm110.myapplication.app.mvp.contract

import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.com.view.mvp.IBaseView
import com.example.krcm110.myapplication.com.view.mvp.IPresenter

class HomeContract {
    interface View : IBaseView {
        /**
         * 设置第一次请求的数据
         */
        fun setHomeData(homeBean: HomeBean)
        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>)
        /**
         * 显示错误信息
         */
        fun showError(msg: String, errorCode: Int)
    }

    /**
     * 定于Presenter的接口
     */
    interface Presenter : IPresenter<View> {

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()
    }
}

