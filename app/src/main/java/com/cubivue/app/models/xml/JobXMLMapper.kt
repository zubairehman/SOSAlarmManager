package com.cubivue.app.models.xml

import com.cubivue.app.models.job.*
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

            baseJob.taskList?.forEach {

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

            baseJob.infoSummary?.forEach {

                when (it.first) {
                    INFO_UNIT_SUBSCRIPTION_SUMMARY -> {
                        job.productsList = RealmList()


                        val rawList = splitText(it.second, ";")

                        for (value in rawList) {
                            val sub = splitText(value, ",")
                            if (sub.isNotEmpty() && sub.first().isNotEmpty() && sub.last().isNotEmpty())
                                job.productsList?.add(Products(sub.first(), sub.last().toInt()))
                        }
                    }

                    INFO_UNIT_PRODUCT_SUMMARY -> {
                        job.addressedProductsList = RealmList()

                        val rawList = splitText(it.second, ";")

                        for (value in rawList) {
                            val sub = splitText(value, ",")
                            if (sub.isNotEmpty() && sub.first().isNotEmpty() && sub.last().isNotEmpty())
                                job.addressedProductsList?.add(AddressedProducts(sub.first(), sub.last().toInt()))
                        }
                    }

                    INFO_UNIT_LABEL_SUMMARY -> {
                        job.labelsList = RealmList()
                        job.labelsList?.add(Labels(it.second))
                    }

                    INFO_UNIT_KEY_SUMMARY -> {
                        job.keysList = RealmList()
                        job.keysList?.add(Keys(it.second))

                    }
                }
            }
        }

        return job
    }

    private fun splitText(text: String, splitBy: String): List<String> {
        return text.split(splitBy).map { it.trim() }
    }
}