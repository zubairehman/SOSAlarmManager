package com.cubivue.app.ui.login

import android.os.Bundle
import android.util.Log
import com.cubivue.app.R
import com.cubivue.app.models.xml.JobXMLMapper
import com.cubivue.base.baseUi.BaseActivity
import com.cubivue.base.models.job.xml.BaseJob
import com.cubivue.base.util.parser.*

class LoginActivity : BaseActivity() {

    val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Parser for SS App Job
        val baseJob: BaseJob = XMLParser()
                .apply {
                    jobXml = JobXMLStructure()
                    jobXml.job_Attrs = arrayListOf(ATTR_JOB_ID, ATTR_JOB_NAME, ATTR_DATE) //Attributes for Job TAG
                    jobXml.info_info_unit_Attrs = arrayListOf(INFO_UNIT_SUBSCRIPTION_SUMMARY) //Attributes for Info TAG in summary
                    jobXml.main_task_list_Attrs1 = arrayListOf(ATTR_TASK_LIST_ID, ATTR_ORDER_NO) //Attributes for 1st TaskList TAG
                    jobXml.task_item_Attrs = arrayListOf(ATTR_TASK_ID, ATTR_ORDER_LINE_NO, ATTR_SORT_ORDER) //Attributes for Task TAG
                }
                .parse(XML_TYPE_JOB, "test.xml", this)

        Log.i(TAG, "Job: ${baseJob.jobAttributes} ${baseJob.taskList?.size}\n")

        baseJob.taskList?.forEach {
            it.tasks?.forEach {
                Log.i(TAG, "Task: ${it.attributes}\n")
            }
        }

        val job = JobXMLMapper().getMappedJob(baseJob)

        Log.i(TAG, "Job: ${job.jobName} ${job.taskList?.size}\n")

        job.taskList?.forEach {
            Log.i(TAG, "Task: ${it.id}\n")
        }
    }

}
