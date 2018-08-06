package com.cubivue.app.ui.login

import android.arch.lifecycle.ViewModel
import com.cubivue.app.data.repositories.LoginRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var repo: LoginRepository) : ViewModel(){

}