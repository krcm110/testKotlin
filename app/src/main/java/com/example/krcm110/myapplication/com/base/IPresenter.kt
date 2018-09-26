package com.example.krcm110.myapplication.com.base

interface IPresenter<in V: IBaseView>
{
    fun attachView(mRootView: V)
    fun detachView()
}