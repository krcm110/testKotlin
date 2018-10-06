package com.example.krcm110.myapplication.com.view.activity.mvc

import java.lang.ref.Reference
import java.lang.ref.WeakReference

open abstract class MvpBasePresenter<V>
{

    /**
     * View层
     */
    private var mViewRef:Reference<V>? = null;

    public fun attachView(activityed:V)
    {
        mViewRef = WeakReference<V>(activityed);
    }

    /**
     * 获取View
     */
    protected fun getView():V?
    {
        return mViewRef?.get()
    }

    /**
     * 确定是否绑定了View
     */
    fun isViewAttach():Boolean
    {
        return mViewRef!=null && mViewRef?.get()!=null;
    }


    fun detachView()
    {
        if(mViewRef!=null)
        {
            mViewRef?.clear();
            mViewRef = null;
        }
    }
}