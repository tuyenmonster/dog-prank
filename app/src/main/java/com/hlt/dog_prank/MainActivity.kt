package com.hlt.dog_prank

import android.os.Bundle
import com.hlt.dog_prank.databinding.ActivityMainBinding
import com.hlt.dog_prank.presentation.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setupViews() {
        // Không cần làm gì thêm
        // NavHostFragment tự load HomeFragment qua nav_graph
    }
}
