package com.example.krcm110.myapplication.com.service

import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.krcm110.myapplication.com.tools.LogUtil

class ServiceBinder:ServiceBase()
{
    private var downloadBinder:DownloadBinder = DownloadBinder();

    override fun onBind(intent: Intent?): IBinder? {
        return downloadBinder
    }

    inner class DownloadBinder: Binder()
    {
        public fun startDownLoad()
        {
            LogUtil.d(TAG,"startDownLoad")
            while (true)
            {
                LogUtil.d("dile","foreach")
            }
        }

        public fun  getProgress():Int
        {
            LogUtil.d(TAG,"getProgress execued")
            return 0;
        }
    }
}