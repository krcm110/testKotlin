package com.example.krcm110.myapplication.com.data

import android.app.Activity

class ActivityCollection
{
    private var activitys:ArrayList<Activity>? = ArrayList();
    fun addAcvicity(activity: Activity)
    {
        activitys?.add(activity);
    }

    fun removeActivity(activity: Activity)
    {
        activitys?.remove(activity);
    }

    fun removeAllActivity()
    {
        var iterator = activitys?.iterator();

        while (iterator!!.hasNext())
            {
                var activity:Activity =  iterator!!.next();
                if(!activity.isFinishing)
                {
                    activity .finish()
                }
            }
    }
}