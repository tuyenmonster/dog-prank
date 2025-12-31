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
import com.hlt.dog_prank.databinding.FragmentRecordHumanBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.presentation.BaseFragment

class RecordHumanFragment : BaseFragment<FragmentRecordHumanBinding>() {

    private var isRecording = false
    private var seconds = 0

    private val handler = Handler(Looper.getMainLooper())

    // ===== PULSE ANIM =====
    private var pulseAnim1: AnimatorSet? = null
    private var pulseAnim2: AnimatorSet? = null

    private val timerRunnable = object : Runnable {
        override fun run() {
            seconds++
            binding.txtHint.text = String.format("00:%02d", seconds)
            handler.postDelayed(this, 1000)
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecordHumanBinding {
        return FragmentRecordHumanBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        // init
        binding.txtStop.visibility = View.GONE
        binding.pulse1.alpha = 0f
        binding.pulse2.alpha = 0f

        binding.btnRecord.setOnClickListener {
            toggleRecord()
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnSelectLanguage.setOnClickListener {
            mainNavController().navigate(
                R.id.selectLanguageFragment
            )
        }
        parentFragmentManager.setFragmentResultListener(
            REQ_LANGUAGE,
            viewLifecycleOwner
        ) { _, bundle ->
            binding.txtLanguage.text = bundle.getString(KEY_LANGUAGE)
        }

    }


    // =========================
    // RECORD STATE
    // =========================

    private fun toggleRecord() {
        if (!isRecording) {
            startRecord()
        } else {
            stopRecord()
        }
    }

    private fun startRecord() {
        isRecording = true
        seconds = 0

        binding.icMic.visibility = View.GONE
        binding.txtStop.visibility = View.VISIBLE
        binding.txtHint.text = "00:00"

        handler.post(timerRunnable)
        startPulse()
    }

    private fun stopRecord() {
        isRecording = false

        handler.removeCallbacks(timerRunnable)

        binding.icMic.visibility = View.VISIBLE
        binding.txtStop.visibility = View.GONE
        binding.txtHint.text = "Tap mic to talk"

        stopPulse()
    }

    // =========================
    // PULSE ANIMATION (GIỐNG PlaySongFragment)
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
    companion object {
        const val ARG_TARGET = "target_pet" // "dog" | "cat"
        const val REQ_LANGUAGE = "req_language"
        const val KEY_LANGUAGE = "key_language"
    }

    private var targetPet: String = "dog"
    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(timerRunnable)
        stopPulse()
    }

    override fun observeData() {}
}

/**
 * Build pulse animation (COPY Y HỆT PlaySongFragment)
 */
private fun buildPulse(target: View, startDelay: Long): AnimatorSet {
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
