package com.hlt.dog_prank.presentation.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hlt.dog_prank.data.local.WhistleStepProvider
import com.hlt.dog_prank.databinding.FragmentWhistleStepBinding
import com.hlt.dog_prank.presentation.BaseFragment

class WhistleStepFragment : BaseFragment<FragmentWhistleStepBinding>() {

    private var stepIndex: Int = 0

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWhistleStepBinding {
        return FragmentWhistleStepBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        stepIndex = arguments?.getInt(ARG_STEP) ?: 0

        val steps = WhistleStepProvider.getSteps(requireContext())
        val stepData = steps[stepIndex]

        // bind data
        binding.txtTitle.text = stepData.title
        binding.txtDescription.text = stepData.description
        binding.txtStep.text = "Step ${stepIndex + 1}"

        binding.imgIcon.setImageResource(stepData.iconRes)
        binding.imgDot.setImageResource(stepData.dotActiveRes)

        binding.btnNext.setOnClickListener {
            (parentFragment as? InforWhistleFragment)?.nextStep()
        }
    }

    companion object {
        private const val ARG_STEP = "arg_step"

        fun newInstance(step: Int): WhistleStepFragment {
            return WhistleStepFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_STEP, step)
                }
            }
        }
    }
}
