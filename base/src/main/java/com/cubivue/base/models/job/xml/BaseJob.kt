package com.cubivue.base.models.job.xml

class BaseJob() {

    var infoSummary: ArrayList<Pair<String, String>>? = null
    var taskList: ArrayList<BaseTaskList>? = null
    var jobAttributes: ArrayList<String>? = null
}