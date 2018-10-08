package com.example.krcm110.myapplication.com.view.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.Reference
import java.lang.ref.WeakReference

open class BasePresenter<T : IBaseView> : IPresenter<T> {

    /**
     * 持有的View
     */
    var mRootView: Reference<T>? = null
        private set

    //统一管理RXjava
    private var compositeDisposable = CompositeDisposable()


    override fun attachView(mRootView: T) {
        this.mRootView = WeakReference(mRootView)
    }

    override fun detachView() {
        mRootView = null
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    override fun getView(): T? {
        return mRootView?.get();
    }

    private val isViewAttached: Boolean
        get() = mRootView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }
    /**
     * 将创建的RX赋值给compositeDisposable进行统一管理
     */
    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")
}