package com.cubivue.base.util.parser

import com.cubivue.base.models.job.xml.Info
import com.cubivue.base.models.job.xml.InfoUnit
import com.cubivue.base.models.job.xml.Job
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

@Throws(XmlPullParserException::class, IOException::class)
internal fun readJobXML(parser: XmlPullParser): Job {

    parser.require(XmlPullParser.START_TAG, ns, TAG_JOB)

    var jobAttrs: Triple<String, String, String>? = null
    var info: Info? = null

    while (parser.next() != XmlPullParser.END_TAG) {

        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        val name = parser.name

        // Starts by looking for the entry tag
        if (name == TAG_JOB) {
            jobAttrs = readJobAttributes(parser)
        } else if (name == TAG_INFO) {
            info = readInfo(parser)
        } else if (name == TAG_MAIN) {
            //val readInfo = readInfo(parser))
        } else {
            skip(parser)
        }
    }
    return Job(jobAttrs?.first!!, jobAttrs.second, jobAttrs.third, info!!)
}

@Throws(IOException::class, XmlPullParserException::class)
private fun readJobAttributes(parser: XmlPullParser): Triple<String, String, String> {

    var jobId = ""
    var jobName = ""
    var date = ""

    parser.require(XmlPullParser.START_TAG, ns, TAG_JOB)

    val tag = parser.name

    if (tag == TAG_JOB) {
        jobId = parser.getAttributeValue(null, ATTR_JOB_ID)
        jobName = parser.getAttributeValue(null, ATTR_JOB_NAME)
        date = parser.getAttributeValue(null, ATTR_DATE)
        parser.nextTag()
    }

    parser.require(XmlPullParser.END_TAG, ns, TAG_JOB)
    return Triple(jobId, jobName, date)
}


@Throws(XmlPullParserException::class, IOException::class)
private fun readInfo(parser: XmlPullParser): Info {

    parser.require(XmlPullParser.START_TAG, ns, TAG_INFO)

    var infoUnit: InfoUnit? = null

    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }

        val name = parser.name
        if (name == TAG_INFO_UNIT) {
            infoUnit = readInfoUnit(parser)
        } else {
            skip(parser)
        }
    }
    return Info(arrayListOf(infoUnit!!))
}

@Throws(XmlPullParserException::class, IOException::class)
private fun readInfoUnit(parser: XmlPullParser): InfoUnit {

    parser.require(XmlPullParser.START_TAG, ns, TAG_INFO_UNIT)

    var type = ""

    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }

        val name = parser.name
        if (name == TAG_INFO_UNIT) {
            parser.require(XmlPullParser.START_TAG, ns, TAG_INFO_UNIT)
            type = readValues(parser)
            parser.require(XmlPullParser.END_TAG, ns, TAG_INFO_UNIT)
        } else {
            skip(parser)
        }
    }
    return InfoUnit(type)
}