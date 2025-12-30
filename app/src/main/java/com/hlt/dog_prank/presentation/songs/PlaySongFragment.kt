package com.hlt.dog_prank.presentation.songs

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.SeekBar
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.hlt.dog_prank.R
import com.hlt.dog_prank.data.local.SongData
import com.hlt.dog_prank.databinding.FragmentPlaySongBinding
import com.hlt.dog_prank.presentation.BaseFragment
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
class PlaySongFragment : BaseFragment<FragmentPlaySongBinding>() {
    private var pulseAnim1: AnimatorSet? = null
    private var pulseAnim2: AnimatorSet? = null
    private fun startPulse() {
        if (pulseAnim1 == null) pulseAnim1 = buildPulse(binding.pulse1, 0L)
        if (pulseAnim2 == null) pulseAnim2 = buildPulse(binding.pulse2, 550L) // lệch nửa nhịp

        pulseAnim1?.start()
        pulseAnim2?.start()
    }

    private fun stopPulse() {
        pulseAnim1?.cancel()
        pulseAnim2?.cancel()

        // reset về trạng thái nhỏ
        binding.pulse1.apply { scaleX = 1f; scaleY = 1f; alpha = 0f }
        binding.pulse2.apply { scaleX = 1f; scaleY = 1f; alpha = 0f }
    }


    private var player: ExoPlayer? = null
    private var songIndex: Int = 0
    private var isUserSeeking = false

    private val handler = Handler(Looper.getMainLooper())
    private val progressRunnable = object : Runnable {
        override fun run() {
            val p = player ?: return
            if (!isUserSeeking) {
                val dur = p.duration.coerceAtLeast(0L)
                val pos = p.currentPosition.coerceAtLeast(0L)
                if (dur > 0) {
                    binding.seekProgress.max = dur.toInt()
                    binding.seekProgress.progress = pos.toInt()
                }
            }
            handler.postDelayed(this, 300)
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentPlaySongBinding {
        return FragmentPlaySongBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        songIndex = requireArguments().getInt("songIndex", 0).coerceIn(0, SongData.list.lastIndex)

        binding.btnPrev.setOnClickListener { playByIndex(songIndex - 1) }
        binding.btnNext.setOnClickListener { playByIndex(songIndex + 1) }

        binding.btnPlayPause.setOnClickListener {
            val p = player ?: return@setOnClickListener
            if (p.isPlaying) pause() else resume()
        }

        binding.seekProgress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {}
            override fun onStartTrackingTouch(sb: SeekBar?) { isUserSeeking = true }
            override fun onStopTrackingTouch(sb: SeekBar?) {
                val p = player ?: return
                val value = sb?.progress ?: 0
                p.seekTo(value.toLong())
                isUserSeeking = false
            }
        })

        initPlayer()
        playByIndex(songIndex)
    }

    private fun initPlayer() {
        if (player != null) return
        player = ExoPlayer.Builder(requireContext()).build()
        handler.post(progressRunnable)
    }

    private fun playByIndex(newIndex: Int) {
        songIndex = newIndex.coerceIn(0, SongData.list.lastIndex)
        val song = SongData.list[songIndex]
        binding.tvSongTitle.text = song.title

        val p = player ?: return
        p.setMediaItem(MediaItem.fromUri(song.url))
        p.prepare()
        p.playWhenReady = true

        updatePlayIcon(isPlaying = true)
        startPulse()
    }

    private fun pause() {
        player?.pause()
        updatePlayIcon(isPlaying = false)
        stopPulse()
    }

    private fun resume() {
        player?.play()
        updatePlayIcon(isPlaying = true)
        startPulse()
    }


    private fun updatePlayIcon(isPlaying: Boolean) {
        binding.btnPlayPause.setImageResource(
            if (isPlaying) R.drawable.ic_playing else R.drawable.ic_pause
        )
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
private fun buildPulse(target: android.view.View, startDelay: Long): AnimatorSet {
    target.scaleX = 1f
    target.scaleY = 1f
    target.alpha = 0.35f

    val scaleX = ObjectAnimator.ofFloat(target, "scaleX", 1f, 1.35f)
    val scaleY = ObjectAnimator.ofFloat(target, "scaleY", 1f, 1.35f)
    val alpha = ObjectAnimator.ofFloat(target, "alpha", 0.35f, 0f)

    return AnimatorSet().apply {
        playTogether(scaleX, scaleY, alpha)
        duration = 1100L
        interpolator = AccelerateDecelerateInterpolator()
        this.startDelay = startDelay
        // loop
        scaleX.repeatCount = ObjectAnimator.INFINITE
        scaleY.repeatCount = ObjectAnimator.INFINITE
        alpha.repeatCount = ObjectAnimator.INFINITE

        scaleX.repeatMode = ObjectAnimator.RESTART
        scaleY.repeatMode = ObjectAnimator.RESTART
        alpha.repeatMode = ObjectAnimator.RESTART
    }
}
