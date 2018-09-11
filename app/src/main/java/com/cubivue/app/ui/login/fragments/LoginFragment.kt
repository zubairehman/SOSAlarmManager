package com.cubivue.app.ui.login.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.app.R
import com.cubivue.app.ui.home.activities.DashboardActivity
import com.cubivue.base.ui.baseclasses.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*

class LoginFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            val intent = Intent(activity, DashboardActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

    }

    override fun getExtras(extras: ArrayList<*>) {

    }
}
