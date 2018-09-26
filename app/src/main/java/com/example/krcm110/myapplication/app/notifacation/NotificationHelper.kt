package com.example.krcm110.myapplication.app.notifacation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.example.krcm110.myapplication.R

class NotificationHelper
{
    private val notificationId:Int = 10001;

    private var notificationManager:NotificationManager?= null;

    private var context:Context?= null;

    companion object {
        val instance: NotificationHelper by lazy { NotificationHelper() }
    }

    public fun sendNotifacrion(context:Context,noMa:NotificationManager)
    {
        this.context = context;
        notificationManager = noMa;
        var notification = createNotification();
        notificationManager?.notify(notificationId,notification)
    }

    private fun createNotification():Notification
    {
        var id:String ="1234";
        var notifaction:Notification;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            var channel:NotificationChannel = NotificationChannel(id,"这是一个消息",NotificationManager.IMPORTANCE_LOW);
            notificationManager?.createNotificationChannel(channel);
            notifaction = Notification.Builder(context)
                    .setChannelId(id)
                    .setContentText("你们好啊")
                    .setContentTitle("5 new messages")
                    .setAutoCancel(false)
                    .setSmallIcon(R.drawable.ic_add_grey_18dp)
                    .setBadgeIconType(R.drawable.ic_bucket_24dp)
                    .setWhen(System.currentTimeMillis())
                    .build();
        }
        else
        {
            notifaction = NotificationCompat.Builder(context)
                    .setChannelId("default")
                    .setAutoCancel(false)
                    .setContentText("你们好啊")
                    .setSmallIcon(R.drawable.ic_add_grey_18dp)
                    .setBadgeIconType(R.drawable.ic_bucket_24dp)
                    .setWhen(System.currentTimeMillis())
                    .build();
        }
        return notifaction;
    }
}