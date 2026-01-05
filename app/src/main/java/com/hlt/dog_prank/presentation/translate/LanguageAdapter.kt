package com.hlt.dog_prank.presentation.translate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlt.dog_prank.databinding.ItemLanguageBinding

class LanguageAdapter(
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageVH>() {

    private val items = mutableListOf<String>()

    fun submitList(list: List<String>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class LanguageVH(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(language: String) {
            binding.txtLanguage.text = language
            binding.root.setOnClickListener { onClick(language) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageVH {
        val binding = ItemLanguageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LanguageVH(binding)
    }

    override fun onBindViewHolder(holder: LanguageVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
