package com.hlt.dog_prank.presentation.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentPlaySongBinding
import com.hlt.dog_prank.presentation.BaseFragment

class PlaySongFragment : BaseFragment<FragmentPlaySongBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaySongBinding {
        return FragmentPlaySongBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI, ví dụ:
        // binding.btnPlay.setOnClickListener { playSound() }
        // binding.btnStop.setOnClickListener { stopSound() }
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = PlaySongFragment()
    }
}
