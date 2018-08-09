package com.cubivue.base.util

import android.content.Context
import android.content.SharedPreferences
import com.cubivue.base.BuildConfig
import com.cubivue.base.models.SharedPreferenceEntry
import com.cubivue.base.util.preference.PreferencesHelper
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import java.util.*



/**
 * Unit tests for the [PreferencesHelper] that mocks [SharedPreferences].
 */
@RunWith(MockitoJUnitRunner::class)
class PreferencesHelperTest {

    //mock variables
    private var mSharedPreferenceEntry: SharedPreferenceEntry? = null
    private var mMockSharedPreferencesHelper: PreferencesHelper? = null
    private var mMockBrokenSharedPreferencesHelper: PreferencesHelper? = null

    //context
    lateinit var context: Context

    @Mock
    private val mMockSharedPreferences: SharedPreferences? = null

    @Mock
    private val mMockBrokenSharedPreferences: SharedPreferences? = null

    @Mock
    private val mMockEditor: SharedPreferences.Editor? = null

    @Mock
    private val mMockBrokenEditor: SharedPreferences.Editor? = null

    // Create and fill a SharedPreferenceEntry model object.
//    val personalInfo: SharedPreferenceEntry
//        get() {
//            val name = PreferencesHelper.instance.getString(PreferencesHelperTest.KEY_NAME, "")
//            val age = PreferencesHelper.instance.getInt(PreferencesHelperTest.KEY_AGE, 0)
//            val weight = PreferencesHelper.instance.getFloat(PreferencesHelperTest.KEY_NAME, 0f)
//            val dob = PreferencesHelper.instance.getLong(PreferencesHelperTest.KEY_NAME, 0)
//            val isMarried = PreferencesHelper.instance.getBoolean(PreferencesHelperTest.KEY_NAME, false)
//            return SharedPreferenceEntry(name, age, weight, dob, isMarried)
//        }

    @Before
    fun initMocks() {

        //Mock context
//        context = PowerMockito.mock(Context::class.java)

        context = Mockito.mock(Context::class.java)

        //init shared preference
        PreferencesHelper.initializeInstance(context)

        // Create SharedPreferenceEntry to persist.
        mSharedPreferenceEntry = SharedPreferenceEntry(TEST_NAME, TEST_AGE, TEST_WEIGHT, TEST_DATE_OF_BIRTH, TEST_IS_MARRIED)

        // Create a mocked SharedPreferences.
        mMockSharedPreferencesHelper = createMockSharedPreference()

        // Create a mocked SharedPreferences that fails at saving data.
        mMockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference()
    }

    @Test
    fun sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        // Save the personal information to SharedPreferences
        val success = savePersonalInfo(mSharedPreferenceEntry, this!!.mMockSharedPreferencesHelper!!)
//        val success = mMockSharedPreferencesHelper?.savePersonalInfo(this!!.mSharedPreferenceEntry!!)

        assertThat("Checking that SharedPreferenceEntry.save... returns true", success, `is`(true))

        // Read personal information from SharedPreferences
        val savedSharedPreferenceEntry = getPersonalInfo(this!!.mMockSharedPreferencesHelper!!)
//        val savedSharedPreferenceEntry = mMockSharedPreferencesHelper!!.getPersonalInfo()

        // Make sure both written and retrieved personal information are equal.
        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                mSharedPreferenceEntry!!.name,
                `is`(equalTo(savedSharedPreferenceEntry.name)))

        assertThat("Checking that SharedPreferenceEntry.age has been persisted and read correctly",
                mSharedPreferenceEntry!!.age,
                `is`(equalTo(savedSharedPreferenceEntry.age)))

        assertThat("Checking that SharedPreferenceEntry.dateOfBirth has been persisted and read correctly",
                mSharedPreferenceEntry!!.dob,
                `is`(equalTo(savedSharedPreferenceEntry.dob)))

        assertThat("Checking that SharedPreferenceEntry.weight has been persisted and read correctly",
                mSharedPreferenceEntry!!.weight,
                `is`(equalTo(savedSharedPreferenceEntry.weight)))

        assertThat("Checking that SharedPreferenceEntry.married has been persisted and read correctly",
                mSharedPreferenceEntry!!.isMarried,
                `is`(equalTo(savedSharedPreferenceEntry.isMarried)))
    }

    @Test
    fun sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        // Read personal information from a broken SharedPreferencesHelper
        val success = savePersonalInfo(mSharedPreferenceEntry, this!!.mMockBrokenSharedPreferencesHelper!!)
        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                `is`(false))
    }

    /**
     * Creates a mocked SharedPreferences.
     */
    private fun createMockSharedPreference(): PreferencesHelper {
        // Mocking reading the SharedPreferences as if mMockSharedPreferences was previously written
        // correctly.
        `when`(mMockSharedPreferences!!.getString(Matchers.eq(KEY_NAME), Matchers.anyString())).thenReturn(mSharedPreferenceEntry!!.name)

        `when`(mMockSharedPreferences.getInt(Matchers.eq(KEY_AGE), Matchers.anyInt())).thenReturn(mSharedPreferenceEntry!!.age)

        `when`(mMockSharedPreferences.getLong(Matchers.eq(KEY_DOB), Matchers.anyLong())).thenReturn(mSharedPreferenceEntry!!.dob)

        `when`(mMockSharedPreferences.getFloat(Matchers.eq(KEY_WEIGHT), Matchers.anyFloat())).thenReturn(mSharedPreferenceEntry!!.weight)

        `when`(mMockSharedPreferences.getBoolean(Matchers.eq(KEY_MARRIED), Matchers.anyBoolean())).thenReturn(mSharedPreferenceEntry!!.isMarried)

        // Mocking a successful commit.
        `when`(mMockEditor!!.commit()).thenReturn(true)

        // Return the MockEditor when requesting it.
        `when`<SharedPreferences.Editor>(mMockSharedPreferences.edit()).thenReturn(mMockEditor)
        return PreferencesHelper(context, mMockSharedPreferences)
    }

    /**
     * Creates a mocked SharedPreferences that fails when writing.
     */
    private fun createBrokenMockSharedPreference(): PreferencesHelper {
        // Mocking a commit that fails.
        `when`(mMockBrokenEditor!!.commit()).thenReturn(false)

        // Return the broken MockEditor when requesting it.
        `when`<SharedPreferences.Editor>(mMockBrokenSharedPreferences!!.edit()).thenReturn(mMockBrokenEditor)
        return PreferencesHelper(context, mMockBrokenSharedPreferences)
    }

    //General Method to read/write personal info:---------------------------------------------------
    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry?, mMockSharedPreferencesHelper: PreferencesHelper): Boolean {

        return mMockSharedPreferencesHelper.setString(KEY_NAME, sharedPreferenceEntry!!.name) &&
                mMockSharedPreferencesHelper.setInt(PreferencesHelperTest.KEY_AGE, sharedPreferenceEntry.age) &&
                mMockSharedPreferencesHelper.setFloat(PreferencesHelperTest.KEY_WEIGHT, sharedPreferenceEntry.weight) &&
                mMockSharedPreferencesHelper.setLong(PreferencesHelperTest.KEY_DOB, sharedPreferenceEntry.dob) &&
                mMockSharedPreferencesHelper.setBoolean(PreferencesHelperTest.KEY_MARRIED, sharedPreferenceEntry.isMarried)
    }

    fun getPersonalInfo(mMockSharedPreferencesHelper: PreferencesHelper) : SharedPreferenceEntry {
        val name = mMockSharedPreferencesHelper.getString(PreferencesHelperTest.KEY_NAME, "")
        val age = mMockSharedPreferencesHelper.getInt(PreferencesHelperTest.KEY_AGE, 0)
        val weight = mMockSharedPreferencesHelper.getFloat(PreferencesHelperTest.KEY_WEIGHT, 0f)
        val dob = mMockSharedPreferencesHelper.getLong(PreferencesHelperTest.KEY_DOB, 0)
        val isMarried = mMockSharedPreferencesHelper.getBoolean(PreferencesHelperTest.KEY_MARRIED, false)
        return SharedPreferenceEntry(name, age, weight, dob, isMarried)
    }

    companion object {

        //static variables
        private const val TEST_NAME = "Test name"
        private const val TEST_AGE = 27
        private const val TEST_WEIGHT = 80.3f
        private const val TEST_IS_MARRIED = false
        private val TEST_DATE_OF_BIRTH = Calendar.getInstance().timeInMillis

        //preference key variables
        private val BASE_PACKAGE_NAME = BuildConfig.APPLICATION_ID
        private val KEY_NAME = BASE_PACKAGE_NAME + "NAME"
        private val KEY_AGE = BASE_PACKAGE_NAME + "AGE"
        private val KEY_DOB = BASE_PACKAGE_NAME + "DOB"
        private val KEY_WEIGHT = BASE_PACKAGE_NAME + "WEIGHT"
        private val KEY_MARRIED = BASE_PACKAGE_NAME + "MARRIED"
    }
}
