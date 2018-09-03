package com.cubivue.base.util.parser

import android.content.Context
import android.util.Xml
import com.cubivue.base.models.job.xml.Job
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


class XMLParser {

    //Parameters for Parser
    lateinit var jobXml: JobXMLStructure

    fun parse(xmlType: String, path: String, context: Context) {

        when (xmlType) {
            XML_TYPE_JOB -> parseJobXML(path, context)
        }
    }

    private fun parseJobXML(path: String, context: Context): Job? {
        val ins = readAssetsXML(path, context)
        return parse(ins)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parse(inputStream: InputStream?): Job {
        try {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)
            return readJobXML(parser, jobXml)
        } finally {
            inputStream?.close()
        }
    }

}