package com.example.krcm110.myapplication.com.view.activity.mvc

import java.lang.ref.Reference
import java.lang.ref.WeakReference

abstract class BasePresenter<V>
{

    private var mViewRef:Reference<V>? = null;

    public fun attachActivity(activityed:V)
    {
        mViewRef = WeakReference<V>(activityed);
    }

    protected fun getActivity():V?
    {
        return mViewRef?.get()
    }

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