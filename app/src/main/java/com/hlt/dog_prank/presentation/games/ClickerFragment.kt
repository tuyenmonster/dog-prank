package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentClickerBinding
import com.hlt.dog_prank.presentation.BaseFragment

class ClickerFragment : BaseFragment<FragmentClickerBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentClickerBinding {
        return FragmentClickerBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // ví dụ:
        // binding.btnClick.setOnClickListener { ... }
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = ClickerFragment()
    }
}
