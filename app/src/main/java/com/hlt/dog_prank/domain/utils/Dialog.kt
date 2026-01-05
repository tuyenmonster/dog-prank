package com.hlt.dog_prank.domain.utils

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.view.Window
import coil.load
import com.hlt.dog_prank.databinding.DialogClickerBinding
import com.hlt.dog_prank.databinding.DialogResultRecordDogBinding
import com.hlt.dog_prank.databinding.DialogWhistleBinding
import com.hlt.dog_prank.domain.model.TranslateContent

fun showWhistleInfoDialog(
    context: Context,
    onOpenInfor: () -> Unit
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    val binding = DialogWhistleBinding.inflate(dialog.layoutInflater)
    dialog.setContentView(binding.root)

    dialog.window?.apply {
        setBackgroundDrawableResource(android.R.color.transparent)
        setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    binding.btnClose.setOnClickListener {
        dialog.dismiss()
    }

    binding.btnInfor.setOnClickListener {
        dialog.dismiss()
        onOpenInfor.invoke()
    }

    dialog.show()
}
fun showClickerInfoDialog(
    context: Context,
    onLesson1: () -> Unit,
    onLesson2: () -> Unit
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    val binding = DialogClickerBinding.inflate(dialog.layoutInflater)
    dialog.setContentView(binding.root)

    dialog.window?.apply {
        setBackgroundDrawableResource(android.R.color.transparent)
        setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    binding.btnClose.setOnClickListener { dialog.dismiss() }

    binding.btnInfor.setOnClickListener {
        dialog.dismiss()
        onLesson1()
    }

    binding.btnInfor1.setOnClickListener {
        dialog.dismiss()
        onLesson2()
    }

    dialog.show()
}
fun showTranslateResultDialog(
    context: Context,
    content: TranslateContent,
    onComplete: () -> Unit = {}
) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

    val binding = DialogResultRecordDogBinding.inflate(dialog.layoutInflater)
    dialog.setContentView(binding.root)

    dialog.window?.apply {
        setBackgroundDrawableResource(android.R.color.transparent)
        setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    // TEXT
    binding.txtResult.text = context.getString(content.textRes)
    Log.d("showTranslateResultDialog", "showTranslateResultDialog: ${content.imageUrl}")
    binding.imgResult.load(content.imageUrl) {
        crossfade(true)
        crossfade(300)
        scale(coil.size.Scale.FILL)
    }

    binding.btnComplete.setOnClickListener {
        dialog.dismiss()
        onComplete()
    }

    dialog.show()
}