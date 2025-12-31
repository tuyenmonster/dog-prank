package com.hlt.dog_prank.presentation.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hlt.dog_prank.databinding.ItemChallengeBinding
import com.hlt.dog_prank.domain.model.ChallengeItem

class ChallengeAdapter(
    private val items: List<ChallengeItem>,
    private val onItemClick: (ChallengeItem) -> Unit
) : RecyclerView.Adapter<ChallengeAdapter.VH>() {

    inner class VH(val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ItemChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.binding.txtTitle.text = item.title

        holder.binding.root.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
