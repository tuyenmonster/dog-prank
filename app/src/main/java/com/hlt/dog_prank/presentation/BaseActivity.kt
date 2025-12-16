package com.hlt.dog_prank.presentation

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge


abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private var _binding: T? = null

    val binding get() = _binding!!
    private var noInternetDialog: Dialog? = null
//    private var networkMonitor: NetworkMonitor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        _binding = inflateBinding()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // ---------------------------------------------------------
//        initNetworkMonitor()
        setupViews()
    }
//    private fun initNetworkMonitor() {
//        if (!isInternetAvailable(this)) {
//            if (noInternetDialog == null) {
//                noInternetDialog = showNoInternetDialog(this)
//            }
//        }
//        networkMonitor = NetworkMonitor(
//            this,
//            onAvailable = {
//                runOnUiThread {
//                    (application as? MyApplication)?.initData()
//                    noInternetDialog?.dismiss()
//                    noInternetDialog = null
//                }
//            },
//            onLost = {
//                runOnUiThread {
//                    if (noInternetDialog == null) {
//                        noInternetDialog = showNoInternetDialog(this)
//                    }
//                }
//            }
//        )
//        networkMonitor?.start()
//    }

//    override fun attachBaseContext(newBase: Context?) {
//        if (newBase == null) {
//            super.attachBaseContext(null)
//            return
//        }
//
//        val lang = LanguageUtils.getSavedLanguage(newBase)?:"en"
//        val updatedContext = LanguageUtils.applyLanguage(newBase, lang)
//        super.attachBaseContext(updatedContext)
//    }

    abstract fun inflateBinding(): T

    abstract fun setupViews()

    override fun onDestroy() {
        super.onDestroy()
        noInternetDialog?.dismiss()
        noInternetDialog = null
//        networkMonitor?.stop()
        _binding = null
    }
}