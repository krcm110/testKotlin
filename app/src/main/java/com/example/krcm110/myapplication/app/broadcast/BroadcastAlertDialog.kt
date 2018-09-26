package com.example.krcm110.myapplication.app.broadcast

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.WindowManager
import android.widget.Toast

class BroadcastAlertDialog: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?)
    {
        var sysytemAlert:AlertDialog.Builder = AlertDialog.Builder(context);
        sysytemAlert.setTitle("System AlertDialog");
        sysytemAlert.setMessage("this is system alertDialog,Close me please \"ok\" btn");
        sysytemAlert.setCancelable(false);

        sysytemAlert.setPositiveButton("OK"){dialog, which ->  Toast.makeText(context,"come on",Toast.LENGTH_LONG).show() }

        var alertDialog:AlertDialog = sysytemAlert.create();
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(context)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                context?.startActivity(intent)
                return
            } else {
                //绘ui代码, 这里说明6.0系统已经有权限了
                alertDialog.window.setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
            }
        } else {
            //绘ui代码,这里android6.0以下的系统直接绘出即可
            alertDialog.window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        alertDialog.show();
    }
}