package com.example.krcm110.myapplication.com.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.RuntimeException

/**
 * 此Presenter绑定了
 * 此Presenter基于RXJAVA,保证了在和View层解除关系的时候能够解除所有的订阅关系
 *
 */
open class BasePrensenter<T:IBaseView>: IPresenter<T>
{
    //把View设置成私有的变量标准外部内能访问的同时不能够设置值
    var mRootView: T? = null
    private set;

    //可以快速解除所有添加的Disposable类
    private var compositeDisposable = CompositeDisposable()

    /**
     * 通过attachView绑定具体的View层
     * @param mRootView 具体的View层
     */
    override fun attachView(mRootView: T)
    {
        this.mRootView = mRootView;
    }

    /**
     * 解除绑定关系
     * 解除基于RXJAVA的订阅关系
     */
    override fun detachView()
    {
        this.mRootView = null;
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    /**
     * 查看是否绑定得有View层
     */
    private val isViewAttached: Boolean
        get() = mRootView != null

    /**
     * 检测是否绑定得有View如果没有则抛出异常
     */
    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    //private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

    internal  class  MvpViewNotAttachedException:RuntimeException
    {
        constructor()
        {
            RuntimeException("Please call IPresenter.attachView(IBaseView) before" +" requesting data to the IPresenter")
        }
    }
}