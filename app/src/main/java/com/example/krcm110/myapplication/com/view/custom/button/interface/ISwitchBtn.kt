package com.example.krcm110.myapplication.com.view.custom.button.`interface`

interface ISwitchBtn
{
    /**
     * 切换状态
     */
    fun setSelected(boolean:Boolean)

    /**
     * 返回当前的状态
     */
    fun getSelectedStatus():Boolean

    /**
     * 更具当前的状态自动切换
     */
    fun setToggle()
}

interface ISwitchBtnImage : ISwitchBtn
{
    /*
    * 设置选中前的图片
     */
    fun NormalImagedIndex(index:Int)

    /**
     * 设置选中后的图片
     */
    fun SelectImageIndex(index: Int)
}