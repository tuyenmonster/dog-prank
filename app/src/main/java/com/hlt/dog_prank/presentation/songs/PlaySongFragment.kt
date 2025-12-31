package com.hlt.dog_prank.presentation.songs

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentPlaySongBinding
import com.hlt.dog_prank.presentation.BaseFragment

class PlaySongFragment : BaseFragment<FragmentPlaySongBinding>() {

    private var player: ExoPlayer? = null
    private var isUserSeeking = false

    // pulse anim
    private var pulseAnim1: AnimatorSet? = null
    private var pulseAnim2: AnimatorSet? = null

    private val handler = Handler(Looper.getMainLooper())
    private val progressRunnable = object : Runnable {
        override fun run() {
            val p = player ?: return
            if (!isUserSeeking) {
                val dur = p.duration.coerceAtLeast(0)
                val pos = p.currentPosition.coerceAtLeast(0)
                if (dur > 0) {
                    binding.seekProgress.max = dur.toInt()
                    binding.seekProgress.progress = pos.toInt()
                }
            }
            handler.postDelayed(this, 300)
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaySongBinding {
        return FragmentPlaySongBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        val songUrl = requireArguments().getString("songUrl") ?: return
        val songTitle = requireArguments().getString("songTitle").orEmpty()

        binding.tvSongTitle.text = songTitle

        binding.btnPrev.isEnabled = false
        binding.btnNext.isEnabled = false

        binding.btnPlayPause.setOnClickListener {
            val p = player ?: return@setOnClickListener
            if (p.isPlaying) pause() else resume()
        }

        binding.seekProgress.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(sb: SeekBar?) {
                isUserSeeking = true
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
                player?.seekTo(sb?.progress?.toLong() ?: 0)
                isUserSeeking = false
            }
        })

        initPlayer()
        play(songUrl)
    }

    private fun initPlayer() {
        if (player != null) return
        player = ExoPlayer.Builder(requireContext()).build()
        handler.post(progressRunnable)
    }

    private fun play(url: String) {
        player?.apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            play()
        }
        updatePlayIcon(true)
        startPulse()
    }

    private fun pause() {
        player?.pause()
        updatePlayIcon(false)
        stopPulse()
    }

    private fun resume() {
        player?.play()
        updatePlayIcon(true)
        startPulse()
    }

    private fun updatePlayIcon(isPlaying: Boolean) {
        binding.btnPlayPause.setImageResource(
            if (isPlaying) R.drawable.ic_play_music
            else R.drawable.ic_pause_music
        )
    }

    // =========================
    // PULSE ANIMATION
    // =========================

    private fun startPulse() {
        if (pulseAnim1 == null) pulseAnim1 = buildPulse(binding.pulse1, 0L)
        if (pulseAnim2 == null) pulseAnim2 = buildPulse(binding.pulse2, 550L)

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
        handler.removeCallbacks(progressRunnable)
        stopPulse()
        player?.release()
        player = null
    }

    override fun observeData() {}
}

/**
 * Build pulse animation
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
