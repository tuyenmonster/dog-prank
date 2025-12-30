package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import com.hlt.dog_prank.databinding.FragmentClickerBinding
import com.hlt.dog_prank.databinding.PopupClickerMenuBinding
import com.hlt.dog_prank.presentation.BaseFragment

class ClickerFragment : BaseFragment<FragmentClickerBinding>() {

    private var popupWindow: PopupWindow? = null

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentClickerBinding {
        return FragmentClickerBinding.inflate(inflater, container, false)
    }

    override fun setupViews() {

        binding.menu.setOnClickListener {
            showMenu()
        }
    }

    private fun showMenu() {
        if (popupWindow?.isShowing == true) {
            popupWindow?.dismiss()
            return
        }

        val popupBinding = PopupClickerMenuBinding.inflate(layoutInflater)

        popupWindow = PopupWindow(
            popupBinding.root,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        ).apply {
            elevation = 20f
            isOutsideTouchable = true
        }

        popupBinding.item1.setOnClickListener {
            selectSound("Sound 1")
        }

        popupBinding.item2.setOnClickListener {
            selectSound("Sound 2")
        }

        popupBinding.item3.setOnClickListener {
            selectSound("Sound 3")
        }

        // show dưới menu
        popupWindow?.showAsDropDown(
            binding.menu,
            0,
            8
        )
    }

    private fun selectSound(title: String) {
        binding.txtTitle.text = title
        popupWindow?.dismiss()
    }

    override fun observeData() {}

    companion object {
        fun newInstance() = ClickerFragment()
    }
}
