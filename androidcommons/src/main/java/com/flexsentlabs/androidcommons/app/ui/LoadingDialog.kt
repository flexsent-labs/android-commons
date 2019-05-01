package com.flexsentlabs.androidcommons.app.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.flexsentlabs.androidcommons.R

class LoadingDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = createDialog()

    private fun createDialog(): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.dialog_loading)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    companion object {
        private val instance: LoadingDialog by lazy { LoadingDialog() }

        fun show(fragmentManager: FragmentManager?) {
            if (!instance.isAdded) {
                instance.showAllowingStateLoss(fragmentManager, instance::class.java.name)
            }
        }

        fun hide() {
            instance.dismissAllowingStateLoss()
        }

        fun showIf(fragmentManager: FragmentManager?, isShow: Boolean) {
            if (isShow) {
                show(fragmentManager)
            } else {
                hide()
            }
        }
    }
}

fun DialogFragment.showAllowingStateLoss(fragmentManager: FragmentManager?, tag: String) {
    fragmentManager
        ?.beginTransaction()
        ?.add(this, tag)
        ?.commitAllowingStateLoss()
}