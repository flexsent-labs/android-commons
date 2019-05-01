package com.flexsentlabs.androidcommons.splash.ui

import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single

abstract class SplashViewModel : ViewModel() {

    abstract fun autoLogin(): Completable

    abstract fun isLoggedIn(): Single<Boolean>
}