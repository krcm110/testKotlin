package com.example.krcm110.myapplication.app.ui.activity

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import com.example.krcm110.myapplication.com.view.SuperActivity

class OnlyJavaLayoutActivity: SuperActivity()
{
    private var clicked:Boolean  = false;
    private var myButton:Button?=null;
    private var myEditText:EditText?=null;

    override protected fun  onCreate(savedInstanceState: Bundle?)
    {
    super.onCreate(savedInstanceState);

    //定义两个控件，button和edittext
    myButton = Button(this);
    myButton?.setText("Press me");

    myEditText =  EditText(this);
    myEditText?.setHint("See me");

    //为空间设置ID
    myButton?.setId(1.toInt());
    myEditText?.setId(2.toInt());

    //定义好父容器并设置相关属性
   var myLayout: RelativeLayout = RelativeLayout(this);
    myLayout.setBackgroundColor(Color.BLUE);
    //设置Button的布局参数
       var buttonParams:RelativeLayout.LayoutParams  =
     RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
    RelativeLayout.LayoutParams.WRAP_CONTENT);

    buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

    //设置EditText的布局参数
       var textParams:RelativeLayout.LayoutParams  =
     RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
    RelativeLayout.LayoutParams.WRAP_CONTENT);

        var dd:Int? = myButton?.id;
    textParams.addRule(RelativeLayout.ABOVE, dd!!);
    textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
    textParams.setMargins(0, 0, 0, 80);//这里的80是px

    //设置EditText的宽度为指定大小宽度,要相应的dp转化为px
    var r: Resources = getResources();
    var px:Int  =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200.toFloat(), r.getDisplayMetrics()).toInt();

    myEditText?.setWidth(px);
    //将布局添加到父容器中
    myLayout.addView(myButton, buttonParams);
    myLayout.addView(myEditText, textParams);

    //设置显示父容器
    setContentView(myLayout);

    //为Button设置点击事件，点击使其变宽
    val  wrapWidth = myButton?.getLayoutParams()?.width;
    myButton?.setOnClickListener(){
            //点击Button之后使其宽度变宽，再次点击恢复之前的wrap_content状态，可以配合属性动画使其变化更加自然
            if (!clicked) {
                myButton?.getLayoutParams()?.width = 800;
            } else {
                myButton?.getLayoutParams()?.width = wrapWidth;
            }
            clicked = !clicked;
            myButton?.requestLayout();
        }
}
}