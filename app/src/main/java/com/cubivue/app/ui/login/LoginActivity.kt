package com.cubivue.app.ui.login

import android.os.Bundle
import com.cubivue.app.R
import com.cubivue.base.baseUi.BaseActivity
import com.cubivue.base.util.parser.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Parser for SS App Job
        XMLParser()
                .apply {
                    jobXml = JobXMLStructure()
                    jobXml.job_Attrs = arrayListOf(ATTR_JOB_ID, ATTR_JOB_NAME, ATTR_DATE) //Attributes for Job TAG
                    jobXml.info_info_unit_Attrs = arrayListOf(INFO_UNIT_SUBSCRIPTION_SUMMARY) //Attributes for Info TAG in summary
                    jobXml.main_task_list_Attrs1 = arrayListOf(ATTR_TASK_LIST_ID, ATTR_ORDER_NO) //Attributes for 1st TaskList TAG
                    jobXml.task_item_Attrs = arrayListOf(ATTR_TASK_ID, ATTR_ORDER_LINE_NO, ATTR_SORT_ORDER) //Attributes for Task TAG
                }
                .parse(XML_TYPE_JOB, "test.xml", this)
    }

}
