package com.cubivue.app.models.job

interface JobRepositoryTest {

    fun createJob(job: JobTest)

    fun getJobs(): List<JobTest>

    fun findJobById(id: String): JobTest?
}