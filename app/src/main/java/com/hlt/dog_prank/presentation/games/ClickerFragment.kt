package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.os.bundleOf
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentClickerBinding
import com.hlt.dog_prank.databinding.PopupClickerMenuBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.domain.utils.showClickerInfoDialog
import com.hlt.dog_prank.presentation.BaseFragment

class ClickerFragment : BaseFragment<FragmentClickerBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentClickerBinding {
        return FragmentClickerBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        binding.icBook.setOnClickListener {

            showClickerInfoDialog(
                requireContext(),

                // Lesson 1
                onLesson1 = {
                    mainNavController().navigate(
                        R.id.clickerInforFragment,
                        bundleOf("lesson" to 1)
                    )
                },

                // Lesson 2
                onLesson2 = {
                    mainNavController().navigate(
                        R.id.clickerInforFragment,
                        bundleOf("lesson" to 2)
                    )
                }
            )
        }
    }
}
