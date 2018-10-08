package com.example.krcm110.myapplication.app.ui.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import com.airbnb.lottie.LottieAnimationView
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.com.view.mvp.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: BaseFragment() {

    private var mTitle:String = "";

    //Lottie的动画视图
    private var animationView:LottieAnimationView?=null;

    //格式化小数点
    private val format = java.text.DecimalFormat("#.##")

    override fun initView() {
        animationSeekBar.visibility = View.GONE;
        setAnimation();
        seekBar();

      //  var btnSwitchChild: BtnSwitch = ImageBtnSwitch(activity!!.baseContext,null);
        var btnSwitchChild:Button = Button(context);
        var viewGroup:ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);

        btnSwitchChild.setOnClickListener {
            //image4.setToggle();
            //showToast("你好啊${image4.getSelectedStatus()}")
        }
        parentLayout.addView(btnSwitchChild,viewGroup);
    }

    /**
     * 设置SeekBar
     */
    private fun seekBar()
    {
        animationSeekBar.setOnSeekBarChangeListener(myOnSeekBarChangeListener);
    }

    /**
     * 设置动画
     */
    private fun setAnimation()
    {
        animationView = lottie_likeanim;
        animationView?.setAnimation("animation-w465-h465.json");
        // animationView.loop(true);一直循环播放
        // animationView.playAnimation();//开始播放动画
        //if (animationView.isAnimating()) {//查看是否正在播放动画
        // Do something.动画正在运行
        // }
        //progress范围0~1f，设置动画进度
        animationView?.setProgress(0f);
        // 自定义动画时长，此处利用ValueAnimator值动画来实时更新AnimationView的进度来达到控制动画时长。
        var animator:ValueAnimator  = ValueAnimator.ofFloat(0f, 1f).setDuration(4000);
        animator.addUpdateListener(myAnimatorUpdateListener1);
        animator.start();//启动动画
    }

    /**
     * 动画播放更新事件回调
     */
    private var myAnimatorUpdateListener1: ValueAnimator.AnimatorUpdateListener =(object:ValueAnimator.AnimatorUpdateListener
    {
        override fun onAnimationUpdate(animation: ValueAnimator?) {
            var currentPrgress=animation?.getAnimatedFraction();
            animationView?.setProgress(currentPrgress!!);
            progressText.setText(format.format(currentPrgress!!*100).toString()+"%");
            if(currentPrgress>=1)
            {
                animationSeekBar.visibility = View.VISIBLE;
            }
        }
    })

    private var myOnSeekBarChangeListener: SeekBar.OnSeekBarChangeListener  = (object:SeekBar.OnSeekBarChangeListener
    {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            animationView?.setProgress(progress / 100f);
            progressText.setText(format.format(progress).toString()+"%");
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }
        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    });

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
}