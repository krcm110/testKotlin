package com.example.krcm110.myapplication.app.view.custome.component

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.content.LocalBroadcastManager
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.LinearLayout
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.broadcast.AppBoradCast
import kotlinx.android.synthetic.main.custom_title.view.*

class TitleLayout : LinearLayout {
    public var TAG:String = this.javaClass.name + "___";

    private var localBroadcastManager:LocalBroadcastManager?=null
    private var appBoradCast:AppBoradCast? = null
    private var interFilter:IntentFilter? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_title,this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIcon)
        btn_BackActivity.setOnClickListener()
        {
            appBoradCast = AppBoradCast();
            interFilter = IntentFilter();

            interFilter?.addAction("com.example.krcm110.maapplication.broadCast")
            localBroadcastManager = LocalBroadcastManager.getInstance(context)

           localBroadcastManager!!.registerReceiver(appBoradCast!!,interFilter!!)

           var intent:Intent = Intent("com.example.krcm110.maapplication.broadCast");
            localBroadcastManager?.sendBroadcast(intent);
        }

        btnQuitAppActivity.setOnClickListener()
        {
            Log.e(TAG,"btnQuitAppActivity");
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        appBoradCast?.let { localBroadcastManager?.unregisterReceiver(appBoradCast!!) }
    }

}
