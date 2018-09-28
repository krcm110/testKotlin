package com.example.krcm110.myapplication.com.view

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.com.data.ActivityCollection
import com.squareup.leakcanary.RefWatcher
import com.tencent.bugly.crashreport.CrashReport
import kotlin.properties.Delegates

open class ProApplication:Application()
{

    val TAG:String = this.javaClass.name+"________";
    var proApplication:ActivityCollection ?= null;

    override fun onCreate() {
        super.onCreate()
        tencentBugly();
        Log.e(TAG,"onCreate")
        if (proApplication == null)
        {
            proApplication = ActivityCollection();
        }
    }


    fun addActivity(activity:Activity)
    {
        proApplication?.addAcvicity(activity);
    }

    fun removeActivity(activity:Activity)
    {
        proApplication?.removeActivity(activity);
    }

    fun removeAllActivity()
    {
        proApplication?.removeAllActivity();
    }

    //初始化腾讯Bugly
    private fun tencentBugly()
    {
        CrashReport.initCrashReport(getApplicationContext(), "df49a3e74f", true);
        //主动抛出一个异常
        // CrashReport.testJavaCrash();
    }

    override fun attachBaseContext(base: Context?) {
        Log.e(TAG,"attachBaseContext")
        super.attachBaseContext(base)
    }

    override fun onLowMemory() {
        Log.e(TAG,"onLowMemory")
        super.onLowMemory()
    }

    override fun onTerminate() {
        Log.e(TAG,"onTerminate")
        super.onTerminate()
    }

    override fun onTrimMemory(level: Int) {
        Log.e(TAG,"onTrimMemory")
        super.onTrimMemory(level)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        Log.e(TAG,"onConfigurationChanged")
        super.onConfigurationChanged(newConfig)
    }
}