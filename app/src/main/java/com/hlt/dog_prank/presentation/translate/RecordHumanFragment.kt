package com.hlt.dog_prank.presentation.translate

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentRecordHumanBinding
import com.hlt.dog_prank.domain.utils.buildPulse
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.presentation.BaseFragment
import java.util.Locale

class RecordHumanFragment : BaseFragment<FragmentRecordHumanBinding>() {

    // ===== STATE =====
    private enum class RecordState { IDLE, RECORDING, RECORDED }
    private var recordState = RecordState.IDLE

    private var seconds = 0
    private var recognizedText: String? = null
    private var targetPet: String = "dog"

    private val handler = Handler(Looper.getMainLooper())

    // ===== SPEECH =====
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var speechIntent: Intent

    // ===== PULSE ANIM (GIỮ NGUYÊN) =====
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
        targetPet = arguments?.getString(ARG_TARGET, "dog") ?: "dog"
        updateTargetPetUI()

        binding.txtStop.visibility = View.GONE
        binding.pulse1.alpha = 0f
        binding.pulse2.alpha = 0f

        initSpeech()

        binding.btnRecord.setOnClickListener { toggleRecord() }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.btnSelectLanguage.setOnClickListener {
            mainNavController().navigate(R.id.selectLanguageFragment)
        }

        parentFragmentManager.setFragmentResultListener(
            REQ_LANGUAGE,
            viewLifecycleOwner
        ) { _, bundle ->
            binding.txtLanguage.text = bundle.getString(KEY_LANGUAGE)
        }
    }

    // =========================
    // RECORD LOGIC
    // =========================

    private fun toggleRecord() {
        when (recordState) {
            RecordState.IDLE -> startRecord()
            RecordState.RECORDING -> stopRecord()
            RecordState.RECORDED -> goToResult()
        }
    }

    private fun startRecord() {
        recordState = RecordState.RECORDING
        seconds = 0
        recognizedText = null

        binding.icMic.visibility = View.GONE
        binding.txtStop.visibility = View.VISIBLE
        binding.txtHint.text = "00:00"
        binding.txtResultRecord.text = ""

        handler.post(timerRunnable)
        startPulse()

        speechRecognizer.startListening(speechIntent)
    }

    private fun stopRecord() {
        recordState = RecordState.RECORDED

        handler.removeCallbacks(timerRunnable)
        stopPulse()

        binding.icMic.visibility = View.VISIBLE
        binding.txtStop.visibility = View.GONE
        binding.txtHint.text = "Tap mic to continue"

        speechRecognizer.stopListening()
    }

    private fun goToResult() {
        if (recognizedText.isNullOrBlank()) return

        mainNavController().navigate(
            R.id.resultTranslateFragment,
            Bundle().apply {
                putString(ARG_TARGET, targetPet)
                putString(KEY_RECORD_TEXT, recognizedText)
            }
        )
    }

    // =========================
    // SPEECH INIT
    // =========================

    private fun initSpeech() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(object : RecognitionListener {

            override fun onResults(results: Bundle?) {
                recognizedText = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()

                binding.txtResultRecord.text = recognizedText ?: ""
            }

            override fun onError(error: Int) {
                stopRecord()
            }

            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault().toString()
            )
        }
    }

    // =========================
    // UI + ANIM (KHÔNG ĐỔI)
    // =========================

    private fun updateTargetPetUI() {
        if (targetPet == "cat") {
            binding.icAnimal.setImageResource(R.drawable.ic_cat)
            binding.txtAnimal.text = "Cat"
        } else {
            binding.icAnimal.setImageResource(R.drawable.ic_dog)
            binding.txtAnimal.text = "Dog"
        }
    }

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
        handler.removeCallbacks(timerRunnable)
        stopPulse()
        if (::speechRecognizer.isInitialized) {
            speechRecognizer.destroy()
        }
    }

    override fun observeData() {}

    companion object {
        const val ARG_TARGET = "target_pet"
        const val REQ_LANGUAGE = "req_language"
        const val KEY_LANGUAGE = "key_language"
        const val KEY_RECORD_TEXT = "record_text"
    }
}
