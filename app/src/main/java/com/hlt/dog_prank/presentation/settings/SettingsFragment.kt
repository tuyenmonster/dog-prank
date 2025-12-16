package com.hlt.dog_prank.presentation.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.databinding.FragmentSettingsBinding
import com.hlt.dog_prank.presentation.BaseFragment

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // setup UI settings
        // ví dụ:
        // binding.switchSound.isChecked = true
    }

    override fun observeData() {
        // observe ViewModel nếu có
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}
