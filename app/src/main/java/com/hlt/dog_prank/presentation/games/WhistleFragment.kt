package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import com.github.nisrulz.zentone.ZenTone
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentWhistleBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.domain.utils.showWhistleInfoDialog
import com.hlt.dog_prank.presentation.BaseFragment

class WhistleFragment : BaseFragment<FragmentWhistleBinding>() {

    private lateinit var zenTone: ZenTone
    private var isPlaying = false

    private var frequency = 1000
    private val volume = 100

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWhistleBinding {
        return FragmentWhistleBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        zenTone = ZenTone()

        binding.sliderOpacity.max = 20000
        binding.sliderOpacity.progress = frequency
        binding.txtHz.text = "$frequency Hz"

        binding.icPlay.setOnClickListener {
            if (isPlaying) {
                stopWhistle()
            } else {
                startWhistle()
            }
        }

        // SLIDER đổi Hz
        binding.sliderOpacity.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    frequency = progress.coerceAtLeast(1)
                    binding.txtHz.text = "$frequency Hz"

                    if (isPlaying) {
                        // ZenTone không cho setFrequency → stop & play lại
                        zenTone.stop()
                        zenTone.play(
                            frequency.toFloat(), // Float
                            volume               // Int
                        )
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        // INFO
        binding.icInfor.setOnClickListener {
            showWhistleInfoDialog(requireContext()) {
                mainNavController().navigate(R.id.inforWhistleFragment)
            }
        }
    }

    private fun startWhistle() {
        zenTone.play(
            frequency.toFloat(), // Float
            volume               // Int
        )
        isPlaying = true
        binding.icPlay.setImageResource(R.drawable.ic_stop)
    }

    private fun stopWhistle() {
        zenTone.stop()
        isPlaying = false
        binding.icPlay.setImageResource(R.drawable.ic_whistle)
    }

    override fun onPause() {
        super.onPause()
        stopWhistle()
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = WhistleFragment()
    }
}
