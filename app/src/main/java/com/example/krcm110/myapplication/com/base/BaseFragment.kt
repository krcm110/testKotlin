package com.example.krcm110.myapplication.com.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView
import com.example.krcm110.myapplication.app.application.MyApplication

abstract class BaseFragment: Fragment() {
    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(getLayoutId(),null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView()
        lazyLoadDataIfPrepared()
        //多种状态切换的view 重试点击事件
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }


    /**
     *多种状态的 View 的切换进行了事件监听
     */
    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }


    //Android应用开发过程中，ViewPager同时加载多个fragment，
    // 以实现多tab页面快速切换, 但是fragment初始化时若加载的内容较多，
    // 就可能导致整个应用启动速度缓慢，影响用户体验。
    //为了提高用户体验，我们会使用一些懒加载方案，实现加载延迟。
    // 这时我们会用到getUserVisibleHint()与setUserVisibleHint()这两个方法。
    //@isVisibleToUser 当前Fragment是否可见 value=true 可见 value=false 不可见
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    /**
     * 加载数据
     */
    private fun lazyLoadDataIfPrepared() {
        //如果当前视图处于可见状态
        //如果当前视图加载完成
        //如果当前数据还未被加载过
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    /**
     * 初始化 ViewI
     */
    abstract fun initView()

    /**
     * 懒加载
     */
    abstract fun lazyLoad()


    /**
     * 加载布局
     * 返回布局的ID
     */
    @LayoutRes
    abstract fun getLayoutId():Int

    /**
     * 销毁时候用LeakCanary监听Fragment的内存泄漏情况
     */
    override fun onDestroy() {
        super.onDestroy()
        MyApplication.getRefWatcher(activity!!)?.watch(activity)
    }
}