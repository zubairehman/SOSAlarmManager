package com.cubivue.app.models.job

import io.realm.Realm

class JobRepositoryImplTest constructor(private var db: Realm) : JobRepositoryTest {

    fun setDb(mocked: Realm) {
        this.db = mocked
    }

    override fun findJobById(id: String): JobTest? {
        return db.where(JobTest::class.java).equalTo("jobId", id).findFirst()
    }

    override fun getJobs(): List<JobTest> {
        return db.where(JobTest::class.java).findAll()
    }

    override fun createJob(job: JobTest) {
        db.executeTransaction {
            it.copyToRealmOrUpdate(job)
        }
    }

}