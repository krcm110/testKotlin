package com.example.krcm110.myapplication.app.ui.activity

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.com.Utils.AppUtils
import com.example.krcm110.myapplication.com.Utils.LogUtil
import com.example.krcm110.myapplication.com.base.BaseActivity
import kotlinx.android.synthetic.main.activity_lunch.*
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem

class ActivityLaunch:BaseActivity(){

    /**
     * 字体1
     */
    private var textTypeface: Typeface? = null;

    /**
     * 字体2
     */
    private var descTypeFace:Typeface? = null;

    /**
     * 透明动画
     */
    private var alphaAnimation:AlphaAnimation?=null
    init {
        //初始化字体库
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }


    override fun initData() {
    }

    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"

        //创建一个动画渐变展示效果
        alphaAnimation= AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(arg0: Animation) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationStart(animation: Animation) {}
        })

        checkPermission();
    }

    /**
     * 过度动画完成后启动StartActivity
     */
    fun redirectTo() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun start() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_lunch
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission(){
        val permissionItems = ArrayList<PermissionItem>()
        permissionItems.add(PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机状态", R.drawable.permission_ic_phone))
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE,"存储空间",R.drawable.permission_ic_storage))
        HiPermission.create(this)
                .title("程序需要权限")
                .msg("为了能够正常使用，请开启这些权限吧！")
                .permissions(permissionItems)
                .style(R.style.PermissionDefaultBlueStyle)
                .animStyle(R.style.PermissionAnimScale)
                .checkMutiPermission(object : PermissionCallback {
                    override fun onClose() {
                        LogUtil.i( "....","permission_onClose")
                        showToast("用户关闭了权限")
                    }

                    override fun onFinish() {
                        showToast("初始化完毕！")
                        //用户同意了权限
                        layout_parent.startAnimation(alphaAnimation)
                    }

                    override fun onDeny(permission: String, position: Int) {
                        LogUtil.i("....","permission_onDeny");
                    }

                    override fun onGuarantee(permission: String, position: Int) {
                        LogUtil.i("....","permission_onGuarantee");
                    }
                })
    }
}