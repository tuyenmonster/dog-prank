package com.hlt.dog_prank.presentation.sounds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlt.dog_prank.databinding.ItemListSoundsBinding
import com.hlt.dog_prank.domain.model.SoundItem

class SoundsAdapter(
    private val onItemClick: (SoundItem) -> Unit
) : RecyclerView.Adapter<SoundsAdapter.SoundVH>() {

    private val items = mutableListOf<SoundItem>()

    fun submitList(data: List<SoundItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class SoundVH(
        private val binding: ItemListSoundsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SoundItem) {
            binding.imgAvatar.setImageResource(item.iconRes)
            binding.txtTitle.setText(item.titleRes)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundVH {
        val binding = ItemListSoundsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SoundVH(binding)
    }

    override fun onBindViewHolder(holder: SoundVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
