package com.example.krcm110.myapplication.app.ui

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.HorizontalScrollView
import android.widget.ScrollView

class HorizontalScrollView (context: Context, attrs: AttributeSet):HorizontalScrollView(context,attrs) {
    private var mGestureDetector: GestureDetector?=null;
    init {
        mGestureDetector = GestureDetector(context, XScrollDetector())
    }

    var startDownX:Float = 0f;
    var startDownY:Float = 0f;

    override  fun  onInterceptTouchEvent(ev: MotionEvent):Boolean
    {
        when(ev.action)
        {
            MotionEvent.ACTION_DOWN->{
                startDownX = ev.rawX;
                startDownY = ev.rawY;
            }
            MotionEvent.ACTION_MOVE->{
                //如果产生了移动
                if(startDownX!= ev.rawX || startDownY!=ev.y)
                {
                    return true;
                }
            }
            MotionEvent.ACTION_UP->{}
            MotionEvent.ACTION_CANCEL->{}

        }
      return  super.onInterceptTouchEvent(ev);
    }
    class XScrollDetector: GestureDetector.SimpleOnGestureListener() {
        override public fun  onScroll(e1: MotionEvent, e2: MotionEvent, distanceX:Float, distanceY:Float ) :Boolean {
            //如果我们滚动更接近水平方向,返回false,让子视图来处理它  
            return (Math.abs(distanceY) > Math.abs(distanceX));
        }
    }
}

