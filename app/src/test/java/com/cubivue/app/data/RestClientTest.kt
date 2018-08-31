package com.cubivue.app.data

import com.cubivue.base.data.network.BaseRestService
import com.cubivue.base.models.apis.JobResponse
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28)
class RestClientTest {

    lateinit var testScheduler: TestScheduler
    lateinit var testObserver: TestObserver<JobResponse>

    @Mock
    var api: BaseRestService? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        testObserver = TestObserver()
        testScheduler = TestScheduler()
    }

    @Test
    fun shouldGetJobResponse() {

        //Make api return mock data
        Mockito.`when`(api?.getJobsFromServer("1")).thenReturn(Observable.just(JobResponse("1", "Job 1")))

        //Subscribe with test subscriber
        api?.getJobsFromServer("1")?.subscribe(testObserver)

        //Wait till subscribed
        testObserver.awaitTerminalEvent()

        //Make sure that there are no errors
        testObserver.assertNoErrors()

        //Verify if method was called
        verify(api, times(1))?.getJobsFromServer("1")
    }
}