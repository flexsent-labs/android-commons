package com.flexsentlabs.androidcommons.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.flexsentlabs.androidcommons.R
import com.flexsentlabs.androidcommons.app.ui.LoadingDialog
import com.flexsentlabs.androidcommons.app.ui.setDebouncedOnClickListener
import com.flexsentlabs.extensions.viewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import timber.log.Timber

class LoginFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: remove login for release
        emailEditText.setText("admin")
        passwordEditText.setText("password")

        loginCardView.setDebouncedOnClickListener { login() }
    }

    private fun login() {
        viewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { LoadingDialog.show(requireFragmentManager()) }
            .doFinally { LoadingDialog.hide() }
            // will be disposed onDestroyView
            .autoDisposable(AndroidLifecycleScopeProvider.from(viewLifecycleOwner))
            .subscribe(
                {
                    Timber.d("logged in successfully")
                    viewModel.onLoggedIn()
                },
                Timber::e
            )
    }
}