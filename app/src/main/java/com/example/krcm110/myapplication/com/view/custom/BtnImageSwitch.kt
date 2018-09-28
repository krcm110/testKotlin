package com.example.krcm110.myapplication.com.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import com.example.krcm110.myapplication.R

class BtnImageSwitch(contxt:Context,attrs: AttributeSet):BtnSwitch(contxt,attrs)
{
    /**
     * 默认显示的图片
     */
    private var normalPng = 0;

    /**
     * 切换后的图片
     */
    private var switchImage = 0;

    init {
       var typedArray:TypedArray = contxt.obtainStyledAttributes(attrs, R.styleable.BtnSwitch);
        normalPng = typedArray.getResourceId(R.styleable.BtnSwitch_normalStatus,R.mipmap.ic_discovery_normal);
        switchImage = typedArray.getResourceId(R.styleable.BtnSwitch_selectStatus,0);
        autoSwitch = typedArray.getBoolean(R.styleable.BtnSwitch_autoSwitch,true);
        typedArray.recycle();

        setImageResource(normalPng);
    }

    override fun showSwitchedStatus()
    {
        if(switchImage==0)
        {
            setFilter();
        }
        else
        {
            setImageResource(switchImage)
        }

        hasSelectEffect = true;
    }

    override fun showNormalStatus() {
        if(switchImage==0)
        {
            removeFilter();
        }
        else
        {
            setImageResource(normalPng);
        }
        hasSelectEffect = false;
    }

    /**
     * 设置图片的滤镜效果
     */
    private fun setFilter()
    {
        drawable.let { drawable.setColorFilter(Color.GRAY,PorterDuff.Mode.MULTIPLY)  }
    }

    /**
     * 移除滤镜效果
     */
    private fun removeFilter()
    {
        drawable.let { drawable.clearColorFilter()}
    }
}