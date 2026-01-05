package com.hlt.dog_prank.presentation.games

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.os.bundleOf
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentClickerBinding
import com.hlt.dog_prank.databinding.PopupClickerMenuBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.domain.utils.showClickerInfoDialog
import com.hlt.dog_prank.presentation.BaseFragment

class ClickerFragment : BaseFragment<FragmentClickerBinding>() {

    private var mediaPlayer: MediaPlayer? = null

    private var currentSoundRes = R.raw.click1_1

    private val soundMap = mapOf(
        1 to R.raw.click1_1,
        2 to R.raw.click2_2,
        3 to R.raw.click3_3
    )

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentClickerBinding {
        return FragmentClickerBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        // Click play
        binding.icPlay.setOnClickListener {
            playClickSound()
        }

        // Open popup menu
        binding.menu.setOnClickListener {
            showSoundPopup()
        }

        // Info
        binding.icBook.setOnClickListener {
            showClickerInfoDialog(
                requireContext(),
                onLesson1 = {
                    mainNavController().navigate(
                        R.id.clickerInforFragment,
                        bundleOf("lesson" to 1)
                    )
                },
                onLesson2 = {
                    mainNavController().navigate(
                        R.id.clickerInforFragment,
                        bundleOf("lesson" to 2)
                    )
                }
            )
        }
    }

    private fun showSoundPopup() {
        val popupBinding = PopupClickerMenuBinding.inflate(layoutInflater)

        val popupWindow = PopupWindow(
            popupBinding.root,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupBinding.item1.setOnClickListener {
            selectSound(1)
            popupWindow.dismiss()
        }

        popupBinding.item2.setOnClickListener {
            selectSound(2)
            popupWindow.dismiss()
        }

        popupBinding.item3.setOnClickListener {
            selectSound(3)
            popupWindow.dismiss()
        }

        popupWindow.elevation = 10f
        popupWindow.showAsDropDown(binding.menu)
    }

    private fun selectSound(index: Int) {
        currentSoundRes = soundMap[index] ?: R.raw.click1_1
        binding.txtTitle.text = "Sound $index"
    }

    private fun playClickSound() {
        stopSound()
        mediaPlayer = MediaPlayer.create(requireContext(), currentSoundRes)
        mediaPlayer?.start()
    }

    private fun stopSound() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onPause() {
        super.onPause()
        stopSound()
    }

    override fun observeData() {}
}
