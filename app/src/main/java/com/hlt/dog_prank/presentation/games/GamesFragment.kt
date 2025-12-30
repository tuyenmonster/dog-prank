package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentGamesBinding
import com.hlt.dog_prank.presentation.BaseFragment

class GamesFragment : BaseFragment<FragmentGamesBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGamesBinding {
        return FragmentGamesBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        setupViewPager()
        setupButtons()

        // default tab
        selectTab(0)
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = GamesPagerAdapter(this)
        binding.viewPager.offscreenPageLimit = 3

        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    selectTab(position)
                }
            }
        )
    }

    private fun setupButtons() {
        binding.btnWhistle.setOnClickListener {
            binding.viewPager.currentItem = 0
        }

        binding.btnClicker.setOnClickListener {
            binding.viewPager.currentItem = 1
        }

        binding.btnChallenge.setOnClickListener {
            binding.viewPager.currentItem = 2
        }
    }

    private fun selectTab(index: Int) {
        val selectedBg = R.drawable.bg_btn_select
        val unselectedBg = R.drawable.bg_btn_unselect

        val selectedText = ContextCompat.getColor(requireContext(), android.R.color.white)
        val unselectedText = ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected)

        binding.btnWhistle.apply {
            background = ContextCompat.getDrawable(requireContext(),
                if (index == 0) selectedBg else unselectedBg
            )
            setTextColor(if (index == 0) selectedText else unselectedText)
        }

        binding.btnClicker.apply {
            background = ContextCompat.getDrawable(requireContext(),
                if (index == 1) selectedBg else unselectedBg
            )
            setTextColor(if (index == 1) selectedText else unselectedText)
        }

        binding.btnChallenge.apply {
            background = ContextCompat.getDrawable(requireContext(),
                if (index == 2) selectedBg else unselectedBg
            )
            setTextColor(if (index == 2) selectedText else unselectedText)
        }
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = GamesFragment()
    }
}
