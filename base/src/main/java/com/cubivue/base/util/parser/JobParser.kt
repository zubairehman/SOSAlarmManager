package com.cubivue.base.util.parser

import com.cubivue.base.models.job.xml.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

@Throws(XmlPullParserException::class, IOException::class)
internal fun readJobXML(parser: XmlPullParser, jobXml: JobXMLStructure): Job {

    var eventType = parser.eventType

    var job: Job? = null
    var jobAttrs = arrayListOf<String>()
    var info: Info? = null
    val taskList = arrayListOf<TaskList>()
    val tasks = arrayListOf<Task>()
    val infoUnit = arrayListOf<InfoUnit>()

    var taskListItem: TaskList? = null
    var taskItem: Task? = null

    while (eventType != XmlPullParser.END_DOCUMENT) {

        when (eventType) {
            XmlPullParser.START_DOCUMENT -> {

            }
            XmlPullParser.START_TAG -> {

                when (parser.name) {
                    TAG_JOB -> {
                        jobXml.job_Attrs?.let {
                            jobAttrs = readJobAttributes(parser, it)
                        }
                    }
                    TAG_INFO_UNIT -> {
                        infoUnit.add(readInfoUnit(parser))
                    }
                    TAG_INFO -> {
                        info = Info(infoUnit)
                    }
                    TAG_TASK_LIST -> {
                        taskListItem = readTaskList(parser)

                        taskListItem?.let {
                            taskList.add(it)
                        }
                    }
                    TAG_TASK -> {
                        taskItem = readTask(parser)
                        taskItem?.let {
                            tasks.add(it)
                            taskList.last().tasks = tasks
                        }
                    }
                }
            }
            XmlPullParser.END_TAG -> {

                info?.info_unit = infoUnit

                job = Job()
                job.info = info
                job.taskList = taskList
                job.jobAttributes = jobAttrs
            }
            XmlPullParser.END_DOCUMENT -> {

            }
        }

        eventType = parser.next()
    }

    return job!!
}

@Throws(IOException::class, XmlPullParserException::class)
private fun readJobAttributes(parser: XmlPullParser, listOfAttributes: ArrayList<String>): ArrayList<String> {

    val arrayList = arrayListOf<String>()
    val tag = parser.name

    if (tag == TAG_JOB) {

        for (attr in listOfAttributes) {
            val value = parser.getAttributeValue("", attr)
            if (value != null) {
                arrayList.add(value)
            }
        }

        parser.nextTag()
    }

    return arrayList
}


@Throws(XmlPullParserException::class, IOException::class)
private fun readInfo(parser: XmlPullParser): Info {

    var infoUnit: InfoUnit? = null
    val type: String? = null

    if (parser.name == TAG_INFO) {
        infoUnit = type?.let { InfoUnit(type) }!!
    }

    return infoUnit?.let { Info(listOf(infoUnit)) }!!
}

@Throws(XmlPullParserException::class, IOException::class)
private fun readInfoUnit(parser: XmlPullParser): InfoUnit {

    var type: String? = null

    if (parser.name == TAG_INFO_UNIT) {
        type = readValues(parser)
    }

    return type?.let { InfoUnit(type) }!!
}

@Throws(XmlPullParserException::class, IOException::class)
private fun readTaskList(parser: XmlPullParser): TaskList? {

    val taskList = TaskList()
    var taskListId: String? = null
    var orderNo: String? = null

    if (parser.name == TAG_TASK_LIST) {

        taskListId = parser.getAttributeValue("", ATTR_TASK_LIST_ID)
        orderNo = parser.getAttributeValue("", ATTR_ORDER_NO)

        taskListId?.let {
            taskList.task_list_id = it
        }

        orderNo?.let {
            taskList.order_no = it
        }

    }

    return taskList
}

@Throws(XmlPullParserException::class, IOException::class)
private fun readTask(parser: XmlPullParser): Task? {

    var taskId: String? = null
    var orderLineNo: String? = null
    var sortOrder: String? = null

    if (parser.name == TAG_TASK) {
        taskId = parser.getAttributeValue("", ATTR_TASK_ID)
        orderLineNo = parser.getAttributeValue("", ATTR_ORDER_LINE_NO)
        sortOrder = parser.getAttributeValue("", ATTR_SORT_ORDER)
    }

    return Task(taskId, orderLineNo, sortOrder)
}