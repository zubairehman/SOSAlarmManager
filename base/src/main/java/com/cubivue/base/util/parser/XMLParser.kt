package com.cubivue.base.util.parser

import android.content.Context
import com.cubivue.base.models.job.xml.*
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory


@Suppress("UNCHECKED_CAST", "UNREACHABLE_CODE")
class XMLParser {

    //Parameters for Parser
    var xml: JobXMLStructure? = null

    fun <T> parse(xmlType: String, path: String, context: Context): T {

        if (xml != null) {

            when (xmlType) {
                XML_TYPE_JOB -> return parseJobXML(path, context) as T
            }

        }

        return null!!
    }

    private fun parseJobXML(path: String, context: Context): BaseJob? {
        val ins = readAssetsXML(path, context)
        return parse(ins)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parse(inputStream: InputStream?): BaseJob? {

        var job: BaseJob? = null

        try {
            val factory = SAXParserFactory.newInstance()
            val saxParser = factory.newSAXParser()

            val handler = object : DefaultHandler() {

                //Job
                var jobAttrs: ArrayList<String>? = null

                //Info
                var info: BaseInfo? = null
                var jobInfoSummary: ArrayList<Pair<String, String>>? = arrayListOf()

                //Main
                val parentTaskList = arrayListOf<BaseTaskList>()
                val infoUnit = arrayListOf<BaseInfoUnit>()

                //Temp Variables
                var singleTaskListCollection: ArrayList<BaseTaskList>? = null

                //SAX Parser Variables
                var currentValue = ""
                var currentElement = false
                var currentInfo = ""

                // This method is invoked when start parse xml element node use sax parser.
                @Throws(SAXException::class)
                override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {

                    currentElement = true
                    currentValue = ""

                    when (localName) {
                        TAG_JOB -> {
                            job = BaseJob()

                            //Get required Attributes for Job TAG
                            xml?.job_Attrs?.let {
                                jobAttrs = readAttributes(attributes, it)
                            }
                        }
                        TAG_INFO_UNIT -> {

                            val parsedType = attributes.getValue(ATTR_TYPE)

                            //Only look for Job Summary Info types
                            if (xml?.info_info_unit_Attrs_Types!!.contains(parsedType) && jobInfoSummary?.size!! <= xml?.max_summary_info_tags!!) {

                                //Set current attribute name
                                currentInfo = parsedType
                            }
                        }
                        TAG_INFO -> {
                            info = BaseInfo(infoUnit)
                        }
                        TAG_TASK_LIST -> {

                            if (singleTaskListCollection == null) {
                                singleTaskListCollection = arrayListOf()
                            }

                            var taskListAttrs = arrayListOf<String>()

                            //Get required Attributes for TaskList1 TAG
                            xml?.main_task_list_Attrs1?.let {
                                taskListAttrs = readAttributes(attributes, it)
                            }

                            val singleTaskList = BaseTaskList(taskListAttrs)
                            singleTaskListCollection?.add(singleTaskList)
                        }
                        TAG_TASK -> {

                            var taskAttrs = arrayListOf<String>()

                            //Get required Attributes for Task TAG
                            xml?.task_item_Attrs?.let {
                                taskAttrs = readAttributes(attributes, it)
                            }

                            val taskItem = BaseTask(taskAttrs)

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

                            //Add info only for selected type
                            if (xml?.info_info_unit_Attrs_Types!!.contains(currentInfo) && jobInfoSummary?.size!! <= xml?.max_summary_info_tags!!) {
                                jobInfoSummary?.add(Pair(currentInfo, currentValue))
                                currentInfo = ""
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

            saxParser.parse(inputStream, handler)
        } finally {
            inputStream?.close()
        }

        return job
    }

}