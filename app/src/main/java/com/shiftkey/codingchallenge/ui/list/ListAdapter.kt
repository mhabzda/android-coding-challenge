package com.shiftkey.codingchallenge.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ItemShiftBinding
import com.shiftkey.codingchallenge.model.entity.ShiftEntity

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var list: List<ShiftEntity> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(
        private val binding: ItemShiftBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShiftEntity) = with(binding) {
            itemShiftTitle.text = item.specialty
            itemShiftSubtitle.text = item.startTime
        }
    }
}
