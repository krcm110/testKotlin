package com.example.krcm110.myapplication.app.view.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.view.Gravity
import android.widget.ArrayAdapter
import com.example.krcm110.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView
import com.example.krcm110.myapplication.app.notifacation.NotificationHelper
import com.example.krcm110.myapplication.app.service.ServiceForeground
import com.example.krcm110.myapplication.com.broadCast.AndroidStartComplete
import com.example.krcm110.myapplication.com.view.SuperActivity
import kotlin.collections.ArrayList

class MainActivity : SuperActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView()
    {
        initDrawerMenu();
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        val path = applicationContext.packageResourcePath
        textPatch.setText(path);
        Log.e(TAG,"krcm110");
    }

    private var stmNM: NotificationManager? = null

    private fun initDrawerMenu()
    {
        stmNM = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val menu  = ArrayList<String>();
        menu.add("RetorfitActivity")
        menu.add("SecondActivity")
        menu.add("ServiceActivity")
        menu.add("notificationActivity")
        menu.add("notificationService")
        menu.add("retrofitActivity");
        menu.add("activityHandler")
        menu.add("testHandler");
        menu.add("motionEvent");

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,menu);
        main_menu.adapter = adapter;
        var intent:Intent?=null
        main_menu.setOnItemClickListener(AdapterView.OnItemClickListener {parent, view, position, id ->

            when(position)
            {
                0->{
                    intent = Intent(this,RetrofitActivity::class.java);
                    startActivity(intent)
                }
                1->{
                    intent = Intent(this,ActitityB::class.java);
                    startActivity(intent)
                    mainText.setText(menu[position]);
                }
                2->{
                    intent = Intent(this,ActivityService::class.java);
                    startActivity(intent)
                }
                3->
                {
                    NotificationHelper.instance.sendNotifacrion(this,stmNM!!)
                    //Notificaitons.getInstance().sendSimpleNotification(applicationContext,stmNM);
                }
                4->
                {
                    intent = Intent(this, ServiceForeground::class.java);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                    {
                        startService(intent);
                    }
                    else
                    {
                        startService(intent)
                    }
                }
                5->{
                    intent = Intent(this, OnlyJavaLayoutActivity::class.java);
                    startActivity(intent);
                }
                6->
                {
                    intent = Intent(this,ActivityHandle::class.java)
                    startActivity(intent);
                }
                7->
                {
                    var message:Message = Message();
                    message.what=1;
                    HanlderHeard.handler?.sendMessage(message);
                }
                8->
                {
                    intent = Intent("krcm110.intent.action.aaaaaaa")
                    sendBroadcast(intent);
                   // intent = Intent(this,ActivityMotionEvent::class.java)
                    //startActivity(intent);
                }
            }
        })
        open();
        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }


    fun open()
    {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            drawerLayout.closeDrawers();
        }
        else
        {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

}
