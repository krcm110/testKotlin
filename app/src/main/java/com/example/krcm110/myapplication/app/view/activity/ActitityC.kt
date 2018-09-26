package com.example.krcm110.myapplication.app.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.krcm110.myapplication.R
import com.example.krcm110.myapplication.com.view.SuperActivity
import kotlinx.android.synthetic.main.activity_c.*
import android.content.ComponentName
import com.example.krcm110.myapplication.app.broadcast.BroadcastAlertDialog


class ActitityC: SuperActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)
        onEvent()
    }

    override fun onResume() {
        super.onResume()

    }

    fun onEvent()
    {
        btnConnect.setOnClickListener()
        {
            var intent:Intent = Intent(this,ActitityD::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener()
        {
            var intent:Intent = Intent();
            intent.putExtra("data_return","Hello First");
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
        btnSendBoradcast.setOnClickListener()
        {
            val str:String = "com.example.krcm110.myapplication.app.broadcast.BroadcastAlertDialog";
            var str2:String = BroadcastAlertDialog::class.java.toString();

            var myIntent:Intent = Intent("com.example.krcm110.myborcast");
            myIntent.setComponent(ComponentName(application.packageName,
                    str))
            sendBroadcast(myIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }
}