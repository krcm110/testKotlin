package com.example.krcm110.myapplication.com.view.custom.button

interface ISwitchBtnImage:ISwitchBtn {
    /*
    * 设置选中前的图片
     */
    fun NormalImagedIndex(index:Int)

    /**
     * 设置选中后的图片
     */
    fun SelectImageIndex(index: Int)
}