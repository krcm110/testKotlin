package com.example.krcm110.myapplication.app.ui.activity

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.view.Gravity
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import com.example.krcm110.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView
import com.example.krcm110.myapplication.app.checkDeviceHasNavigationBar
import com.example.krcm110.myapplication.app.dip2px
import com.example.krcm110.myapplication.app.mvp.model.ben.TabEntity
import com.example.krcm110.myapplication.app.notifacation.NotificationHelper
import com.example.krcm110.myapplication.app.service.ServiceForeground
import com.example.krcm110.myapplication.com.view.mvp.BaseActivity
import com.example.krcm110.myapplication.app.showToast
import com.example.krcm110.myapplication.app.ui.fragment.DiscoveryFragment
import com.example.krcm110.myapplication.app.ui.fragment.HomeFragment
import com.example.krcm110.myapplication.app.ui.fragment.HotFragment
import com.example.krcm110.myapplication.app.ui.fragment.MineFragment
import com.example.krcm110.myapplication.com.Utils.LogUtil
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import kotlinx.android.synthetic.main.custom_title.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.collections.ArrayList

class MainActivity : BaseActivity() {


    private val mTitles = arrayOf("闯关", "发现","", "佳作", "诵读")
    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.btn_zjm_cg_bottom_nor, R.mipmap.btn_zjm_fx_bottom_nor,R.mipmap.dot,R.mipmap.btn_zjm_jz_bottom_nor, R.mipmap.btn_zjm_sd_bottom_nor)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.btn_zjm_cg_bottom_pre, R.mipmap.btn_zjm_fx_bottom_pre,R.mipmap.dot, R.mipmap.btn_zjm_jz_bottom_pre, R.mipmap.btn_zjm_sd_bottom_pre)

    //推出程序第一次的按键时间
    private var mExitTime: Long = 0

    /**
     * 闯关Fragment
     */
    private var mHomeFragment: HomeFragment? = null

    /**
     * 发现Fragment
     */
    private var mDiscoveryFragment: DiscoveryFragment? = null

    /**
     * 佳作Fragment
     */
    private var mHotFragment: HotFragment? = null

    /**
     * 诵读Fragment
     */
    private var mMineFragment: MineFragment? = null
    /**
     * 记录当前选择的Fragment的Index
     */
    private var mIndex:Int = 0;

    /**
     * 底部导航栏的数据格式
     * 1.Title
     * 2.未被选中的图片
     * 3.选中后的图片
     */
    private val mTabEntities = java.util.ArrayList<CustomTabEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //判断是否有虚拟按键，如果要增加边距
        if(checkDeviceHasNavigationBar(this))
        {
            lllayout.setPadding(0,0,0, dip2px(this,50f));
        }
        initTab();
        addEvent();
        switchFragment(mIndex)

        btn_BackActivity.setOnTouchListener(object:View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when(event?.action)
                {
                    MotionEvent.ACTION_DOWN->{
                        LogUtil.d("你好啊","MotionEvent.ACTION_DOWN");
                    }

                    MotionEvent.ACTION_MOVE->{
                        LogUtil.d("你好啊","MotionEvent.ACTION_MOVE");
                    }

                    MotionEvent.ACTION_UP->{
                        LogUtil.d("你好啊","MotionEvent.ACTION_UP");
                    }
                }
                return true;
        }})
    }

    override fun layoutId(): Int {
        return R.layout.activity_main;
    }

    override fun initData() {

    }

    override fun start() {
    }

    override fun initView()
    {
        initDrawerMenu();
        //改变侧边栏下面部分的界面上蒙的一层阴影
        drawerLayout.setScrimColor(Color.TRANSPARENT)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    private var stmNM: NotificationManager? = null

    /**
     * 初始化菜单
     */
    private fun initDrawerMenu()
    {
        stmNM = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val menu  = ArrayList<String>();
        menu.add("RetorfitActivity")
        menu.add("SecondActivity")
        menu.add("ServiceActivity")
        menu.add("notificationActivity")
        menu.add("notificationService")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,menu);
        main_menu.adapter = adapter;
        var intent:Intent?=null
       main_menu.setOnItemClickListener(AdapterView.OnItemClickListener {parent, view, position, id ->

            when(position)
            {
                0->{
                    intent = Intent(this,RetrofitActivity::class.java);
                    startActivity(intent)
                }
                1->{
                    intent = Intent(this,ActitityB::class.java);
                    startActivity(intent)
                }
                2->{
                    intent = Intent(this,ActivityService::class.java);
                    startActivity(intent)
                }
                3->
                {
                    NotificationHelper.instance.sendNotifacrion(this,stmNM!!)
                    //Notificaitons.getInstance().sendSimpleNotification(applicationContext,stmNM);
                }
                4->
                {
                    intent = Intent(this, ServiceForeground::class.java);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
                    {
                        startService(intent);
                    }
                    else
                    {
                        startService(intent)
                    }
                }
                5->{
                    intent = Intent(this, OnlyJavaLayoutActivity::class.java);
                    startActivity(intent);
                }
                6->
                {
                    intent = Intent(this,ActivityHandle::class.java)
                    startActivity(intent);
                }
                7->
                {
                    var message:Message = Message();
                    message.what=1;
                    HanlderHeard.handler?.sendMessage(message);
                }
                8->
                {
                    intent = Intent("krcm110.intent.action.aaaaaaa")
                    sendBroadcast(intent);
                   // intent = Intent(this,ActivityMotionEvent::class.java)
                    //startActivity(intent);
                }
            }
        })
        openDrawerMenu();
        drawerLayout.openDrawer(Gravity.LEFT);//侧滑打开  不设置则不会默认打开
    }




    /**
     * 打开/关闭抽屉菜单
     */
    fun openDrawerMenu()
    {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            drawerLayout.closeDrawers();
        }
        else
        {
            drawerLayout.openDrawer(Gravity.LEFT);
        }
    }


    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        //获取Fragment的管理类
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 // 闯关
            -> mHomeFragment?.let {
                transaction.show(it)
            } ?: HomeFragment.getInstance(mTitles[position]).let {
                mHomeFragment = it
                transaction.add(R.id.fl_container, it, "home")
            }
            1  //发现
            -> mDiscoveryFragment?.let {
                transaction.show(it)
            } ?: DiscoveryFragment.getInstance(mTitles[position]).let {
                mDiscoveryFragment = it
                transaction.add(R.id.fl_container, it, "discovery") }
            3  //佳作
            -> mHotFragment?.let {
                transaction.show(it)
            } ?: HotFragment.getInstance(mTitles[position]).let {
                mHotFragment = it
                transaction.add(R.id.fl_container, it, "hot") }
            4//诵读
            -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine") }
            else -> {
            }
        }
        mIndex = position
        tab_layout.currentTab = mIndex
        ///提交并且刷新UI
       // transaction.commitAllowingStateLoss()
        transaction.commit();
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomeFragment?.let { transaction.hide(it) }
        mDiscoveryFragment?.let { transaction.hide(it) }
        mHotFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }



    //初始化底部菜单
    private fun initTab() {
        //把
        (0 until mTitles.size)
                .mapTo(mTabEntities) {
                    TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it])
                }

        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        //屏蔽底部Table导航的第二个按钮点击事件
        tab_layout.setSpecialTab(2)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //底部导航按钮的s事件响应
                switchFragment(position);
            }
            override fun onTabReselect(position: Int) {
            }
        })
    }

    /**
     *
     */
    private fun showAnimation()
    {
        showToast("你好啊");
    }

    /**
     *中心的加号按钮
     */
    private fun addEvent()
    {
        btn_table_center.onClick{
            showAnimation();
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    /**
     * 图吃程序
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
