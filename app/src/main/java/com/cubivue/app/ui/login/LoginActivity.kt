package com.cubivue.app.ui.login

import android.os.Bundle
import com.cubivue.app.R
import com.cubivue.base.baseUi.BaseActivity
import com.cubivue.base.util.parser.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        XMLParser()
                .apply {
                    jobXml = JobXMLStructure()
                    jobXml.job_Attrs = arrayListOf(ATTR_JOB_ID, ATTR_JOB_NAME, ATTR_DATE)
                }
                .parse(XML_TYPE_JOB, "test.xml", this)
    }

}
