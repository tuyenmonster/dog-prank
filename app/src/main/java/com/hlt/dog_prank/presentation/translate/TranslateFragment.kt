package com.hlt.dog_prank.presentation.translate

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentTranslateBinding
import com.hlt.dog_prank.presentation.BaseFragment

class TranslateFragment : BaseFragment<FragmentTranslateBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTranslateBinding {
        return FragmentTranslateBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI dịch chó / mèo
        // ví dụ:
        // binding.btnTranslate.setOnClickListener { ... }
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = TranslateFragment()
    }
}
