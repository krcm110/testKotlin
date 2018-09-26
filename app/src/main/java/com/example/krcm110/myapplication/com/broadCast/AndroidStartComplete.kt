package com.example.krcm110.myapplication.com.broadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import com.example.krcm110.myapplication.app.service.ServiceForeground

class AndroidStartComplete: BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context,"myApplication",Toast.LENGTH_LONG).show();
        var intent:Intent = Intent(context,ServiceForeground::class.java);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
        {
            context?.startForegroundService(intent)
        }
        else
        {
            context?.startService(intent);
        }
    }
}