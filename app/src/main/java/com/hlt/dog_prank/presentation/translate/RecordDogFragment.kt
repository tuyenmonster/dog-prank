package com.hlt.dog_prank.presentation.translate

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.AssetFileDescriptor
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.hlt.dog_prank.data.local.TranslateDataSource
import com.hlt.dog_prank.databinding.FragmentRecordDogBinding
import com.hlt.dog_prank.domain.utils.showTranslateResultDialog
import com.hlt.dog_prank.presentation.BaseFragment
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import kotlin.concurrent.thread
import kotlin.math.abs
import kotlin.random.Random

class RecordDogFragment : BaseFragment<FragmentRecordDogBinding>() {

    // ======================
    // STATE
    // ======================
    private var isRecording = false
    private var isDogDetected = false
    private var recordSeconds = 0

    private var audioRecord: AudioRecord? = null
    private var audioThread: Thread? = null
    private var tensorFlowInterpreter: Interpreter? = null

    private val lockObject = Any()
    private val handler = Handler(Looper.getMainLooper())

    // ======================
    // AUDIO CONFIG
    // ======================
    private val sampleRate = 16000
    private val bufferSize by lazy {
        AudioRecord.getMinBufferSize(
            sampleRate,
            AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
    }

    // ======================
    // TIMER (00:00)
    // ======================
    private val timerRunnable = object : Runnable {
        override fun run() {
            recordSeconds++
            binding.txtHint.text = String.format("00:%02d", recordSeconds)
            handler.postDelayed(this, 1000)
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecordDogBinding {
        return FragmentRecordDogBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        binding.txtStop.visibility = View.GONE
        binding.txtHint.text = "Tap mic to record dog sound"

        binding.btnRecord.setOnClickListener {
            if (!hasAudioPermission()) {
                requestPermissions(
                    arrayOf(Manifest.permission.RECORD_AUDIO),
                    REQ_AUDIO_PERMISSION
                )
                return@setOnClickListener
            }

            if (!isRecording) startAudioRecording()
            else stopAndRelease()
        }
    }

    // ======================
    // PERMISSION
    // ======================
    private fun hasAudioPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQ_AUDIO_PERMISSION &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            startAudioRecording()
        }
    }

    // ======================
    // START RECORD
    // ======================
    private fun startAudioRecording() {
        Log.d(TAG, "🎙️ Start recording")

        isRecording = true
        isDogDetected = false
        recordSeconds = 0

        binding.icMic.visibility = View.GONE
        binding.txtStop.visibility = View.VISIBLE
        binding.txtHint.text = "00:00"

        handler.post(timerRunnable)

        try {
            tensorFlowInterpreter = Interpreter(loadModel())

            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                bufferSize
            )

            audioRecord?.startRecording()

            audioThread = thread {
                val buffer = ShortArray(bufferSize)
                while (isRecording) {
                    val read = audioRecord?.read(buffer, 0, buffer.size) ?: 0
                    if (read > 0) {
                        processAudioData(buffer)
                    }
                }
            }

        } catch (e: SecurityException) {
            Log.e(TAG, "RECORD_AUDIO denied", e)
            resetUI("Microphone permission denied")
        }
    }

    // ======================
    // PROCESS AUDIO (FAKE DOG LOGIC)
    // ======================
    private fun processAudioData(audioData: ShortArray) {
        synchronized(lockObject) {
            val interpreter = tensorFlowInterpreter ?: return

            val input = FloatArray(15600)
            val limit = minOf(audioData.size, 15600)

            var maxAmplitude = 0
            for (i in 0 until limit) {
                val amp = abs(audioData[i].toInt())
                if (amp > maxAmplitude) maxAmplitude = amp
                input[i] = audioData[i] / 32768f
            }

            val output = Array(1) { FloatArray(521) }
            interpreter.run(arrayOf(input), output)

            var maxIndex = -1
            var maxValue = 0f
            output[0].forEachIndexed { idx, value ->
                if (value > maxValue) {
                    maxValue = value
                    maxIndex = idx
                }
            }

            Log.d(
                TAG,
                "🧠 idx=$maxIndex conf=$maxValue amp=$maxAmplitude"
            )

            // ======================
            // 🔥 FAKE DOG DETECT (GIỐNG APP GỐC)
            // ======================
            if (
                recordSeconds >= 2 && // minimum listen time
                (
                        maxAmplitude > 1200 ||        // loud sound
                                maxValue > 0.55f              // confident model
                        )
            ) {
                // thêm random để giống AI thật
                if (Random.nextFloat() > 0.25f) {
                    Log.w(TAG, "🐶 FAKE DOG DETECTED")
                    isDogDetected = true
                }
            }
        }
    }

    // ======================
    // STOP + RELEASE
    // ======================
    private fun stopAndRelease() {
        Log.d(TAG, "⏹ Stop recording")

        isRecording = false
        handler.removeCallbacks(timerRunnable)

        binding.icMic.visibility = View.VISIBLE
        binding.txtStop.visibility = View.GONE
        binding.txtHint.text = "Analyzing..."

        try {
            audioThread?.join(300)
        } catch (_: InterruptedException) {
        }
        audioThread = null

        audioRecord?.apply {
            stop()
            release()
        }
        audioRecord = null

        synchronized(lockObject) {
            tensorFlowInterpreter?.close()
            tensorFlowInterpreter = null
        }

        Log.d(TAG, "Detect result = $isDogDetected")

        handler.postDelayed({
            if (isDogDetected) showResult()
            else binding.txtHint.text = "No dog sound detected"
        }, 3000)
    }

    // ======================
    // RESULT (FAKE)
    // ======================
    private fun showResult() {
        val content = TranslateDataSource.contents.random()
        showTranslateResultDialog(requireContext(), content)
        binding.txtHint.text = "Tap mic to record again"
    }

    // ======================
    // LOAD MODEL (NO LEAK)
    // ======================
    private fun loadModel(): MappedByteBuffer {
        val afd: AssetFileDescriptor =
            requireContext().assets.openFd("voice_detect.tflite")

        afd.use { assetFd ->
            FileInputStream(assetFd.fileDescriptor).use { fis ->
                return fis.channel.map(
                    FileChannel.MapMode.READ_ONLY,
                    assetFd.startOffset,
                    assetFd.declaredLength
                )
            }
        }
    }

    private fun resetUI(msg: String) {
        handler.removeCallbacks(timerRunnable)
        isRecording = false
        binding.icMic.visibility = View.VISIBLE
        binding.txtStop.visibility = View.GONE
        binding.txtHint.text = msg
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(timerRunnable)
        isRecording = false
        audioRecord?.release()
        audioRecord = null
        tensorFlowInterpreter?.close()
        tensorFlowInterpreter = null
    }

    override fun observeData() {}

    companion object {
        private const val TAG = "RecordDog"
        private const val REQ_AUDIO_PERMISSION = 1001
    }
}
