package com.example.krcm110.myapplication.com.view.mvp

interface IPresenter<V: IBaseView>
{
    /**
     * 绑定View
     */
    fun attachView(mRootView: V)

    /**
     * 接触绑定View
     */
    fun detachView()

    fun getView():V?
}