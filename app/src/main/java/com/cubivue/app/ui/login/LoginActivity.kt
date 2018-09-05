package com.cubivue.app.ui.login

import android.os.Bundle
import android.util.Log
import com.cubivue.app.R
import com.cubivue.app.models.xml.*
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
                    xml = getJobXMLStructureForBKApp()
                }
                .parse(XML_TYPE_JOB, "test2.xml", this)

        val job = JobXMLMapper().getMappedJob(baseJob)

        Log.i(TAG, "Job: ${job.jobName} ${job.taskList?.size}\n")

        job.taskList?.forEach {
            Log.i(TAG, "Task: ${it.id}\n")
        }

        job.productsList?.forEach {
            Log.i(TAG, "Products: ${it.product} ${it.quantity}\n")
        }

        job.addressedProductsList?.forEach {
            Log.i(TAG, "Addressed: ${it.product} ${it.quantity}\n")
        }

        job.labelsList?.forEach {
            Log.i(TAG, "Label: ${it.label}\n")
        }

        job.keysList?.forEach {
            Log.i(TAG, "Key: ${it.key}\n")
        }
    }

    private fun getJobXMLStructureForSSApp(): JobXMLStructure {
        val xml = JobXMLStructure()
        xml.job_Attrs = arrayListOf(ATTR_JOB_ID, ATTR_JOB_NAME, ATTR_DATE) //Attributes for Job TAG
        xml.info_info_unit_Attrs_Types = arrayListOf(INFO_UNIT_SUBSCRIPTION_SUMMARY) //Attributes for Info TAG in summary
        xml.max_summary_info_tags = 1 //Max number of TAGS in Job XML
        xml.main_task_list_Attrs1 = arrayListOf(ATTR_TASK_LIST_ID, ATTR_ORDER_NO) //Attributes for 1st TaskList TAG
        xml.task_item_Attrs = arrayListOf(ATTR_TASK_ID, ATTR_ORDER_LINE_NO, ATTR_SORT_ORDER) //Attributes for Task TAG
        return xml
    }

    private fun getJobXMLStructureForBKApp(): JobXMLStructure {
        val xml = JobXMLStructure()
        xml.job_Attrs = arrayListOf(ATTR_JOB_ID, ATTR_JOB_NAME, ATTR_DATE) //Attributes for Job TAG
        xml.info_info_unit_Attrs_Types = arrayListOf(
                INFO_UNIT_SUBSCRIPTION_SUMMARY,
                INFO_UNIT_PRODUCT_SUMMARY,
                INFO_UNIT_LABEL_SUMMARY,
                INFO_UNIT_KEY_SUMMARY) //Attributes for Info TAG in summary
        xml.max_summary_info_tags = 4 //Max number of TAGS in Job XML
        xml.main_task_list_Attrs1 = arrayListOf(ATTR_TASK_LIST_ID, ATTR_ORDER_NO) //Attributes for 1st TaskList TAG
        xml.task_item_Attrs = arrayListOf(ATTR_TASK_ID, ATTR_ORDER_LINE_NO, ATTR_SORT_ORDER) //Attributes for Task TAG
        return xml
    }
}
