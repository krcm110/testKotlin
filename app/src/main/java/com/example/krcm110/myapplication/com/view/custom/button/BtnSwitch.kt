package com.example.krcm110.myapplication.com.view.custom.button.supper

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView

open abstract class BtnSwitch(contex:Context,attrs:AttributeSet?): ImageView(contex,attrs)
{
    /**
     * 选中前的按钮
     */
    protected var normalImage = 0
        get

    /**
     * 当前选中的状态
     */
    protected var status:Boolean = false
        get

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            /**
             * 当鼠标点击后
             */
            MotionEvent.ACTION_DOWN->
            {
                   switch(true)
            }
            /**
             * 当鼠标移除或者松开的时候
             */
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->
            {
                if(event?.action==MotionEvent.ACTION_UP)
                {
                    performClick()
                }
                    switch(false)
            }
        }
        return true;
    }

    /**
     * 切换前的状态
     */
    abstract protected fun switch(boolean:Boolean)

}