package com.hlt.dog_prank.presentation.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlt.dog_prank.databinding.ItemSongBinding
import com.hlt.dog_prank.domain.model.SongItem

class SongsAdapter(
    private val onClick: (position: Int) -> Unit
) : RecyclerView.Adapter<SongsAdapter.VH>() {

    private val items = mutableListOf<SongItem>()

    fun submitList(data: List<SongItem>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class VH(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SongItem, position: Int) {
            binding.tvSongName.text = item.title

            binding.root.setOnClickListener { onClick(position) }

            // Star: UI thôi (nếu muốn lưu favorite thì mình làm tiếp)
            binding.icStar.setOnClickListener {
                // TODO: toggle favorite icon (optional)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}
