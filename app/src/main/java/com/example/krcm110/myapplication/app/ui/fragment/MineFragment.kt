package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.app.mvp.contract.HomeContract
import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.app.mvp.presenter.MinePresenter
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.app.ui.adapter.MineAdapter
import com.example.krcm110.myapplication.com.view.mvp.BaseFragment
import com.example.krcm110.myapplication.com.net.excption.ErrorStatus
import kotlinx.android.synthetic.main.fragment_mine.*

class MineFragment: BaseFragment(), HomeContract.View{
    private var num: Int = 1
    private var mHomeAdapter: MineAdapter? = null
    /**
     *页面是否刷新
     */
    private var isRefresh = false
    //lazy()是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，
    // 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果，
    // 后续调用 get() 只是返回记录的结果。
    private val mPresenter by lazy { MinePresenter() }

    private var mTitle:String = "";

    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
        // Adapter
        mHomeAdapter = MineAdapter( MyApplication.context, homeBean.issueList[0].itemList)
        mHomeAdapter?.setBannerSize(homeBean.issueList[0].count)

        mRecyclerView.adapter = mHomeAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }


    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    /**
     *  如果加载出错
     */
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    /**
     * 显示加载页
     */
    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    /**
     * 取消Lading的加载
     */
    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }

    /**
     * 和Precenter绑定
     */
    override fun initView() {
        mLayoutStatusView = multipleStatusView
        mPresenter.attachView(this)
        //内容跟随偏移
    }

    /**
     * 数据加载类
     */
    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }

    /**
     * 获取ID
     */
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