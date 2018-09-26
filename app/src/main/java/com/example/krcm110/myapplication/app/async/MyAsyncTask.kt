package com.example.krcm110.myapplication.app.async

import android.os.AsyncTask

class MyAsyncTask:AsyncTask<Void,Int,Boolean>()
{
    /**
     * 开始前的准备
     */
    override fun onPreExecute() {
        super.onPreExecute()
    }

    /**
     * 子线程
     * 正式开始
     */
    override fun doInBackground(vararg params: Void?): Boolean
    {
        //把任务信息传递给onProgressUpdate
        publishProgress(100);
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     *  更新的进度
     */
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    /**
     * 返回的结果
     */
    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }

}