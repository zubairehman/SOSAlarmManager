package com.embrace.soslibrary

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@SuppressStaticInitializationFor("io.realm.internal.Util")
class BaseDBTest {

    @Rule
    @JvmField
    var rule = PowerMockRule()
    var testRealm: Realm? = null

    @Rule
    @JvmField
    var mTempFolder = TemporaryFolder()

    var mMockContext: Context? = null

    @Before
    @Throws(Exception::class)
    fun setup() {

        mMockContext = RuntimeEnvironment.application.applicationContext

        //`when`(mMockContext?.filesDir).thenReturn(mTempFolder.newFolder())

        Realm.init(mMockContext!!)
        val testConfig = RealmConfiguration.Builder().inMemory().name("test-realm").build()
        testRealm = Realm.getInstance(testConfig)
    }

    @Test
    @Throws(Exception::class)
    fun shouldNotBeNull() {
        Assert.assertNotNull(testRealm)
    }
}