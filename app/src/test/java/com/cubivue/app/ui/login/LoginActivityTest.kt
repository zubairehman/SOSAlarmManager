package com.cubivue.app.ui.login

import com.cubivue.app.TestApp
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28, application = TestApp::class)
public class LoginActivityTest {

    var activity: LoginActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {

        activity = Robolectric.buildActivity(LoginActivity::class.java)
                .create()
                .visible()
                .resume()
                .get()
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

}
