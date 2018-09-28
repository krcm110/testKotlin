package com.example.krcm110.myapplication.com.Utils

import android.content.Context
import android.os.Environment


class PatchTools {
    companion object {
        private fun getSystemFilePath(context: Context): String {
            val cachePath: String
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
                cachePath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()
                //            cachePath = context.getExternalCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
            } else {
                cachePath = context.getFilesDir().getAbsolutePath()
                //            cachePath = context.getCacheDir().getPath();//也可以这么写，只是返回的路径不一样，具体打log看
            }
            return cachePath
        }
    }
}