package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HotFragment: BaseFragment() {
    private var mTitle:String = "";
    override fun initView() {

    }

    override fun lazyLoad() {

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_hot
    }

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}