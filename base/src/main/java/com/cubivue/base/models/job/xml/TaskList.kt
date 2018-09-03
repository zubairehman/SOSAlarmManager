package com.cubivue.base.models.job.xml

data class TaskList(var attributes: ArrayList<String>) {
    var tasks: ArrayList<Task>? = null
}