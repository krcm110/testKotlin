package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.mvp.contract.HomeContract
import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.app.mvp.presenter.MinePresenter
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.com.Utils.LogUtil
import com.example.krcm110.myapplication.com.view.mvp.BaseFragment
import com.example.krcm110.myapplication.com.net.excption.ErrorStatus
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment: BaseFragment(), HomeContract.View{
    private var num: Int = 1
    private var isRefresh = false
    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }

    //lazy()是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，
    // 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果，
    // 后续调用 get() 只是返回记录的结果。
    private val mPresenter by lazy { MinePresenter() }
    private var mTitle:String = "";
    override fun initView() {
        mPresenter.attachView(this)
        //内容跟随偏移
    }

    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_mine
    }

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}