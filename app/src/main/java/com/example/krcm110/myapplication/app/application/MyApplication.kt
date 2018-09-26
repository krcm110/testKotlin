package com.example.krcm110.myapplication.app.application

import android.content.Context
import com.example.krcm110.myapplication.com.view.ProApplication
import com.squareup.leakcanary.RefWatcher

class MyApplication: ProApplication()
{
    private var refWatcher: RefWatcher? = null

    companion object {
        fun getRefWatcher(context: Context): RefWatcher? {
            val myApplication = context.applicationContext as MyApplication
            return myApplication.refWatcher
        }
    }
}