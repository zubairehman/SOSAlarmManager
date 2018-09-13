package com.cubivue.app.ui.login

import android.support.v4.app.Fragment
import com.cubivue.app.ui.login.fragments.LoginFragment
import com.cubivue.base.ui.baseclasses.activity.BaseActivity
import java.util.*


class LoginActivity : BaseActivity() {
    override fun getBaseFragment(): Fragment {
        return LoginFragment()
    }

    override fun getExtras(extras: ArrayList<*>) {
    }

}