package com.flexsentlabs.androidcommons.splash.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.flexsentlabs.androidcommons.R
import com.flexsentlabs.androidcommons.app.ui.LoadingDialog
import com.flexsentlabs.extensions.viewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import timber.log.Timber

class SplashActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (viewModel.isLoggedIn().blockingGet()) {
            returnOkResult(true)
        } else {
            autoLogin()
        }
    }

    private fun autoLogin() {
        // try to auto login
        viewModel.autoLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { LoadingDialog.show(supportFragmentManager) }
            .doFinally { LoadingDialog.hide() }
            // will be disposed onDestroyView
            .autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle))
            .subscribe(
                {
                    Timber.d("auto logged in successfully")
                    returnOkResult(true)
                },
                {
                    Timber.e(it)
                    returnOkResult(false)
                }
            )
    }

    private fun returnOkResult(isAutoLoggedIn: Boolean) {
        val data = Intent()
        data.putExtra(EXTRA_IS_AUTO_LOGGED_IN, isAutoLoggedIn)
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_IS_AUTO_LOGGED_IN = "EXTRA_IS_AUTO_LOGGED_IN"
    }
}