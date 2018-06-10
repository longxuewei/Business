package cn.lxw.business.user.ui.activity

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.lxw.business.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 ***************************************************
 * 猿代码：Lxw
 * Email： China2021@126.com
 * 时间轴：2018年06月08日 17:38
 ***************************************************
 * 备注：
 * 功能描述：
 */
class RegisterActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btCommit.setOnClickListener{
        toast("haha ")

        }
    }
}