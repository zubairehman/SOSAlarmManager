package com.cubivue.base.util.parser

import android.content.Context
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import java.io.InputStream

/*
 * Read XML file as InputStream.
 */
fun readAssetsXML(fileName: String, context: Context): InputStream? {
    val am = context.assets
    return am.open("xml/$fileName")
}

/*
 * Skip tags that not need parsing.
 */
@Throws(XmlPullParserException::class, IOException::class)
fun skip(parser: XmlPullParser) {
    var depth = 1
    while (depth != 0) {
        when (parser.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
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