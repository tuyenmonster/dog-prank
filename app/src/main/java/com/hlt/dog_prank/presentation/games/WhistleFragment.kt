package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentWhistleBinding
import com.hlt.dog_prank.presentation.BaseFragment

class WhistleFragment : BaseFragment<FragmentWhistleBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWhistleBinding {
        return FragmentWhistleBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // TODO: setup UI
        // ví dụ:
        // binding.btnPlayWhistle.setOnClickListener { ... }
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = WhistleFragment()
    }
}
