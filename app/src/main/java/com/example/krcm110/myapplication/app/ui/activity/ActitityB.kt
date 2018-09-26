package com.example.krcm110.myapplication.app.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.view.SuperActivity
import kotlinx.android.synthetic.main.activity_b.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ActitityB:SuperActivity()
{
    private var alertDialogs:AlertDialog?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        isShowKeyboard = true;
        onEvent();
    }

    fun createAlertDialog()
    {
        var alertDialog:AlertDialog.Builder = AlertDialog.Builder(applicationContext);
        alertDialog.setTitle("I'm AlertDialog")
        alertDialog.setMessage("How do you do?")
        alertDialog.setCancelable(false);//不准通过点击返回键关闭
        alertDialog.setPositiveButton("ok"){dialog,which->
            Toast.makeText(this,"come on", Toast.LENGTH_LONG).show()
        }
        alertDialogs = alertDialog.create();
    }

    fun onEvent()
    {
        btnB.onClick {  }
        btnB.setOnClickListener()
        {
            var intent: Intent = Intent(this,ActitityC::class.java)
            //startActivity(intent)
            startActivityForResult(intent,1)
        }
        btnShowSystemAlertDialog.setOnClickListener()
        {
            createAlertDialog();

            if(Build.VERSION.SDK_INT>=23)
            {
                if(Settings.canDrawOverlays(this))
                {
                    alertDialogs?.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                    alertDialogs?.show();
                }
                else
                {
                    var intent:Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    startActivityForResult(intent,100);
                }
            }
            else
            {
                alertDialogs?.window?.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT)
                alertDialogs?.show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode)
        {
            100->
            {
                if(Build.VERSION.SDK_INT>=23)
                {
                    if(Settings.canDrawOverlays(this))
                    {
                        alertDialogs?.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY)
                        alertDialogs?.show();
                    }
                    else
                    {
                        Toast.makeText(this,"你拒绝了权限",Toast.LENGTH_SHORT);
                    }
                }
            }
            1 ->
            {
                if (resultCode== Activity.RESULT_OK)
                {
                    var returnedData:String = data!!.getStringExtra("data_result");
                    Log.d("krcm110",returnedData);
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("dd","dd");
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialogs?.let {alertDialogs?.dismiss()}
    }
}