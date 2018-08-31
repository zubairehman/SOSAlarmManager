package com.cubivue.base.models.job.xml

data class Job(val job_id: String?, val job_name: String?, val date: String?, val info: Info?) {


    override fun toString(): String {
        return "Job(job_id=$job_id, job_name=$job_name, date=$date, info=$info)"
    }
}