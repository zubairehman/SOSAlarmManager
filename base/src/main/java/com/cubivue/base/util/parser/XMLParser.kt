package com.cubivue.base.util.parser

import android.content.Context
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.SAXParserFactory


class XMLParser {

    //Parameters for Parser
    lateinit var jobXml: JobXMLStructure

    fun parse(xmlType: String, path: String, context: Context) {

        when (xmlType) {
            XML_TYPE_JOB -> parseJobXML(path, context)
        }
    }

    private fun parseJobXML(path: String, context: Context) {
        val ins = readAssetsXML(path, context)
        return parse(ins)
    }

    @Throws(XmlPullParserException::class, IOException::class)
    private fun parse(inputStream: InputStream?) {
        try {
            val factory = SAXParserFactory.newInstance()
            val saxParser = factory.newSAXParser()
            saxParser.parse(inputStream, readJobXML(jobXml))
        } finally {
            inputStream?.close()
        }
    }

}