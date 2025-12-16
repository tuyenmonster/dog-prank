package com.hlt.dog_prank.presentation.sounds

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentSoundsBinding
import com.hlt.dog_prank.presentation.BaseFragment

class SoundsFragment : BaseFragment<FragmentSoundsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSoundsBinding {
        return FragmentSoundsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI danh sách sound
        // ví dụ:
        // binding.recyclerViewSounds.adapter = soundsAdapter
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = SoundsFragment()
    }
}
