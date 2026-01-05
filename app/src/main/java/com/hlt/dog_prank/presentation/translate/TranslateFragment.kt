package com.hlt.dog_prank.presentation.translate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hlt.dog_prank.R
import com.hlt.dog_prank.databinding.FragmentTranslateBinding
import com.hlt.dog_prank.domain.utils.mainNavController
import com.hlt.dog_prank.presentation.BaseFragment

class TranslateFragment : BaseFragment<FragmentTranslateBinding>() {

    private var isDogLeft = true   // true = Dog đang được chọn

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTranslateBinding {
        return FragmentTranslateBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        // swap Dog <-> Cat
        binding.layoutPet.setOnClickListener {
            swapPetContent()
        }

        // 👉 CLICK CARD → ĐI SANG RECORD
        binding.cardHumanToDog.setOnClickListener {
            openRecordScreen()
        }
        binding.cardDogToHuman.setOnClickListener {
            openRecordDogScreen()
        }
    }

    private fun swapPetContent() {
        if (isDogLeft) {
            binding.img1.setImageResource(R.drawable.ic_cat)
            binding.txt1.text = "Cat"

            binding.img2.setImageResource(R.drawable.ic_dog)
            binding.txt2.text = "Dog"
        } else {
            binding.img1.setImageResource(R.drawable.ic_dog)
            binding.txt1.text = "Dog"

            binding.img2.setImageResource(R.drawable.ic_cat)
            binding.txt2.text = "Cat"
        }
        isDogLeft = !isDogLeft
    }

    private fun openRecordScreen() {
        val target = if (isDogLeft) "dog" else "cat"

        val bundle = Bundle().apply {
            putString(RecordHumanFragment.ARG_TARGET, target)
        }

        mainNavController().navigate(
            R.id.recordHumanFragment,
            bundle
        )
    }
    private fun openRecordDogScreen() {
        mainNavController().navigate(
            R.id.recordDogFragment
        )
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = TranslateFragment()
    }
}
