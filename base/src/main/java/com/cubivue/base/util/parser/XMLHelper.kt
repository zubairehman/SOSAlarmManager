package com.cubivue.base.util.parser

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream


//XML Types
const val XML_TYPE_JOB = "1"
const val XML_TYPE_PROBLEM_CODES = "2"

/*
 * Read XML file as InputStream.
 */
fun readAssetsXML(fileName: String, context: Context): InputStream? {
    val am = context.assets
    return am.open("xml/$fileName")
}

/*
 * This will extract values from TAGs
 */
@Throws(IOException::class, XmlPullParserException::class)
fun readValues(parser: XmlPullParser): String {
    var result = ""
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.text
        parser.nextTag()
    }
    return result
}