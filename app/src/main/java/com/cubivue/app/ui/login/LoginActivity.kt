package com.cubivue.app.ui.login

import android.support.v4.app.Fragment
import com.cubivue.app.ui.login.fragments.LoginFragment
import com.cubivue.base.ui.login.BaseLoginActivity


class LoginActivity : BaseLoginActivity() {

    override fun getLoginFragment(): Fragment {
        return LoginFragment()
    }
}