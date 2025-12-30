package com.hlt.dog_prank.presentation.sounds

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentPlaySoundsBinding
import com.hlt.dog_prank.presentation.BaseFragment


class PlaySoundsFragment : BaseFragment<FragmentPlaySoundsBinding>() {


    private var mediaPlayer: MediaPlayer? = null
    private var soundRes: Int = 0
    private var isPlaying = false

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaySoundsBinding {
        return FragmentPlaySoundsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        val args = requireArguments()

        val iconRes = args.getInt("iconRes")
        val titleRes = args.getInt("titleRes")
        soundRes = args.getInt("soundRes")

        // Bind UI
        binding.imgAvatar.setImageResource(iconRes)
        binding.txtTitle.setText(titleRes)

        // Default icon
        updatePlayIcon()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.icPlay.setOnClickListener {
            togglePlayPause()
        }

        setupVolumeControl()
    }

    // ===============================
    // PLAY / PAUSE LOGIC
    // ===============================

    private fun togglePlayPause() {
        if (isPlaying) {
            pauseSound()
        } else {
            playSound()
        }
        updatePlayIcon()
    }

    private fun playSound() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(requireContext(), soundRes).apply {
                isLooping = true
                start()
            }
        } else {
            mediaPlayer?.start()
        }
        isPlaying = true
    }

    private fun pauseSound() {
        mediaPlayer?.pause()
        isPlaying = false
    }

    private fun stopSound() {
        mediaPlayer?.release()
        mediaPlayer = null
        isPlaying = false
    }

    private fun updatePlayIcon() {
        binding.icPlay.setImageResource(
            if (isPlaying) R.drawable.ic_pause_music
            else R.drawable.ic_play_music
        )
    }

    // ===============================
    // VOLUME CONTROL
    // ===============================

    private fun setupVolumeControl() {
        binding.sliderOpacity.setOnSeekBarChangeListener(
            object : android.widget.SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: android.widget.SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val volume = progress / 255f
                    mediaPlayer?.setVolume(volume, volume)
                }

                override fun onStartTrackingTouch(seekBar: android.widget.SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: android.widget.SeekBar?) {}
            }
        )
    }

    // ===============================
    // LIFECYCLE
    // ===============================

    override fun onStop() {
        super.onStop()
        stopSound()
        updatePlayIcon()
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = PlaySoundsFragment()
    }
}
