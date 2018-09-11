package com.cubivue.base.modules.login

import android.support.v4.app.Fragment
import com.cubivue.base.modules.baseclasses.activity.BaseActivity
import java.util.*


abstract class BaseLoginActivity : BaseActivity() {
    override fun getBaseFragment(): Fragment {
        return getLoginFragment()
    }

    override fun getExtras(extras: ArrayList<Any>?) {
    }

    //Abstract Methods:-----------------------------------------------------------------------------
    abstract fun getLoginFragment(): Fragment
}