package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
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
        // ví dụ:
        // binding.tvTitle.text = "Challenge"
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = ChallengeFragment()
    }
}
