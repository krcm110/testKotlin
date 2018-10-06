package com.example.krcm110.myapplication.app

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.krcm110.myapplication.app.application.MyApplication

/**
 * Created by xuhao on 2017/11/14.
 */

fun Fragment.showToast(content: String): Toast {
    val toast = Toast.makeText(this.activity?.applicationContext, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

/**

 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)

 */
fun  dip2px(context:Context,dpValue:Float ):Int{
    val scale:Float = context.getResources().getDisplayMetrics().density;
    var dd =(dpValue*scale+0.5f).toInt();
    return dd;
}


/**
 * 显示Tost提示
 */
fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(MyApplication.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}

/**
 * 判断当前的字符串是否存能匹配后面的
 */
fun isValid(contentType: String?, vararg allowTypes: String): Boolean {
    if (null == contentType || "" == contentType) {
        return false
    }
    for (type in allowTypes) {
        if (contentType.indexOf(type) > -1) {
            return true
        }
    }
    return false
}

/**
 * 判断是否有虚拟按键区域
 */
fun checkDeviceHasNavigationBar(context: Context): Boolean {
    var hasNavigationBar = false
    val rs = context.resources
    val id = rs.getIdentifier("config_showNavigationBar", "bool", "android")
    if (id > 0) {
        hasNavigationBar = rs.getBoolean(id)
    }
    try {
        val systemPropertiesClass = Class.forName("android.os.SystemProperties")
        val m = systemPropertiesClass.getMethod("get", String::class.java)
        val navBarOverride = m.invoke(systemPropertiesClass, "qemu.hw.mainkeys") as String
        if ("1" == navBarOverride) {
            hasNavigationBar = false
        } else if ("0" == navBarOverride) {
            hasNavigationBar = true
        }
    } catch (e:Exception) {
    }
    return hasNavigationBar
}


