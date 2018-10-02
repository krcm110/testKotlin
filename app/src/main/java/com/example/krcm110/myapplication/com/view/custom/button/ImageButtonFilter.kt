package com.example.krcm110.myapplication.com.view.custom.button

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.view.custom.button.`interface`.ISwitchBtn
import com.example.krcm110.myapplication.com.view.custom.button.supper.BtnSwitch

class ImageButtonFilter: BtnSwitch, ISwitchBtn
{

    constructor(context:Context):super(context)
    constructor(contex:Context,attrs: AttributeSet?):super(contex,attrs)
    {
        var typeArray:TypedArray = contex.obtainStyledAttributes(attrs, R.styleable.BtnSwitch)
        normalImage = typeArray.getResourceId(R.styleable.BtnSwitch_normalStatus,R.mipmap.ic_launcher);
        setImageResource(normalImage);
    }

    /**
     * 返回当前的状态
     */
    override fun getSelectedStatus():Boolean
    {
        return status;
    }

    /**
     * 切换状态
     */
    override fun setSelected(boolean:Boolean)
    {
        if(status==boolean)
        {
            return;
        }
        switch(status);
    }

    /**
     * 自动切换
     */
    override protected fun switch(boolean: Boolean)
    {
        status = boolean;
        if(status)
        {
            setFilter();
        }
        else
        {
            clearFilter();
        }
    }

    /*
    *设置自动切换
     */
    override fun setToggle()
    {
        status=!status;
        switch(status);
    }

    /**
     * 设置图片的滤镜效果
     */
    private fun setFilter()
    {
        drawable.let { drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)  }
    }


    /**
     * 清除选中效果
     */
    private fun clearFilter()
    {
        drawable.let { drawable.clearColorFilter()}
    }

}