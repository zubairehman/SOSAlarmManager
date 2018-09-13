package com.cubivue.app.ui.splash.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cubivue.app.R
import com.cubivue.app.ui.login.LoginActivity
import com.cubivue.base.ui.splash.fragments.BaseSplashFragment

class SplashFragment : BaseSplashFragment() {

    private var splashTime: Long = 3000

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_client_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //take user to login screen
        Handler().postDelayed({
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }, splashTime)
    }

}
