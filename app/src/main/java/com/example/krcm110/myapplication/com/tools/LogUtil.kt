package com.example.krcm110.myapplication.com.tools

import android.util.Log

class LogUtil
{
    companion object {
        private var VERBOSE:Int = 1;
        private var DEBUG:Int = 2;
        private var INFO = 3;
        private var WARN = 4;
        private var ERROR = 5;
        private var NOTHING = 6;
        private var LEVEL = VERBOSE;

        fun v(tag:String,msg:String)
        {
            if (LEVEL<= VERBOSE)
            {
                Log.v(tag,msg);
            }
        }

        fun d(tag:String,msg:String)
        {
            if (LEVEL<= VERBOSE)
            {
                Log.d(tag,msg);
            }
        }

        fun i(tag:String,msg:String)
        {
            if (LEVEL<= VERBOSE)
            {
                Log.i(tag,msg);
            }
        }

        fun w(tag:String,msg:String)
        {
            if (LEVEL<= VERBOSE)
            {
                Log.w(tag,msg);
            }
        }
    }
}