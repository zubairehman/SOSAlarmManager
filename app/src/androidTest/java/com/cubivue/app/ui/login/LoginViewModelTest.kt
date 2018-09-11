package com.cubivue.app.ui.login

import com.cubivue.app.TestApp
import com.cubivue.app.data.repositories.LoginRepository
import com.cubivue.base.data.network.MockBaseRestClient
import com.cubivue.base.models.apis.JobResponse
import io.reactivex.schedulers.TestScheduler
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28, application = TestApp::class)
class LoginViewModelTest {

    lateinit var mockClient: MockBaseRestClient
    lateinit var loginViewModel: LoginViewModel
    lateinit var testScheduler: TestScheduler
    lateinit var loginRepository: LoginRepository

    var webServer: MockWebServer? = null

    @Before
    fun setup() {
        testScheduler = TestScheduler()

        val data = JobResponse("1", "Job1")
        mockClient = MockBaseRestClient(testScheduler, data)

        //loginViewModel = LoginViewModel(loginRepository, api)

        webServer = MockWebServer()
        webServer?.start(8080)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        webServer?.shutdown()
    }

    @Test
    fun getRepoShouldShowData() {

    }
}