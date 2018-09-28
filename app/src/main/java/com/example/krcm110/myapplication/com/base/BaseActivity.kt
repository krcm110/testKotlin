package com.example.krcm110.myapplication.com.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.classic.common.MultipleStatusView
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.com.view.ProApplication

abstract class BaseActivity: AppCompatActivity()
{
    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())



        initData()
        initView()
        start()
        initListener()
    }



    /**
     * 加载布局
     */
    abstract fun layoutId():Int

    /**
     * 初始化数据
     */
    abstract fun initData()
    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 开始请求
     */
    abstract fun start()

    /**
     * 打卡软键盘
     */
    fun openKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: EditText, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * 多态视图的点击事件监听
     */
    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open var mRetryClickListener: View.OnClickListener = View.OnClickListener {
        start();
    }

    override fun onDestroy()
    {
        super.onDestroy()
        MyApplication.getRefWatcher(this)?.watch(this);
    }
}