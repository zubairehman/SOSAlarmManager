package com.cubivue.base.util.parser

import android.content.Context
import org.xml.sax.Attributes
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

fun readAttributes(attributes: Attributes, listOfAttributes: ArrayList<String>): ArrayList<String> {

    val arrayList = arrayListOf<String>()

    for (attr in listOfAttributes) {
        arrayList.add(attributes.getValue(attr))
    }

    return arrayList
}