package com.example.krcm110.myapplication.app

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast
import com.example.krcm110.myapplication.app.application.MyApplication
import com.example.krcm110.myapplication.com.view.ProApplication

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


fun Context.showToast(content: String): Toast {
    val toast = Toast.makeText(MyApplication.context, content, Toast.LENGTH_SHORT)
    toast.show()
    return toast
}





