package com.example.krcm110.myapplication.app.ui.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.krcm110.myapplication.app.view.custome.component.MyViewGroup
import com.example.krcm110.myapplication.app.view.custome.view.MyTextView
import com.example.krcm110.myapplication.com.Utils.LogUtil
import com.example.krcm110.myapplication.com.view.SuperActivity

class ActivityMotionEvent: SuperActivity()
{
    private var btn0:Button? = null;
    private var btn1:Button? = null;
    private var linearLayout:MyViewGroup? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linearLayout = MyViewGroup(this);
        var linearLayoutParams:ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        linearLayout?.orientation = LinearLayout.VERTICAL;

        var buttonlayout:ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        btn0 = Button(this);
        btn0?.setText("Button1");

        btn1 = Button(this);
        btn1?.setText("Button2");



       // linearLayout?.addView(btn0,buttonlayout);
       // linearLayout?.addView(btn1,buttonlayout);

        var textView:MyTextView = MyTextView(this);
        textView.hint="123123";
        linearLayout?.addView(textView,buttonlayout);

        setContentView(linearLayout,linearLayoutParams);
        setOnclickHandler();
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean
    {
        when(ev?.action)
        {
            MotionEvent.ACTION_DOWN->
            {
                LogUtil.d("Activity....dispatchTouchEvent","ACTION_DOWN");
            }

            MotionEvent.ACTION_UP->{
                LogUtil.d("Activity....dispatchTouchEvent","ACTION_UP");
            }

            MotionEvent.ACTION_CANCEL->
            {
                LogUtil.d("Activity....dispatchTouchEvent","ACTION_CANCEL");
            }

            MotionEvent.ACTION_MOVE->{
                LogUtil.d("Activity....dispatchTouchEvent","ACTION_MOVE");
            }
        }

        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN->
            {
                LogUtil.d("Activity....onTouchEvent","ACTION_DOWN");
            }

            MotionEvent.ACTION_UP->{
                LogUtil.d("Activity....onTouchEvent","ACTION_UP");
            }

            MotionEvent.ACTION_CANCEL->
            {
                LogUtil.d("Activity....onTouchEvent","ACTION_CANCEL");
            }

            MotionEvent.ACTION_MOVE->{
                LogUtil.d("krcm110....onTouchEvent","ACTION_MOVE");
            }
        }

        return super.onTouchEvent(event)
    }


    private fun setOnclickHandler()
    {
        btn0?.setOnTouchListener(object :View.OnTouchListener
        {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean
            {
                LogUtil.d(TAG, "btn0.setOnTouchListener"+"动作是"+event?.action);
                return false;
            }
        })

        btn1?.setOnTouchListener(object :View.OnTouchListener
        {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                LogUtil.d(TAG, "btn1.setOnTouchListener"+"动作是"+event?.action);
                return true;
            }
        })



        btn0?.setOnClickListener()
        {
            LogUtil.d(TAG, "点击了btn0");
        }

        btn1?.setOnClickListener()
        {
            LogUtil.d(TAG, "点击了btn1");
        }
        linearLayout?.setOnClickListener()
        {
            LogUtil.d(TAG, "点击了ViewGroup");
        }

    }
}