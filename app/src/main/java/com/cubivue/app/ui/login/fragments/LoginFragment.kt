package com.cubivue.app.ui.login.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.app.R
import com.cubivue.base.modules.baseclasses.fragment.BaseFragment
import java.util.*

class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun getExtras(extras: ArrayList<Any>) {

    }
}
