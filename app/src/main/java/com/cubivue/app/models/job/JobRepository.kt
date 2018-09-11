package com.cubivue.app.models.job

interface JobRepository {

    fun createJob(job: Job)

    fun getJobs(): List<Job>
}