package com.hlt.dog_prank.presentation.sounds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hlt.dog_prank.R
import com.hlt.dog_prank.data.local.CatSoundData
import com.hlt.dog_prank.data.local.DogSoundData
import com.hlt.dog_prank.databinding.FragmentSoundsBinding
import com.hlt.dog_prank.domain.model.SoundItem
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.presentation.BaseFragment

class SoundsFragment : BaseFragment<FragmentSoundsBinding>() {

    private lateinit var soundsAdapter: SoundsAdapter
    private var isDogSelected = true

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSoundsBinding {
        return FragmentSoundsBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {
        setupRecyclerView()
        setupButtons()

        // default: Dog
        showDogSounds()
    }

    private fun setupRecyclerView() {
        soundsAdapter = SoundsAdapter { item ->
            playSound(item)
        }

        binding.rcvSounds.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = soundsAdapter
        }
    }

    private fun setupButtons() {
        binding.btnDog.setOnClickListener {
            if (!isDogSelected) {
                isDogSelected = true
                showDogSounds()
            }
        }

        binding.btnCat.setOnClickListener {
            if (isDogSelected) {
                isDogSelected = false
                showCatSounds()
            }
        }
    }

    private fun showDogSounds() {
        soundsAdapter.submitList(DogSoundData.list)
    }

    private fun showCatSounds() {
        soundsAdapter.submitList(CatSoundData.list)
    }

    private fun playSound(item: SoundItem) {
        val bundle = Bundle().apply {
            putInt("iconRes", item.iconRes)
            putInt("titleRes", item.titleRes)
            putInt("soundRes", item.soundRes)
        }

        mainNavController().navigate(
            R.id.playSoundsFragment,
            bundle
        )
    }


    override fun observeData() {
        // Không cần ViewModel cho màn này
    }

    companion object {
        fun newInstance() = SoundsFragment()
    }
}
