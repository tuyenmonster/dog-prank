package com.hlt.dog_prank.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentHomeBinding
import com.hlt.dog_prank.presentation.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val tabNavController by lazy {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.homeNavHost)
                    as NavHostFragment
        navHostFragment.navController
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        // ❌ KHÔNG navigate mặc định ở đây nữa

        binding.tabSounds.setOnClickListener {
            navigateSingleTop(R.id.soundsFragment)
        }

        binding.tabTranslate.setOnClickListener {
            navigateSingleTop(R.id.translateFragment)
        }

        binding.tabGames.setOnClickListener {
            navigateSingleTop(R.id.gamesFragment)
        }

        binding.tabSongs.setOnClickListener {
            navigateSingleTop(R.id.songsFragment)
        }

        binding.tabSettings.setOnClickListener {
            navigateSingleTop(R.id.settingsFragment)
        }

        observeTabChange()
    }

    /**
     * 🔥 Sync TAB UI theo destination hiện tại
     */
    private fun observeTabChange() {
        tabNavController.addOnDestinationChangedListener { _, destination, _ ->
            resetTabs()
            when (destination.id) {
                R.id.soundsFragment ->
                    setTabSelected(binding.icSounds, binding.txtSounds)

                R.id.translateFragment ->
                    setTabSelected(binding.icTranslate, binding.txtTranslate)

                R.id.gamesFragment ->
                    setTabSelected(binding.icGames, binding.txtGames)

                R.id.songsFragment ->
                    setTabSelected(binding.icSongs, binding.txtSongs)

                R.id.settingsFragment ->
                    setTabSelected(binding.icSettings, binding.txtSettings)
            }
        }
    }

    private fun navigateSingleTop(destinationId: Int) {
        val currentId = tabNavController.currentDestination?.id
        if (currentId == destinationId) return

        val navOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .setPopUpTo(
                tabNavController.graph.startDestinationId,
                inclusive = false,
                saveState = true
            )
            .build()

        tabNavController.navigate(destinationId, null, navOptions)
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
}


private enum class Tab {
    SOUNDS, TRANSLATE, GAMES, SONGS, SETTINGS
}
