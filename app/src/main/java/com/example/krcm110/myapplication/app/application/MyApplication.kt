package com.example.krcm110.myapplication.app.application

import android.content.Context
import com.example.krcm110.myapplication.com.view.ProApplication
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

class MyApplication: ProApplication()
{
    private var refWatcher: RefWatcher? = null

    override fun onCreate() {
        super.onCreate()
        MyApplication.context = applicationContext;
    }

    companion object {
        //延迟初始化防止还未初始化context的时候进行了访问
        var context: Context by Delegates.notNull()
            private set

        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }
}