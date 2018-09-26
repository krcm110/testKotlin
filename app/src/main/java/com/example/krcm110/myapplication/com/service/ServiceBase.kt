package com.example.krcm110.myapplication.com.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.krcm110.myapplication.com.tools.LogUtil

open class ServiceBase: Service()
{
    protected val TAG:String = ServiceBase::class.java.name + "________________";

    override fun onCreate() {
        super.onCreate()
        LogUtil.d(TAG,"onCreate");
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LogUtil.d(TAG,"onStartCommand1");
      //  stopSelf();
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.d(TAG,"onDestroy");
    }

    override fun onBind(intent: Intent?): IBinder?
    {
        LogUtil.d(TAG,"onBind");
        return null;
    }

}