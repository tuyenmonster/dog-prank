package com.hlt.dog_prank.presentation.games

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class GamesPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WhistleFragment()
            1 -> ClickerFragment()
            2 -> ChallengeFragment()
            else -> WhistleFragment()
        }
    }
}
