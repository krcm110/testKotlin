package com.example.krcm110.myapplication.app.view.custome.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.example.krcm110.myapplication.com.view.custom.BtnImageSwitch

class ButtonSwitchChild(context:Context,attrs: AttributeSet): BtnImageSwitch(context,attrs)
{
    init {
        setOnClickListener {
            Toast.makeText(context,id,Toast.LENGTH_SHORT).show();
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN->{    Log.d("ButSTouchdispatchEvent","ACTION_DOWN");}
            MotionEvent.ACTION_MOVE->{    Log.d("ButSTouchdispatchEvent","ACTION_MOVE");}
            MotionEvent.ACTION_UP->{    Log.d("ButSTouchdispatchEvent","ACTION_UP");}
            MotionEvent.ACTION_CANCEL->{    Log.d("ButSTouchdispatchEvent","ACTION_CANCEL");}
        }
        return super.dispatchTouchEvent(event)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean
    {
        when(event?.action)
        {
            MotionEvent.ACTION_DOWN->{
                Log.d("ButtonSTouchEvent","ACTION_DOWN");
            }
            MotionEvent.ACTION_MOVE->{    Log.d("ButtonSTouchEvent","ACTION_MOVE");}
            MotionEvent.ACTION_UP->{
                Log.d("ButtonSTouchEvent","ACTION_UP"); }
            MotionEvent.ACTION_CANCEL->{    Log.d("ButtonSTouchEvent","ACTION_CANCEL");}
        }
        return super.onTouchEvent(event)
    }
}