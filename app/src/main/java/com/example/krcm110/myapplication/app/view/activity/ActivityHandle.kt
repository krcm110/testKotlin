package com.example.krcm110.myapplication.app.view.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.krcm110.myapplication.com.view.SuperActivity
import java.lang.reflect.Parameter

class ActivityHandle: SuperActivity()
{
    private var btn:Button?=null;
    private var linearLayout:LinearLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

     var linearLayout:LinearLayout = LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        var linearLayoutParams:ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);

        var btn:Button = Button(this);
        btn.setText("你好啊");
        var btnParameter = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        var btn2:Button = Button(this);
        btn2.setText("不错哦");


        var handler:HanlderHeard = HanlderHeard();
        handler.start();

        btn.setOnClickListener()
        {
            var message:Message = Message();
            message.what = 1;
            HanlderHeard.handler?.sendMessage(message);
        };

        linearLayout.addView(btn2,btnParameter);
        linearLayout.addView(btn,btnParameter);
        setContentView(linearLayout,linearLayoutParams);
    }

    private fun initView() {
    // 获取xml的RelativeLayout
        var layout:LinearLayout  = LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);

    for (i  in 1..5)
        {
            //布局是LinerLayout的话这里一般就是使用LinearLayout.LayoutParams什么布局使用什么
           var llParams:LinearLayout.LayoutParams  = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            // 每行都有一个linearlayout
            var lLayout:LinearLayout  = LinearLayout(this);
            lLayout.setId(i + 10);
            lLayout.setOrientation(LinearLayout.HORIZONTAL);
            var lLayoutlayoutParams:LinearLayout.LayoutParams  = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            lLayout.setLayoutParams(lLayoutlayoutParams);

           var tv: TextView = TextView(this);
            tv.setId(i);
            tv.setText("这是第" + i + "个文本框");
            tv.setTextColor(Color.BLUE);

            var tv_num:TextView  = TextView(this);
            tv_num.setId(i + 20);
            tv_num.setText(i.toString() + "台");
            tv_num.setTextColor(Color.RED);
            // 为TextView添加长高设置
           var layoutParams_txt:ViewGroup.LayoutParams  = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(layoutParams_txt);
            tv_num.setLayoutParams(layoutParams_txt);

            // 添加到每行的linearlayout中
            lLayout.addView(tv);
            lLayout.addView(tv_num);

            // 每个linearlayout都在前一个的下面，第一个在顶,不处理
            if (i > 0) {
                // relativeParams.addRule(RelativeLayout.BELOW, i + 10 - 1);
            }

            // 把每个linearlayout加到relativelayout中
            layout.addView(lLayout, llParams);
            setContentView(layout);
        }
    }

    override fun onDestroy()
    {
        //closeHandler();
        super.onDestroy()
    }

    fun closeHandler()
    {
        HanlderHeard.handler?.looper?.quit();
         HanlderHeard.handler?.removeCallbacksAndMessages(null);
         HanlderHeard.handler = null;
    }
}

    class HanlderHeard:Thread()
    {
        companion object
        {
            var handler: Handler?=null;
        }

        override fun run()
        {
            super.run();
            Looper.prepare();
            handler =  object: Handler()
            {
                override fun handleMessage(msg: Message?)
                {
                    when(msg?.what)
                    {
                        1->
                        {
                            Log.d("我接受到了一个来至ActivityThreed","HandlerThread");
                        }
                    }
                }
            }
            //必须调用Loop循环接受消息
            //由于Loop是一个死循环所以后面的代码不会执行
            Looper.loop();
        }
    }