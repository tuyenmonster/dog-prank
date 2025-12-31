package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.data.local.WhistleStepProvider
import com.hlt.dog_prank.databinding.FragmentInforWhistleBinding
import com.hlt.dog_prank.presentation.BaseFragment

class InforWhistleFragment : BaseFragment<FragmentInforWhistleBinding>() {

    private lateinit var pagerAdapter: InforWhistlePagerAdapter

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInforWhistleBinding {
        return FragmentInforWhistleBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        val stepCount = WhistleStepProvider.getSteps(requireContext()).size

        pagerAdapter = InforWhistlePagerAdapter(this, stepCount)

        binding.viewPager.apply {
            adapter = pagerAdapter
            isUserInputEnabled = false
            offscreenPageLimit = stepCount
        }

        binding.btnClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    fun nextStep() {
        val current = binding.viewPager.currentItem
        if (current < pagerAdapter.itemCount - 1) {
            binding.viewPager.currentItem = current + 1
        } else {
            parentFragmentManager.popBackStack()
        }
    }
}
