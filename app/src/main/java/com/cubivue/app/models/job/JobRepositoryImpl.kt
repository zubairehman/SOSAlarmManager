package com.cubivue.app.models.job

import com.cubivue.base.data.local.RealmHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobRepositoryImpl @Inject constructor(private var db: RealmHelper) : JobRepository {

    override fun getJobs(): List<Job> {
        return db.findAll(Job::class.java)
    }

    override fun createJob(job: Job) {
        db.add(job)
    }

}