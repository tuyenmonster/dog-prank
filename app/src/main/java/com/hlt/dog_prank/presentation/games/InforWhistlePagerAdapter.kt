package com.hlt.dog_prank.presentation.games

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class InforWhistlePagerAdapter(
    fragment: Fragment,
    private val stepCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = stepCount

    override fun createFragment(position: Int): Fragment {
        return WhistleStepFragment.newInstance(position)
    }
}
