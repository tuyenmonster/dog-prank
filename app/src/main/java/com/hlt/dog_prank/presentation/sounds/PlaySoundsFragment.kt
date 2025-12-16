package com.hlt.dog_prank.presentation.sounds

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentPlaySoundsBinding
import com.hlt.dog_prank.presentation.BaseFragment

class PlaySoundsFragment : BaseFragment<FragmentPlaySoundsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlaySoundsBinding {
        return FragmentPlaySoundsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI play sound
        // ví dụ:
        // binding.btnPlay.setOnClickListener { playSound() }
        // binding.btnStop.setOnClickListener { stopSound() }
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = PlaySoundsFragment()
    }
}
