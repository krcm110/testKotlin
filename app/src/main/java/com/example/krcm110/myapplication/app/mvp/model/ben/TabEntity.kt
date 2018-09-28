package com.example.krcm110.myapplication.app.mvp.model.ben

import com.flyco.tablayout.listener.CustomTabEntity

class TabEntity (var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity
{
    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon;
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon;
    }

    override fun getTabTitle(): String {
        return title;
    }
}