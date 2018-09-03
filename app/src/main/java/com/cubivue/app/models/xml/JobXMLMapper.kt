package com.cubivue.app.models.xml

import com.cubivue.app.models.job.Job
import com.cubivue.app.models.job.Task
import com.cubivue.base.models.job.xml.BaseJob
import io.realm.RealmList

class JobXMLMapper {

    fun getMappedJob(baseJob: BaseJob?): Job {
        val job = Job()

        baseJob?.let {

            job.jobId = it.jobAttributes?.get(0)!! //Set Job ID
            job.jobName = it.jobAttributes?.get(1)!! //Set Job Name
            job.productionDate = it.jobAttributes?.get(2)!! //Set Production date

            job.taskList = RealmList()

            it.taskList?.forEach {

                val parentTask = Task()
                parentTask.id = it.attributes.get(0) //Set TaskListId

                job.taskList?.add(parentTask) //Add to Job Task's

                it.tasks?.forEach {
                    val task = Task()
                    task.id = it.attributes.get(0) //Set TaskId
                    task.sortNo = it.attributes.get(2) //Set Sort No
                    task.parentId = parentTask.id //Set Parent Id

                    job.taskList?.add(task) //Add to Job Task's
                }
            }
        }

        return job
    }
}