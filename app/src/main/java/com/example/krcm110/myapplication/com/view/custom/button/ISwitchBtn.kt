package com.example.krcm110.myapplication.com.view.custom.button

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
