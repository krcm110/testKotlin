package com.example.krcm110.myapplication.com.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.os.IBinder
import android.os.PersistableBundle
import android.view.inputmethod.InputMethodManager
import com.example.krcm110.myapplication.com.Utils.ViewTools


open class SuperActivity: AppCompatActivity() {

    private var index:Int=0;
    /**
     *当输入键盘出来的时候,点击空白区域不会关闭键盘
     */
    protected var isShowKeyboard:Boolean = false;

    protected var TAG:String = this.javaClass.name+"______"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        (application as ProApplication).addActivity(this)
    }

    override fun onRestart() {
        Log.e(TAG,"onRestart")

        super.onRestart()
    }

    override fun onStart() {
        Log.e(TAG,"onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.e(TAG,"onResume")
        super.onResume()
       // dd();
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG,"onStop")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    /**
     * 于PostCreate相同，只是优先于onPostCreate
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }
    /**
     * 第一次启动不调用，以后每次启动都会调用
     */
    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        Log.e(TAG,"onPostCreate")
        super.onPostCreate(savedInstanceState, persistentState)
    }


    /**
     * 屏幕点击事件
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (isShowKeyboard && ev?.getAction() === MotionEvent.ACTION_DOWN) {
            //当前有焦点的View
            val v = currentFocus;
            //如果键盘正在显示
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideKeyboard(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            //新申请了一个坐标
            val l = intArrayOf(0, 0)
            //在父窗口的坐标位置
            v!!.getLocationInWindow(l)

            //left0
            val left = l[0]
            //left1
            val top = l[1]

            //获取被点View视图坐标的最大值
            val bottom = top + v!!.getHeight()
            //获取被点View视图坐标的最小值
            val right = left + v!!.getWidth()
            return if (event.x > left && event.x < right && event.y > top && event.y < bottom)
            {
                // 点击EditText的事件，忽略它。
                false
            }
            else
            {
                true
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 分析1：onUserInteraction()
     * 作用：实现屏保功能
     * 注：
     *    a. 该方法为空方法
     *    b. 当此activity在栈顶时，触屏点击按home，back，menu键等都会触发此方法
     *    c.疑惑，点击了屏幕它都在触发，但又不是不必现
     */
    override fun onUserInteraction()
    {
        super.onUserInteraction()
        Log.e("我点击了屏幕的按键","ssssss");
    }

    private fun showChrends()
    {
        var list:List<View>  = ViewTools.getAllChildViews(getWindow().getDecorView());
        Log.d("size...." ,"++"+ list.size);
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     * @param token
     */
    private fun hideKeyboard(token: IBinder?) {
        if (token != null) {
            val im:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            im!!.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 当用户调用了Back键
     * 或者执行了Finish
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG,"OnDestroy")
        (application as ProApplication).removeActivity(this)
    }
}