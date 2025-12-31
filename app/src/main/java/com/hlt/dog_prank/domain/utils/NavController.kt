package com.hlt.dog_prank.domain.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.hlt.dog_prank.R

fun Fragment.mainNavController(): NavController {
    return requireActivity().findNavController(R.id.navHostFragment)
}
