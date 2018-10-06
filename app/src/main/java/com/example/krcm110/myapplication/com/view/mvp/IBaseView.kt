package com.example.krcm110.myapplication.com.view.mvp

interface IBaseView
{
    /**
     * 显示加载进度
     */
    fun showLoading()

    /**
     * 隐藏进度
     */
    fun dismissLoading()
}