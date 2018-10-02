package com.example.krcm110.myapplication.com.view.custom.button

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.view.custom.button.`interface`.ISwitchBtnImage
import com.example.krcm110.myapplication.com.view.custom.button.supper.BtnSwitch

open class ImageBtnSwitch: BtnSwitch, ISwitchBtnImage
{
    /**
     * 切换后的图片
     */
    private var selectImage = 0;

    /**
     * 是否自动切换
     */
    private var autoSwitch:Boolean = false;


    constructor(context:Context):super(context)
    {
        retrieveAttributes(context,null)
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)
    {
        retrieveAttributes(context,attributeSet)
    }

    /**
     * 设置显示的选中图片
     */
    override fun SelectImageIndex(index:Int)
    {
        if(index !=null && index!=0 )
        {
            normalImage = index;
        }
    }

    /**
     * 设置显示的默认图片
     */
    override fun NormalImagedIndex(index: Int)
    {
        if(index !=null && index!=0 )
        {
            selectImage = index;
        }
    }

    /**
     * 内部自动切换方法
     */
    override fun switch(boolean: Boolean)
    {
        //如果是自动切换状态
        if(autoSwitch)
        {
            status = boolean;
            if(status)
            {
                showSelectImgae();
            }
            else
            {
                showNormalImgae()
            }
        }
        else
        {
            //设置点击效果
            if(boolean)
            {
                setFilter();
            }
            else
            {
                clearFilter();
            }
        }
    }

    /**
     * 设置图片的滤镜效果
     */
    private fun setFilter()
    {
        drawable.let { drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)  }
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

        status = boolean;
        if(status)
        {
            showNormalImgae()
        }
        else
        {
            showSelectImgae();
        }
    }

    /**
     * 清除选中效果
     */
    private fun clearFilter()
    {
        drawable.let { drawable.clearColorFilter()}
    }

    /**
     * 返回当前选中状态
     */
    override fun getSelectedStatus(): Boolean
    {
        return status;
    }

    /**
     *设置当前的选中状态
     */
    override fun setToggle()
    {
        status=!status;
        if(status)
        {
            showSelectImgae();
        }
        else
        {
            showNormalImgae();
        }
    }

    /**
     * 显示默认图片
     */
    private fun showNormalImgae()
    {
        setImageResource(normalImage);
    }

    /**
     * 显示选中后的图片
     */
    private fun showSelectImgae()
    {
        setImageResource(selectImage);
    }

    /**
     * 获取XML 属性
     */
    private fun retrieveAttributes(context: Context,attributeSet: AttributeSet?)
    {
        if(attributeSet==null)
        {
            normalImage = R.mipmap.ic_launcher;
            selectImage = R.mipmap.ic_launcher;
        }
        else
        {
            val typedArray:TypedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BtnSwitch);
            normalImage = typedArray.getResourceId(R.styleable.BtnSwitch_normalStatus,R.mipmap.ic_launcher);
            selectImage = typedArray.getResourceId(R.styleable.BtnSwitch_selectStatus,0);
            autoSwitch = typedArray.getBoolean(R.styleable.BtnSwitch_autoSwitch,true);
            typedArray.recycle();
        }
        setImageResource(normalImage);
    }
}