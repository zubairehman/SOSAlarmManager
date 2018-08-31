package com.cubivue.base.util.parser

import com.cubivue.base.models.job.xml.Info
import com.cubivue.base.models.job.xml.InfoUnit
import com.cubivue.base.models.job.xml.Job
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

@Throws(XmlPullParserException::class, IOException::class)
internal fun readJobXML(parser: XmlPullParser): Job {

    var eventType = parser.eventType

    var job: Job? = null
    var jobAttrs: Triple<String, String, String> = Triple("", "", "")
    var info: Info? = null
    val infoUnit: ArrayList<InfoUnit> = arrayListOf()

    while (eventType != XmlPullParser.END_DOCUMENT) {

        when (eventType) {
            XmlPullParser.START_DOCUMENT -> {

            }
            XmlPullParser.START_TAG -> {

                when (parser.name) {
                    TAG_JOB -> {
                        jobAttrs = readJobAttributes(parser)
                    }
                    TAG_INFO_UNIT -> {
                        infoUnit.add(readInfoUnit(parser))
                    }
                    TAG_INFO -> {
                        info = Info(infoUnit)
                    }
                    TAG_MAIN -> {
                    }
                }
            }
            XmlPullParser.END_TAG -> {
                info?.info_unit = infoUnit
                job = Job(jobAttrs.first, jobAttrs.second, jobAttrs.third, info)
            }
            XmlPullParser.END_DOCUMENT -> {

            }
        }

        eventType = parser.next()
    }

    return job!!
}

@Throws(IOException::class, XmlPullParserException::class)
private fun readJobAttributes(parser: XmlPullParser): Triple<String, String, String> {

    var jobId = ""
    var jobName = ""
    var date = ""

    val tag = parser.name

    if (tag == TAG_JOB) {
        jobId = parser.getAttributeValue("", ATTR_JOB_ID)
        jobName = parser.getAttributeValue("", ATTR_JOB_NAME)
        date = parser.getAttributeValue("", ATTR_DATE)
        parser.nextTag()
    }

    return Triple(jobId, jobName, date)
}


@Throws(XmlPullParserException::class, IOException::class)
private fun readInfo(parser: XmlPullParser): Info {

    var infoUnit: InfoUnit? = null
    var type: String? = null

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