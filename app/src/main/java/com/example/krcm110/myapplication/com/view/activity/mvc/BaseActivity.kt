package com.example.krcm110.myapplication.com.view.activity.mvc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity


abstract class BaseActivity<V, T:BasePresenter<V>>:AppCompatActivity()
{
    var mPresenter:BasePresenter<V>? = null;

    @SuppressWarnings("unChecked")
    @Override
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        mPresenter = createPrensenter();
        mPresenter?.attachActivity(this as V);
    }

    protected override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView();
    }

    protected abstract fun  createPrensenter():T
}