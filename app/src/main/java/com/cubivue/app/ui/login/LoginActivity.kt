package com.cubivue.app.ui.login

import android.os.Bundle
import com.cubivue.app.R
import com.cubivue.base.baseUi.BaseActivity
import com.cubivue.base.util.parser.XMLParser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val job = XMLParser().parseJobXML("test.xml", this)
        tvHelloWorld.text = job?.job_id + " " + job?.date
    }
}