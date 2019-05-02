package com.flexsentlabs.androidcommons.app.ui

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.flexsentlabs.androidcommons.R
import timber.log.Timber

abstract class WorkaroundAnimatedExitPoppedFragment : Fragment() {

    private val slideOutLeftAnimation by lazy { AnimationUtils.loadAnimation(context, R.anim.slide_out_left) }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        Timber.e(
            "onCreateAnimation nextAnim = $nextAnim, " +
                    "transit = $transit, " +
                    "enter = $enter, " +
                    "shouldSlideOutLeftOnExitAnimation = ${shouldSlideOutLeftOnExitAnimation()}, " +
                    "R.animator.slide_in_left ${R.animator.slide_in_left}, " +
                    "R.animator.slide_in_right ${R.animator.slide_in_right}, " +
                    "R.animator.slide_out_left ${R.animator.slide_out_left}, " +
                    "R.animator.slide_out_right ${R.animator.slide_out_right}"
        )

        return if (!enter && shouldSlideOutLeftOnExitAnimation()) {
            slideOutLeftAnimation
        } else super.onCreateAnimation(transit, enter, nextAnim)
    }

    abstract fun shouldSlideOutLeftOnExitAnimation(): Boolean
}