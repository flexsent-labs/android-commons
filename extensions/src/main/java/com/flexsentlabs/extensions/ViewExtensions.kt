package com.flexsentlabs.extensions

import android.view.View

fun View.showIf(shouldShow: Boolean) {
    visibility = if (shouldShow) View.VISIBLE else View.INVISIBLE
}