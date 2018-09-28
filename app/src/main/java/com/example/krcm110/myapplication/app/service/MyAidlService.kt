package com.example.krcm110.myapplication.app.service

import android.app.Person
import android.os.IBinder
import com.example.krcm110.myapplication.IMyAidl
import com.example.krcm110.myapplication.com.service.ServiceBase
import android.content.Intent
import android.util.SparseArray
import android.view.View
import com.example.krcm110.myapplication.com.Utils.LogUtil


class MyAidlService: ServiceBase()
{
    var mPersons:ArrayList<Person>?= null;
    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */
    private var mIBinder: IBinder = object: IMyAidl.Stub() {
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?)
        {
            TODO();
        }

        override fun addPerson(person: Person?)
        {
            mPersons?.add(person!!);
        }

        override fun getPersonList(): MutableList<Person>
        {
            return mPersons!!;
        }
    }

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */
    override fun onBind(intent: Intent?): IBinder? {

        var arra: SparseArray<View> = SparseArray();



        mPersons = ArrayList()
        LogUtil.d(TAG, "MyAidlService onBind")
        return mIBinder
    }

}
