package com.flexsentlabs.androidcommons.splash.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Single

abstract class SplashViewModel : ViewModel() {

    abstract fun autoLogin(): Completable

    abstract fun isLoggedIn(): Single<Boolean>

    abstract fun onLoggedIn(context: Context, startFromMainFragment: Boolean)
}