package com.cubivue.base.ui.baseclasses.activity

import android.arch.lifecycle.ViewModelProvider
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.cubivue.base.R
import com.cubivue.base.ui.baseclasses.fragment.FragmentUtil
import com.cubivue.base.util.permissions.PermissionManager
import com.cubivue.base.util.permissions.PermissionUtils
import com.cubivue.base.util.permissions.enums.PermissionEnum
import com.cubivue.base.util.permissions.interfaces.FullCallback
import com.michaelflisar.rxbus2.interfaces.IRxBusQueue
import com.michaelflisar.rxbus2.rx.RxDisposableManager
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import java.util.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), IRxBusQueue, HasSupportFragmentInjector, FullCallback {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    //static variables
    private val REQUEST_PERMISSIONS = 1

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mResumedProcessor = BehaviorProcessor.createDefault(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
        setContentView(R.layout.base_activity)
        FragmentUtil(this).replaceBaseFragment(getBaseFragment())

        //getting extras
        val extras = intent.extras
        if (extras != null) {
            getExtras(extras.getSerializable("extras") as ArrayList<*>)
        }
    }

    override fun onResume() {
        super.onResume()
        mResumedProcessor.onNext(true)
    }

    override fun onPause() {
        mResumedProcessor.onNext(false)
        super.onPause()
    }

    override fun onDestroy() {
        RxDisposableManager.unsubscribe(this)
        super.onDestroy()
    }

    // ----------------
    // Abstract Methods
    // ----------------

    abstract fun getExtras(extras: ArrayList<*>)

    abstract fun getBaseFragment(): Fragment

    // --------------
    // Interface RxBus
    // --------------

    override fun isBusResumed(): Boolean {
        return mResumedProcessor.value!!
    }

    override fun getResumeObservable(): Publisher<Boolean> {
        return mResumedProcessor
    }

    //Permission Methods:---------------------------------------------------------------------------
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        PermissionManager.handleResult(this, requestCode, permissions, grantResults)
    }

    override fun result(permissionsGranted: ArrayList<PermissionEnum>, permissionsDenied: ArrayList<PermissionEnum>, permissionsDeniedForever: ArrayList<PermissionEnum>, permissionsAsked: ArrayList<PermissionEnum>) {
        if (permissionsGranted.size == permissionsAsked.size) {
            //Do some action

        } else if (permissionsDeniedForever.size > 0) {
            //If user answer "Never ask again" to a request for permission, you can redirect user to app settings, with an utils
            showDialog(true)
        } else {
            showDialog(false)
        }
    }

    private fun isStoragePermissionGranted(): Boolean {
        var flag = false

        if (PermissionUtils.isGranted(this, PermissionEnum.WRITE_EXTERNAL_STORAGE) && PermissionUtils.isGranted(this, PermissionEnum.READ_EXTERNAL_STORAGE)) {
            flag = true
        }
        return flag
    }

    private fun requestStoragePermissions() {
        PermissionManager.Builder()
                .key(REQUEST_PERMISSIONS)
                .permission(PermissionEnum.READ_EXTERNAL_STORAGE, PermissionEnum.WRITE_EXTERNAL_STORAGE)
                .callback(this)
                .ask(this)
    }

    private fun showDialog(isNeverAskAgainChecked: Boolean) {
        AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage(R.string.permission_confirmation)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->

                    if (!isNeverAskAgainChecked) {
                        requestStoragePermissions()
                    } else {
                        PermissionUtils.openApplicationSettings(this, R::class.java.getPackage().name)
                    }
                }
                .setNegativeButton(android.R.string.cancel) { dialogInterface, i -> dialogInterface.dismiss() }
                .show()
    }
}