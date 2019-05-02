package com.flexsentlabs.androidcommons.app.ui

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.flexsentlabs.androidcommons.R
import timber.log.Timber

open class WorkaroundAnimatedViewPagerFragment : Fragment() {

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        Timber.e("onCreateAnimation nextAnim = $nextAnim, " +
            "transit = $transit, " +
            "enter = $enter, " +
            "R.animator.slide_in_left ${R.animator.slide_in_left}, " +
            "R.animator.slide_in_right ${R.animator.slide_in_right}, " +
            "R.animator.slide_out_left ${R.animator.slide_out_left}, " +
            "R.animator.slide_out_right ${R.animator.slide_out_right}")
        return if (!enter && parentFragment != null) {
            dummyAnimation
        }  else super.onCreateAnimation(transit, enter, nextAnim)
    }

    companion object {
        private val dummyAnimation = AlphaAnimation(1f, 1f).also { it.duration = 500 }
    }
}