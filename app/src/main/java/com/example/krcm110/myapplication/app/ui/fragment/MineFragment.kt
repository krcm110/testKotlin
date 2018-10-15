package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.app.mvp.contract.MineContract
import com.example.krcm110.myapplication.app.mvp.model.ben.HomeBean
import com.example.krcm110.myapplication.app.mvp.presenter.MinePresenter
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.app.ui.adapter.MineAdapter
import com.example.krcm110.myapplication.com.view.mvp.BaseFragment
import com.example.krcm110.myapplication.com.net.excption.ErrorStatus
import kotlinx.android.synthetic.main.fragment_mine.*
import java.text.SimpleDateFormat
import java.util.*

class MineFragment: BaseFragment(), MineContract.View {
    private var num: Int = 1
    private var mMineAdapter: MineAdapter? = null

    /**
     *页面是否刷新
     */
    private var isRefresh = false
    //lazy()是接受一个 lambda 并返回一个 Lazy <T> 实例的函数，
    // 返回的实例可以作为实现延迟属性的委托： 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果，
    // 后续调用 get() 只是返回记录的结果。
    private val mPresenter by lazy { MinePresenter() }

    private var mTitle: String = "";

    private var loadingMore = false
    /**
     * 数据加载类
     */
    override fun lazyLoad() {
        mPresenter.requestHomeData(num)
    }


    override fun setHomeData(homeBean: HomeBean) {
        mLayoutStatusView?.showContent()
        // Adapter
        mMineAdapter = MineAdapter(MyApplication.context, homeBean.issueList[0].itemList)
        mMineAdapter?.setBannerSize(homeBean.issueList[0].count)

        mRecyclerView.adapter = mMineAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun addRecyleViewEvent()
    {
        //下拉加载更多
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            //当recycleView的滑动状态改变时回调
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //判断是否滚动停止
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = mRecyclerView.childCount
                    val itemCount = mRecyclerView.layoutManager.itemCount
                    //查找第一个可见的item的position
                    val firstVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        if (!loadingMore) {
                            loadingMore = true
                            mPresenter.loadMoreData()
                        }
                    }
                }
            }

            //当RecycleView滑动之后被回调
            //设置标题
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                    toolbar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.mipmap.ic_action_search_white)
                    tv_header_title.text = ""
                } else {
                    if (mMineAdapter?.mData!!.size > 1) {
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.mipmap.ic_action_search_black)
                        val itemList = mMineAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition + mMineAdapter!!.bannerItemSize - 1]
                        if (item.type == "textHeader") {
                            tv_header_title.text = item.data?.text
                        } else {
                            tv_header_title.text = simpleDateFormat.format(item.data?.date)
                        }
                    }
                }
            }
        })
    }

    private val simpleDateFormat by lazy {
        SimpleDateFormat("- MMM. dd, 'Brunch' -", Locale.ENGLISH)
    }

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }




    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
        loadingMore = false
        mMineAdapter?.addItemData(itemList)
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
        addRecyleViewEvent();
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