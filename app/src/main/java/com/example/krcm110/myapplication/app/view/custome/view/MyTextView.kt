package com.example.krcm110.myapplication.app.view.custome.view

import android.content.Context
import android.view.MotionEvent
import android.widget.TextView
import com.example.krcm110.myapplication.com.tools.LogUtil

class MyTextView: TextView
{
    constructor(context: Context) : super(context)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN->{
                LogUtil.d("View....dispatchTouchEvent","++ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{
                LogUtil.d("View....dispatchTouchEvent","++ACTION_MOVE");
            }
            MotionEvent.ACTION_CANCEL->{
                LogUtil.d("View....dispatchTouchEvent","++ACTION_CANCEL");
            }
            MotionEvent.ACTION_UP->{
                LogUtil.d("View....dispatchTouchEvent","++ACTION_UP");
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN->{
                LogUtil.d("View....onTouchEvent","++ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{
                LogUtil.d("View....onTouchEvent","++ACTION_MOVE");
            }
            MotionEvent.ACTION_CANCEL->{
                LogUtil.d("View....onTouchEvent","++ACTION_CANCEL");
            }
            MotionEvent.ACTION_UP->{
                LogUtil.d("View....onTouchEvent","++ACTION_UP");
            }
        }
        return super.onTouchEvent(event)
    }
}