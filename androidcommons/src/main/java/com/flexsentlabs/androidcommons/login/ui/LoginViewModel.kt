package com.flexsentlabs.androidcommons.login.ui

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single

abstract class LoginViewModel : ViewModel() {

    abstract fun login(username: String, password: String): Completable

    abstract fun autoLogin(): Completable

    abstract fun isLoggedIn(): Single<Boolean>

    abstract fun onLoggedIn()
}