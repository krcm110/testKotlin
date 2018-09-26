package com.example.krcm110.myapplication.com.tools

import android.os.Build

/**
 * 自己统计的Android_SDK版本差异
 */
class AndroidSDKVersion
{
    companion object
    {
        //1............start....................================================
        //Android 此版本以后有对Android复选框做限制
        //AlertDialog
        //用Settings.canDrawOverlays(this)判断系统是否允许复选框显示
        //1............end.......................================================
        val SDK_23:Int = Build.VERSION_CODES.M;

        //1...................start............===================================
        //Android此版本以后Notification受到限制,在构建Notifcation的时候要加入渠道ID
        //var channel:NotificationChannel = NotificationChannel();
        //NotificationManager.createNotificationChannel(channel);
        //1...................end.............=====================================
        val SDK_26:Int = Build.VERSION_CODES.O
    }
}