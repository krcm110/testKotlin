package com.example.krcm110.myapplication.app.ui

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ScrollView

class VerticalScrollView(context: Context, attrs: AttributeSet): ScrollView(context,attrs) {
    private var mGestureDetector: GestureDetector?=null;
    init {
        mGestureDetector = GestureDetector(context, YScrollDetector())
    }

    override public fun  onInterceptTouchEvent(ev:MotionEvent ):Boolean
    {
        return super.onInterceptTouchEvent(ev)&& mGestureDetector!!.onTouchEvent(ev);
    }
    class YScrollDetector: GestureDetector.SimpleOnGestureListener() {
      override public fun  onScroll(e1:MotionEvent , e2:MotionEvent ,distanceX:Float , distanceY:Float ) :Boolean {
            //如果我们滚动更接近水平方向,返回false,让子视图来处理它  
          return (Math.abs(distanceY) > Math.abs(distanceX));
      }
    }
}

