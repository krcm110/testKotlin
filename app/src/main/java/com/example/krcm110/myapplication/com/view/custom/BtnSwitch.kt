package com.example.krcm110.myapplication.com.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import java.util.jar.Attributes

open abstract class BtnSwitch(context:Context,attrs: AttributeSet?): ImageView(context,attrs)
{
    /**
     * 是否开启自动切换
     * true:自动切换
     * false:手动切换
     */
    protected var autoSwitch:Boolean = true

    /**
     * 如果有选中的效果
     */
    protected var hasSelectEffect:Boolean = false;

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            /**
             * 当鼠标点击后
             */
            MotionEvent.ACTION_DOWN->{
                if(autoSwitch)
                {
                    showSwitchedStatus();
                }
            }
            /**
             * 当鼠标移除或者松开的时候
             */
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                if(hasSelectEffect)
                {
                    showNormalStatus();
                }
            }
        }
        return true;
    }

    /**
     * 切换前的状态
     */
    abstract fun showNormalStatus()

    /**
     * 切换后的状态
     */
    abstract fun showSwitchedStatus()

}