package com.example.krcm110.myapplication.com.Utils

import android.view.View
import android.view.ViewGroup

class ViewTools
{
    companion object
    {
        fun getAllChildViews(view: View): List<View>
        {
            val allchildren = ArrayList<View>()
            if (view is ViewGroup)
            {
                for (i in 0 until view.childCount)
                {
                    val viewchild = view.getChildAt(i)
                    allchildren.add(viewchild)
                    //再次 调用本身（递归）
                    allchildren.addAll(getAllChildViews(viewchild))
                }
            }
            return allchildren
        }
    }
}