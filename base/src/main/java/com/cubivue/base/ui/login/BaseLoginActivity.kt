package com.cubivue.base.ui.login

import android.support.v4.app.Fragment
import com.cubivue.base.ui.baseclasses.activity.BaseActivity
import java.util.*


abstract class BaseLoginActivity : BaseActivity() {
    override fun getBaseFragment(): Fragment {
        return getLoginFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }

    //Abstract Methods:-----------------------------------------------------------------------------
    abstract fun getLoginFragment(): Fragment
}