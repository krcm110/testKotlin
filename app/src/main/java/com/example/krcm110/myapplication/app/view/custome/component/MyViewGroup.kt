package com.example.krcm110.myapplication.app.view.custome.component

import android.content.Context
import android.view.MotionEvent
import android.widget.LinearLayout
import com.example.krcm110.myapplication.com.Utils.LogUtil

class MyViewGroup: LinearLayout
{
    constructor(context: Context):super(context);

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean
    {
        when(ev?.action)
        {
            MotionEvent.ACTION_UP->{
                LogUtil.d("ViewGroup....dispatchTouchEvent","ACTION_UP");
            }
            MotionEvent.ACTION_DOWN->{
                LogUtil.d("ViewGroup....dispatchTouchEvent","ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{
                LogUtil.d("ViewGroup....dispatchTouchEvent","ACTION_MOVE");
            }
            MotionEvent.ACTION_CANCEL->{
                LogUtil.d("ViewGroup....dispatchTouchEvent","ACTION_CANCEL");
            }
        }
        return true;
       // return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action)
        {
            MotionEvent.ACTION_UP->{
                LogUtil.d("ViewGroup....onInterceptTouchEvent","ACTION_UP");
            }
            MotionEvent.ACTION_DOWN->{
                LogUtil.d("ViewGroup....onInterceptTouchEvent","ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{
                LogUtil.d("ViewGroup....onInterceptTouchEvent","ACTION_MOVE");
            }
            MotionEvent.ACTION_CANCEL->{
                LogUtil.d("ViewGroup....onInterceptTouchEvent","ACTION_CANCEL");
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            MotionEvent.ACTION_UP->{
                LogUtil.d("ViewGroup....onTouchEvent","ACTION_UP");
            }
            MotionEvent.ACTION_DOWN->{
                LogUtil.d("ViewGroup....onTouchEvent","ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{
                LogUtil.d("ViewGroup....onTouchEvent","ACTION_MOVE");
            }
            MotionEvent.ACTION_CANCEL->{
                LogUtil.d("ViewGroup....onTouchEvent","ACTION_CANCEL");
            }
        }
        return super.onTouchEvent(event)
    }
}