package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentGamesBinding
import com.hlt.dog_prank.presentation.BaseFragment

class GamesFragment : BaseFragment<FragmentGamesBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGamesBinding {
        return FragmentGamesBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // ví dụ:
        // binding.recyclerView.adapter = gamesAdapter
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = GamesFragment()
    }
}
