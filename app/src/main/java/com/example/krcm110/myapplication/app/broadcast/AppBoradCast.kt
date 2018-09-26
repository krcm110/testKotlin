package com.example.krcm110.myapplication.app.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AppBoradCast: BroadcastReceiver()
{
    override fun onReceive(context: Context?, intent: Intent?)
    {
        Toast.makeText(context,"i have a broadCastService",Toast.LENGTH_LONG).show()
    }
}