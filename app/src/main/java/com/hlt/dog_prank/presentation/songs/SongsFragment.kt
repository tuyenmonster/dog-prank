package com.hlt.dog_prank.presentation.songs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.dog_prank.R
import com.hlt.dog_prank.data.local.SongData
import com.hlt.dog_prank.databinding.FragmentSongsBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.presentation.BaseFragment

class SongsFragment : BaseFragment<FragmentSongsBinding>() {

    private lateinit var adapter: SongsAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSongsBinding {
        return FragmentSongsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        adapter = SongsAdapter { song ->
            val bundle = Bundle().apply {
                putString("songUrl", song.url)
                putString("songTitle", song.title)
            }
            mainNavController()
                .navigate(R.id.playSongFragment, bundle)
        }

        binding.rvSongs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSongs.adapter = adapter
        adapter.submitList(SongData.list)
    }

    override fun observeData() {}
    companion object {
        fun newInstance() = SongsFragment()
    }
}
