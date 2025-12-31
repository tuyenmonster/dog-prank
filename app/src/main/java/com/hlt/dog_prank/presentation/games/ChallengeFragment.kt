package com.hlt.dog_prank.presentation.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hlt.dog_prank.R
import com.hlt.dog_prank.data.local.ChallengeData
import com.hlt.dog_prank.databinding.FragmentChallengeBinding
import com.hlt.dog_prank.presentation.BaseFragment

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeBinding {
        return FragmentChallengeBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        val list = ChallengeData.getList(requireContext())

        val adapter = ChallengeAdapter(list) { item ->
            val bundle = Bundle().apply {
                putString("songUrl", item.url)
                putString("songTitle", item.title)
            }

            requireActivity()
                .findNavController(R.id.navHostFragment)
                .navigate(R.id.playSongFragment, bundle)
        }

        binding.rvChallenge.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = ChallengeFragment()
    }
}
