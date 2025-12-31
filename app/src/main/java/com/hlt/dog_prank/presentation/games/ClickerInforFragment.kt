package com.hlt.dog_prank.presentation.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentClickerInforBinding
import com.hlt.dog_prank.presentation.BaseFragment

class ClickerInforFragment :
    BaseFragment<FragmentClickerInforBinding>() {

    private var lesson: Int = 1
    private var player: ExoPlayer? = null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentClickerInforBinding {
        return FragmentClickerInforBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        lesson = arguments?.getInt(ARG_LESSON) ?: 1

        setupContent()
        setupPlayer()

        binding.btnClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setupContent() {
        if (lesson == 1) {
            binding.txtTitle.text = getString(R.string.clicker_training_theory)
            binding.txtDes.text = getString(R.string.content_lesson_click_1)
        } else {
            binding.txtTitle.text = getString(R.string.clicker_training_practice)
            binding.txtDes.text = getString(R.string.content_lesson_click_2)
        }
    }

    private fun setupPlayer() {
        val videoUrl =
            if (lesson == 1)
                "https://system.merryblue.vn/dog/video/lesson_clicker_1.mp4"
            else
                "https://system.merryblue.vn/dog/video/lesson_clicker_2.mp4"

        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
        player = null
    }

    companion object {
        private const val ARG_LESSON = "arg_lesson"

        fun newInstance(lesson: Int) =
            ClickerInforFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LESSON, lesson)
                }
            }
    }
}
