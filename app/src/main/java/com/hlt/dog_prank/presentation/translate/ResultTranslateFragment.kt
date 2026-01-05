package com.hlt.dog_prank.presentation.translate

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentResultTranslateBinding
import com.hlt.dog_prank.domain.utils.buildPulse
import com.hlt.dog_prank.presentation.BaseFragment

class ResultTranslateFragment : BaseFragment<FragmentResultTranslateBinding>() {

    private var isPlaying = false

    private var pulseAnim1: AnimatorSet? = null
    private var pulseAnim2: AnimatorSet? = null
    private var targetPet: String = "dog"
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResultTranslateBinding {
        return FragmentResultTranslateBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        binding.txtStop.text = "Play"
        binding.pulse1.alpha = 0f
        binding.pulse2.alpha = 0f
        targetPet = arguments?.getString(RecordHumanFragment.ARG_TARGET, "dog") ?: "dog"

        if (targetPet == "cat") {
            binding.icPet.setImageResource(R.drawable.ic_cat)
        } else {
            binding.icPet.setImageResource(R.drawable.ic_dog)
        }
        binding.btnRecord.setOnClickListener {
            togglePlay()
        }
    }

    private fun togglePlay() {
        if (isPlaying) {
            pausePlay()
        } else {
            startPlay()
        }
    }

    private fun startPlay() {
        isPlaying = true
        binding.txtStop.text = "Pause"

        startPulse()

        // TODO: play translated sound here
    }

    private fun pausePlay() {
        isPlaying = false
        binding.txtStop.text = "Play"

        stopPulse()

        // TODO: pause sound here
    }

    // =========================
    // PULSE ANIMATION (y hệt Record)
    // =========================

    private fun startPulse() {
        if (pulseAnim1 == null) pulseAnim1 = buildPulse(binding.pulse1, 0L)
        if (pulseAnim2 == null) pulseAnim2 = buildPulse(binding.pulse2, 550L)

        binding.pulse1.alpha = 0.35f
        binding.pulse2.alpha = 0.35f

        pulseAnim1?.start()
        pulseAnim2?.start()
    }

    private fun stopPulse() {
        pulseAnim1?.cancel()
        pulseAnim2?.cancel()

        binding.pulse1.resetPulse()
        binding.pulse2.resetPulse()
    }

    private fun View.resetPulse() {
        scaleX = 1f
        scaleY = 1f
        alpha = 0f
    }

    override fun onStop() {
        super.onStop()
        stopPulse()
    }

    override fun observeData() {}
}
