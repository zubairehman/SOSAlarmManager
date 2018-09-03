package com.cubivue.base.util.parser

import com.cubivue.base.models.job.xml.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

internal fun readJobXML(jobXml: JobXMLStructure): DefaultHandler {

    return object : DefaultHandler() {

        //Job
        var job: Job? = null
        var jobAttrs: ArrayList<String>? = null

        //Info
        var info: Info? = null
        var jobInfoSummary: ArrayList<String>? = null

        //Main
        val parentTaskList = arrayListOf<TaskList>()
        val infoUnit = arrayListOf<InfoUnit>()

        //Temp Variables
        var singleTaskListCollection: ArrayList<TaskList>? = null

        //SAX Parser Variables
        var currentValue = ""
        var currentElement = false
        var currentInfo = ""

        // This method is invoked when start parse xml element node use sax parser.
        @Throws(SAXException::class)
        override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {

            currentElement = true
            currentValue = ""
            currentInfo = ""

            when (localName) {
                TAG_JOB -> {
                    job = Job()

                    //Get required Attributes for Job TAG
                    jobXml.job_Attrs?.let {
                        jobAttrs = readAttributes(attributes, it)
                    }
                }
                TAG_INFO_UNIT -> {

                    if (attributes.getValue(ATTR_TYPE) == INFO_UNIT_SUBSCRIPTION_SUMMARY) {

                        currentInfo = INFO_UNIT_SUBSCRIPTION_SUMMARY

                        //Get required Attributes for Info TAG
                        jobXml.info_info_unit_Attrs?.let {
                            jobInfoSummary = arrayListOf()
                        }
                    }
                }
                TAG_INFO -> {
                    info = Info(infoUnit)
                }
                TAG_TASK_LIST -> {

                    if (singleTaskListCollection == null) {
                        singleTaskListCollection = arrayListOf()
                    }

                    var taskListAttrs = arrayListOf<String>()

                    //Get required Attributes for TaskList1 TAG
                    jobXml.main_task_list_Attrs1?.let {
                        taskListAttrs = readAttributes(attributes, it)
                    }

                    val singleTaskList = TaskList(taskListAttrs)
                    singleTaskListCollection?.add(singleTaskList)
                }
                TAG_TASK -> {

                    var taskAttrs = arrayListOf<String>()

                    //Get required Attributes for Task TAG
                    jobXml.task_item_Attrs?.let {
                        taskAttrs = readAttributes(attributes, it)
                    }

                    val taskItem = Task(taskAttrs)

                    if (singleTaskListCollection?.isNotEmpty()!!) {
                        if (singleTaskListCollection?.last()?.tasks == null) {
                            singleTaskListCollection?.last()?.tasks = arrayListOf()
                        }
                    }

                    taskItem.let {
                        singleTaskListCollection?.last()?.tasks?.add(it)
                    }
                }
            }
        }

        // When sax parse xml element node finished, invoke this method.
        @Throws(SAXException::class)
        override fun endElement(uri: String, localName: String, qName: String) {
            currentElement = false

            when (localName) {
                TAG_JOB -> {
                    job?.infoSummary = jobInfoSummary
                    job?.taskList = parentTaskList
                    job?.jobAttributes = jobAttrs
                }
                TAG_INFO_UNIT -> {

                    if (currentInfo == INFO_UNIT_SUBSCRIPTION_SUMMARY) {
                        jobInfoSummary?.add(currentValue)
                    }
                }
                TAG_INFO -> {
                    info?.info_unit = infoUnit
                }
                TAG_TASK_LIST -> {
                    if (singleTaskListCollection?.isNotEmpty()!!) {
                        parentTaskList.add(singleTaskListCollection?.last()!!)
                        singleTaskListCollection?.clear()
                    }
                }
                TAG_TASK -> {

                }
            }
        }

        // This method is invoked when sax parser parse xml node text.
        @Throws(SAXException::class)
        override fun characters(ch: CharArray, start: Int, length: Int) {
            if (currentElement) {
                currentValue = String(ch, start, length)
                currentElement = false
            }
        }

    }
}