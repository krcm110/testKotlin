package com.example.krcm110.myapplication.app.ui.fragment

import android.os.Bundle
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.Utils.MobileInfor
import com.example.krcm110.myapplication.com.view.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_hot.*

class HotFragment: BaseFragment() {
    private var mTitle:String = "";
    override fun initView() {

    }

    override fun lazyLoad() {
        textView.text = MobileInfor.getInfor;
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