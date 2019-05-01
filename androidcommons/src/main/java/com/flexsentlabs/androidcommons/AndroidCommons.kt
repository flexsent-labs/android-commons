package com.flexsentlabs.androidcommons

import com.flexsentlabs.androidcommons.splash.ui.SplashViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

object AndroidCommons {

    internal lateinit var module: Kodein.Module

    fun initialize(
        splashViewModel: SplashViewModel
    ) {
        module = Kodein.Module(AndroidCommons::class.java.name) {
            bind<SplashViewModel>() with provider {
                splashViewModel
            }
        }
    }
}