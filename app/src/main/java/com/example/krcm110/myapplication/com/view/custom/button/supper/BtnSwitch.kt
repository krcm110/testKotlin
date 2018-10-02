package com.example.krcm110.myapplication.com.view.custom.button.supper

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import org.jetbrains.anko.attr
import java.util.jar.Attributes

open abstract class BtnSwitch: ImageView
{
    /**
     * 选中前的按钮
     */
    protected var normalImage = 0;

    /**
     * 当前选中的状态
     */
    protected var status:Boolean = false

    constructor(context: Context):this(context,null)
    constructor(contex:Context,attrs:AttributeSet?):super(contex,attrs)

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            /**
             * 当鼠标点击后
             */
            MotionEvent.ACTION_DOWN->
            {
                    switch(true);
            }
            /**
             * 当鼠标移除或者松开的时候
             */
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->
            {
                if(event?.action==MotionEvent.ACTION_UP)
                {
                    performClick();
                }
                    switch(false);
            }
        }
        return true;
    }

    /**
     * 切换前的状态
     */
    abstract protected fun switch(boolean:Boolean)

}