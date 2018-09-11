package com.cubivue.base.models.job.xml

data class BaseTaskList(var attributes: ArrayList<String>) {
    var tasks: ArrayList<BaseTask>? = null
}