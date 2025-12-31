package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hlt.dog_prank.databinding.FragmentWhistleBinding
import com.hlt.dog_prank.domain.utils.showWhistleInfoDialog
import com.hlt.dog_prank.presentation.BaseFragment
import com.hlt.dog_prank.R
import com.hlt.dog_prank.domain.utils.mainNavController

class WhistleFragment : BaseFragment<FragmentWhistleBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWhistleBinding {
        return FragmentWhistleBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        binding.icInfor.setOnClickListener {

            showWhistleInfoDialog(requireContext()) {

                mainNavController().navigate(
                    R.id.inforWhistleFragment
                )
            }
        }
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = WhistleFragment()
    }
}
