package com.cubivue.base.util.preference

import android.content.Context
import android.content.SharedPreferences
import com.cubivue.base.BuildConfig



class PreferencesHelper private constructor(context: Context) {

    //Declare shared pref instance
    var mPref: SharedPreferences?

    init {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    constructor(context: Context, mMockBrokenSharedPreferences: SharedPreferences) : this(context) {
        mPref = mMockBrokenSharedPreferences
    }

    // General Methods
    fun clearPreference(): Boolean {
        return mPref?.edit()?.clear()?.commit()!!
    }

    fun remove(key: String): Boolean {
        return mPref?.edit()?.remove(key)?.commit()!!
    }

    fun getString(key: String, defaultValue: String): String {
        return mPref?.getString(key, defaultValue)!!
    }

    fun setString(key: String, value: String): Boolean {
        val editor = mPref?.edit()
        mPref?.edit()?.putString(key, value)
        return editor?.commit()!!
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return mPref?.getInt(key, defaultValue)!!
    }

    fun setInt(key: String, value: Int): Boolean {
        val editor = mPref?.edit()
        mPref?.edit()?.putInt(key, value)
        return editor?.commit()!!
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPref?.getBoolean(key, defaultValue)!!
    }

    fun setBoolean(key: String, value: Boolean): Boolean {
        val editor = mPref?.edit()
        mPref?.edit()?.putBoolean(key, value)
        return editor?.commit()!!
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return mPref?.getLong(key, defaultValue)!!
    }

    fun setLong(key: String, value: Long): Boolean {
        val editor = mPref?.edit()
        mPref?.edit()?.putLong(key, value)
        return editor?.commit()!!
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return mPref?.getFloat(key, defaultValue)!!
    }

    fun setFloat(key: String, value: Float): Boolean {
        val editor = mPref?.edit()
        mPref?.edit()?.putFloat(key, value)
        return editor?.commit()!!
    }

    companion object {

        //Declare singleton instance
        private var mInstance: PreferencesHelper? = null

        //static variables
        private val BASE_PACKAGE_NAME = BuildConfig.APPLICATION_ID
        private val PREF_NAME = BASE_PACKAGE_NAME + "PREF"

        //keys
        val KEY_USER_ID = BASE_PACKAGE_NAME + "USER_ID"

        @Synchronized
        fun initializeInstance(context: Context) {
            if (mInstance == null) {
                mInstance = PreferencesHelper(context)
            }
        }

        val instance: PreferencesHelper
            @Synchronized get() {
                if (mInstance == null) {
                    throw IllegalStateException(PreferencesHelper::class.java.simpleName + " is not initialized, call initializeInstance(..) method first.")
                }
                return mInstance as PreferencesHelper
            }
    }

}
