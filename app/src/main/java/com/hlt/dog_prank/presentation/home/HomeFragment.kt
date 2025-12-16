package com.hlt.dog_prank.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentHomeBinding
import com.hlt.dog_prank.presentation.BaseFragment
import com.hlt.dog_prank.presentation.games.GamesFragment
import com.hlt.dog_prank.presentation.sounds.SoundsFragment
import com.hlt.dog_prank.presentation.songs.SongsFragment
import com.hlt.dog_prank.presentation.translate.TranslateFragment
import com.hlt.dog_prank.presentation.settings.SettingsFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        // tab mặc định
        selectTab(Tab.SOUNDS)

        binding.tabSounds.setOnClickListener {
            selectTab(Tab.SOUNDS)
        }

        binding.tabTranslate.setOnClickListener {
            selectTab(Tab.TRANSLATE)
        }

        binding.tabGames.setOnClickListener {
            selectTab(Tab.GAMES)
        }

        binding.tabSongs.setOnClickListener {
            selectTab(Tab.SONGS)
        }

        binding.tabSettings.setOnClickListener {
            selectTab(Tab.SETTINGS)
        }
    }

    private fun selectTab(tab: Tab) {
        resetTabs()

        when (tab) {
            Tab.SOUNDS -> {
                setTabSelected(binding.icSounds, binding.txtSounds)
                openFragment(SoundsFragment.newInstance())
            }

            Tab.TRANSLATE -> {
                setTabSelected(binding.icTranslate, binding.txtTranslate)
                openFragment(TranslateFragment.newInstance())
            }

            Tab.GAMES -> {
                setTabSelected(binding.icGames, binding.txtGames)
                openFragment(GamesFragment.newInstance())
            }

            Tab.SONGS -> {
                setTabSelected(binding.icSongs, binding.txtSongs)
                openFragment(SongsFragment.newInstance())
            }

            Tab.SETTINGS -> {
                setTabSelected(binding.icSettings, binding.txtSettings)
                openFragment(SettingsFragment.newInstance())
            }
        }
    }

    private fun resetTabs() {
        setTabUnSelected(binding.icSounds, binding.txtSounds)
        setTabUnSelected(binding.icTranslate, binding.txtTranslate)
        setTabUnSelected(binding.icGames, binding.txtGames)
        setTabUnSelected(binding.icSongs, binding.txtSongs)
        setTabUnSelected(binding.icSettings, binding.txtSettings)
    }

    private fun setTabSelected(icon: View, text: View) {
        val color = ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected)
        (icon as? android.widget.ImageView)?.setColorFilter(color)
        (text as? android.widget.TextView)?.setTextColor(color)
    }

    private fun setTabUnSelected(icon: View, text: View) {
        val color = ContextCompat.getColor(requireContext(), R.color.bottom_nav_unselected)
        (icon as? android.widget.ImageView)?.setColorFilter(color)
        (text as? android.widget.TextView)?.setTextColor(color)
    }

    private fun openFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(binding.homeContainer.id, fragment)
            .commit()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}

/**
 * Enum quản lý tab
 */
private enum class Tab {
    SOUNDS, TRANSLATE, GAMES, SONGS, SETTINGS
}
