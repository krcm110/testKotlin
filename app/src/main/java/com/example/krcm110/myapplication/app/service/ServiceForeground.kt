package com.example.krcm110.myapplication.app.service

import android.app.Notification
import android.content.Intent
import android.os.IBinder
import com.example.krcm110.myapplication.R
import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.os.Build
import com.example.krcm110.myapplication.app.view.activity.MainActivity
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.example.krcm110.myapplication.com.service.ServiceBase
import com.example.krcm110.myapplication.com.tools.LogUtil
import java.util.*


class ServiceForeground: ServiceBase()
{
    override fun onCreate() {
        super.onCreate()
        startForegrounddd();
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Thread(object:Runnable{
            override fun run() {
                while (true)
                {
                    Log.d("krcm110","111111111111111111111111");
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId)
    }

    private fun startForegrounddd()
    {
        var build:Notification;
        var chanelId = "my_channel_02";
        var nfIntent:Intent  = Intent(this, MainActivity::class.java);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            build = Notification.Builder(this.getApplicationContext())
                    .setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round)) // 设置下拉列表中的图标(大图标)
                    .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                    .setContentText("要显示的内容") // 设置上下文内容
                    .setWhen(System.currentTimeMillis())
                    .setChannelId(chanelId)
                    .build(); // 设置该通知发生的时间
            startForeground(11111,build);
        }
        else
        {
            build = NotificationCompat.Builder(this.getApplicationContext())
                    .setChannelId(chanelId)
                    .setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0)) // 设置PendingIntent
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher_round)) // 设置下拉列表中的图标(大图标)
                    .setContentTitle("下拉列表中的Title") // 设置下拉列表里的标题
                    .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                    .setContentText("要显示的内容") // 设置上下文内容
                    .setWhen(System.currentTimeMillis())
                    .build();
            startForeground(110, build);// 开始前台服务
        }
        //stmNM = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //stmNM?.notify(110,build)
        object :Thread()
        {
            override fun run()
            {
                while (true)
                {
                    LogUtil.d("krcm110","dddd");
                }
                super.run()
            }
        }.start()
    }


    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }
}