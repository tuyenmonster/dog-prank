package com.hlt.dog_prank.domain.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

fun buildPulse(target: View, startDelay: Long): AnimatorSet {
    target.scaleX = 1f
    target.scaleY = 1f
    target.alpha = 0.35f

    val scaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1f, 1.35f)
    val scaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1f, 1.35f)
    val alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0.35f, 0f)

    return AnimatorSet().apply {
        playTogether(scaleX, scaleY, alpha)
        duration = 1100L
        interpolator = AccelerateDecelerateInterpolator()
        this.startDelay = startDelay

        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatCount = ObjectAnimator.INFINITE
        alpha.repeatCount = ObjectAnimator.INFINITE
    }
}
