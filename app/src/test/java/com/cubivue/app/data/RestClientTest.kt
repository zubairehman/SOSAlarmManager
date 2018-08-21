package com.cubivue.app.data

import com.cubivue.app.BuildConfig
import com.cubivue.app.TestApp
import com.cubivue.app.data.repositories.LoginRepository
import com.cubivue.base.models.apis.JobResponse
import com.google.gson.Gson
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28, application = TestApp::class)
class RestClientTest {

    @Inject
    lateinit var repository: LoginRepository

    lateinit var testObserver: TestObserver<JobResponse>
    lateinit var testScheduler: TestScheduler
    lateinit var webServer: MockWebServer

    @Before
    @Throws(Exception::class)
    fun setUp() {
        (RuntimeEnvironment.application as TestApp).createAppComponent()?.inject(this)

        testObserver = TestObserver()
        testScheduler = TestScheduler()
        webServer = MockWebServer()

        webServer.enqueue(getMockedResponse().setResponseCode(200))

        webServer.start()
        webServer.url(BuildConfig.BASE_URL)

        repository.getAssignedJobs()
                .subscribeOn(testScheduler)
                .observeOn(testScheduler)
                .subscribe(testObserver)

        testScheduler.triggerActions()
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer.shutdown()
    }

    @Test
    fun checkIfSubscribed() {
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        testObserver.assertSubscribed()
    }

    @Test
    fun checkOnNext() {
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        testObserver.assertValuesOnly(JobResponse("1", "Job 1"))
    }

    @Test
    fun checkResult() {
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        testObserver.assertValue {
            it == JobResponse("1", "Job 1")
        }
    }

    @Test
    fun hasNoErrors() {
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        testObserver.assertNoErrors()
    }

    @Test
    fun responseNotEmpty() {
        testScheduler.advanceTimeBy(3000, TimeUnit.MILLISECONDS)
        testObserver.assertEmpty()
    }

    private fun getMockedResponse(): MockResponse {
        val job1 = JobResponse("1", "Job 1")
        return MockResponse().setBody(Gson().toJson(job1))
    }
}