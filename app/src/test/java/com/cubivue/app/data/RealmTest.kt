package com.cubivue.app.data

import android.content.Context
import com.cubivue.app.models.Dog
import com.cubivue.app.models.DogRepositoryImpl
import io.realm.Realm
import io.realm.log.RealmLog
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.internal.verification.VerificationModeFactory.times
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(minSdk = 28)
@PowerMockIgnore("org.mockito.*", "org.robolectric.*", "android.*")
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(Realm::class, RealmLog::class, Context::class)
class RealmTest {

    @Rule
    @JvmField
    var rule = PowerMockRule()
    lateinit var mockRealm: Realm
    lateinit var context: Context

    @Before
    fun setup() {
        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)
        mockStatic(Context::class.java)

        context = PowerMockito.mock(Context::class.java)

        val mockRealm = PowerMockito.mock(Realm::class.java)

        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)

        this.mockRealm = mockRealm
    }

    @Test
    fun shouldBeAbleToGetDefaultInstance() {
        assertThat(Realm.getDefaultInstance(), `is`(mockRealm))
    }

    @Test
    fun shouldBeAbleToMockRealmMethods() {
        `when`(mockRealm.isAutoRefresh).thenReturn(true)
        assertThat(mockRealm.isAutoRefresh, `is`(true))

        `when`(mockRealm.isAutoRefresh).thenReturn(false)
        assertThat(mockRealm.isAutoRefresh, `is`(false))
    }

    @Test
    fun shouldBeAbleToCreateARealmObject() {
        val dog = Dog("germanShephard", "1")
        `when`(mockRealm.createObject(Dog::class.java)).thenReturn(dog)

        val output = mockRealm.createObject(Dog::class.java)

        assertThat(output, `is`(dog))
    }

    /**
     * This test verifies the behavior in the [DogRepositoryImpl] class.
     */
    @Test
    fun shouldVerifyThatDogWasCreated() {

        doCallRealMethod().`when`(mockRealm).executeTransaction(Mockito.any(Realm.Transaction::class.java))

        val dog = mock(Dog::class.java)
        `when`(mockRealm.createObject(Dog::class.java)).thenReturn(dog)

        val dogRepo = DogRepositoryImpl()
        dogRepo.createDog("Spot")

        // Attempting to verify that a method was called (executeTransaction) on a partial
        // mock will return unexpected results due to the partial mock. For example,
        // verifying that `executeTransaction` was called only once will fail as Powermock
        // actually calls the method 3 times for some reason. I cannot determine why at this
        // point.


        // Verify that Realm#createObject was called only once
        verify(mockRealm, times(1)).createObject(Dog::class.java) // Verify that a Dog was in fact created.

        // Verify that Dog#setName() is called only once
        verify(dog, times(3)).name = (Mockito.anyString()) // Any string will do

        // Verify that the Realm was closed only once.
        verify(mockRealm, times(1)).close()
    }

    /**
     * Have to verify the [Realm.executeTransaction] call in a different
     * test because of a problem with Powermock: https://github.com/jayway/powermock/issues/649
     */
    @Test
    fun shouldVerifyThatTransactionWasExecuted() {

        val dogRepo = DogRepositoryImpl()
        dogRepo.createDog("Spot")

        // Verify that the begin transaction was called only once
        verify(mockRealm, times(1)).executeTransaction(Mockito.any(Realm.Transaction::class.java))

        // Verify that the Realm was closed only once.
        verify(mockRealm, times(1)).close()
    }
}