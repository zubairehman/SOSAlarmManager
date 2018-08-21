package com.cubivue.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.cubivue.app.R
import com.cubivue.app.ui.settings.SettingsActivity
import com.cubivue.base.baseUi.BaseActivity
import com.cubivue.base.util.permissions.PermissionManager
import com.cubivue.base.util.permissions.PermissionUtils
import com.cubivue.base.util.permissions.enums.PermissionEnum
import com.cubivue.base.util.permissions.interfaces.FullCallback
import com.cubivue.base.util.preference.PreferencesHelper
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : BaseActivity(), FullCallback {

    //static variables
    private val REQUEST_PERMISSIONS = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //preference test
        PreferencesHelper.instance.setString(PreferencesHelper.KEY_USER_ID, "Zubair")

        //permission test
        if (isStoragePermissionGranted()) {

        } else {
            requestStoragePermissions()
        }

        //preference activity test
        tvHelloWorld.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
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

        if (PermissionUtils.isGranted(this@LoginActivity, PermissionEnum.WRITE_EXTERNAL_STORAGE) && PermissionUtils.isGranted(this@LoginActivity, PermissionEnum.READ_EXTERNAL_STORAGE)) {
            flag = true
        }
        return flag
    }

    private fun requestStoragePermissions() {
        PermissionManager.Builder()
                .key(REQUEST_PERMISSIONS)
                .permission(PermissionEnum.READ_EXTERNAL_STORAGE, PermissionEnum.WRITE_EXTERNAL_STORAGE)
                .callback(this@LoginActivity)
                .ask(this@LoginActivity)
    }

    private fun showDialog(isNeverAskAgainChecked: Boolean) {
        AlertDialog.Builder(this@LoginActivity)
                .setTitle("Permission needed")
                .setMessage(R.string.permission_confirmation)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->

                    if (!isNeverAskAgainChecked) {
                        requestStoragePermissions()
                    } else {
                        PermissionUtils.openApplicationSettings(this@LoginActivity, R::class.java.getPackage().name)
                    }
                }
                .setNegativeButton(android.R.string.cancel) { dialogInterface, i -> dialogInterface.dismiss() }
                .show()
    }
}