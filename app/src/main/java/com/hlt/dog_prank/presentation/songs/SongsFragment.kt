package com.hlt.dog_prank.presentation.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentSongsBinding
import com.hlt.dog_prank.presentation.BaseFragment

class SongsFragment : BaseFragment<FragmentSongsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSongsBinding {
        return FragmentSongsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI danh sách bài hát
        // ví dụ:
        // binding.recyclerViewSongs.adapter = songsAdapter
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = SongsFragment()
    }
}
