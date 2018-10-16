package com.example.krcm110.myapplication.com.Utils

import android.content.Context
import android.os.Build
import java.util.regex.Pattern

class MobileInfor {
    companion object {

        val getInfor by lazy{
            var sb  =  StringBuffer();
            sb.append("主板： "+ Build.BOARD+"\n");
            sb.append("系统启动程序版本号： " + Build.BOOTLOADER+"\n");
            sb.append("系统定制商：" + Build.BRAND+"\n");
            sb.append("cpu指令集： " + Build.CPU_ABI+"\n");
            sb.append("cpu指令集2 "+ Build.CPU_ABI2+"\n");
            sb.append("设置参数： "+ Build.DEVICE+"\n");
            sb.append("显示屏参数：" + Build.DISPLAY+"\n");
            sb.append("无线电固件版本：" + Build.getRadioVersion()+"\n");
            sb.append("硬件识别码：" + Build.FINGERPRINT+"\n");
            sb.append("硬件名称：" + Build.HARDWARE+"\n");
            sb.append("HOST: " + Build.HOST+"\n");
            sb.append("修订版本列表：" + Build.ID+"\n");
            sb.append("硬件制造商：" + Build.MANUFACTURER+"\n");
            sb.append("版本：" + Build.MODEL+"\n");
            sb.append("硬件序列号：" + Build.SERIAL+"\n");
            sb.append("手机制造商：" + Build.PRODUCT+"\n");
            sb.append("描述Build的标签：" + Build.TAGS+"\n");
            sb.append("TIME: " + Build.TIME+"\n");
            sb.append("builder类型：" + Build.TYPE+"\n");
            sb.append("USER: " + Build.USER+"\n");
            sb.append("这是什么:"+Build.VERSION.INCREMENTAL)
            sb.append("USER: " + Build.FINGERPRINT+"\n");
        }
    }
}