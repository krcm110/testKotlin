package com.example.krcm110.myapplication.app.view.activity

import android.content.Intent
import android.os.Bundle
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.app.broadcast.AppBoradCast
import com.example.krcm110.myapplication.com.view.ProApplication
import com.example.krcm110.myapplication.com.view.SuperActivity
import kotlinx.android.synthetic.main.activity_b.*
import kotlinx.android.synthetic.main.activity_d.*

class ActitityD: SuperActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)
        onEvent()
    }

    fun onEvent()
    {
        btnd.setOnClickListener()
        {
            var intent:Intent = Intent(this,ActitityB::class.java)
            startActivity(intent)
        }
        btn_quit.setOnClickListener()
        {
            (application as ProApplication).removeAllActivity();
            System.exit(0);
        }
    }
}