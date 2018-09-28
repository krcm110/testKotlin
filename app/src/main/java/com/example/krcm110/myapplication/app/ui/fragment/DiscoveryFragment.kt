package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.base.BaseFragment

class DiscoveryFragment: BaseFragment() {
    private var mTitle:String? = "";

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_discovery;
    }

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}