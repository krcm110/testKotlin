package com.example.krcm110.myapplication.app.handler

import android.app.Activity
import android.os.Handler
import android.os.Message
import android.util.Log
import java.lang.ref.WeakReference

class MyHandler: Handler
{
    private var weakReferencr:WeakReference<Activity>? = null;
    constructor(activity:Activity)
    {
        weakReferencr = WeakReference(activity);
    }

    override fun handleMessage(msg: Message?)
    {
        var activity:Activity? = weakReferencr?.get();
        if(activity!=null)
        {
            when(msg?.what)
            {
                1->
                {
                    Log.d("messageWhat","11111111111111");
                }
            }
        }
    }
}