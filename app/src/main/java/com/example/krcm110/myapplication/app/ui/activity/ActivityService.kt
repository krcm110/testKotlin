package com.example.krcm110.myapplication.app.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.service.ServiceBase
import com.example.krcm110.myapplication.com.service.ServiceBinder
import com.example.krcm110.myapplication.app.service.ServiceForeground
import com.example.krcm110.myapplication.com.tools.LogUtil
import com.example.krcm110.myapplication.com.view.SuperActivity
import kotlinx.android.synthetic.main.activity_service.*

class ActivityService:SuperActivity()
{

    private var downLoaderBinder: ServiceBinder.DownloadBinder? = null;
    private var hasService:Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        onClickBtn();
    }

    private var connection = object:ServiceConnection
    {
        override fun onServiceDisconnected(name: ComponentName?)
        {
            LogUtil.d(TAG,"onServiceDisconnected");
            hasService = false;
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?)
        {
            hasService = true;
            downLoaderBinder = service as ServiceBinder.DownloadBinder;
            downLoaderBinder?.startDownLoad();
            downLoaderBinder?.getProgress();
        }
    }


    fun onClickBtn()
    {
        btn_startService.setOnClickListener()
        {
            var intent:Intent = Intent(this, ServiceBase::class.java);
            startService(intent);
        }

        btn_stopService.setOnClickListener()
        {
            var intent:Intent = Intent(this, ServiceBase::class.java)
            stopService(intent);
        }

        btn_bindService.setOnClickListener()
        {
            var intent:Intent = Intent(this,ServiceBase::class.java)
            bindService(intent,connection, Context.BIND_AUTO_CREATE);
        }

        btn_unbindService.setOnClickListener()
        {

            if(hasService)
            {
                unbindService(connection)
                hasService = false;
            }
        }

        btn_foregroundStart.setOnClickListener()
        {
            var intent:Intent = Intent(this, ServiceForeground::class.java);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            {
                startForegroundService(intent)
            }
            else
            {
                startService(intent);
            }
        }

        btn_foregroundStop.setOnClickListener()
        {
            var intent:Intent = Intent(this, ServiceForeground::class.java);
            stopService(intent);
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(hasService)
        {
            unbindService(connection)
        }
    }
}