package com.example.krcm110.myapplication.com.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.krcm110.myapplication.com.view.activity.mvc.MvpBasePresenter

open abstract class MvpBaseFragment<V, T:MvpBasePresenter<V>>: Fragment()
{
    var mBasePresenter:MvpBasePresenter<V>? = null;

    @SuppressWarnings("unChecked")
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBasePresenter = createPresenter();
        mBasePresenter?.let {it?.attachView(this as V)};
    }

    @Override
    override fun onDetach() {
        super.onDetach()
        mBasePresenter?.let {it.detachView()};
    }

    protected abstract fun createPresenter():T
}