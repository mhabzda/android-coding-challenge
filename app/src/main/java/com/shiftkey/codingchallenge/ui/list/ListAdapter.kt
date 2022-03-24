package com.shiftkey.codingchallenge.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shiftkey.codingchallenge.databinding.ItemShiftBinding
import com.shiftkey.codingchallenge.model.entity.ShiftEntity

class ListAdapter : PagingDataAdapter<ShiftEntity, ListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShiftBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemShiftBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShiftEntity?) = with(binding) {
            item?.let {
                itemShiftTitle.text = item.specialty
                itemShiftSubtitle.text = item.startTime
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShiftEntity>() {
            override fun areItemsTheSame(oldItem: ShiftEntity, newItem: ShiftEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ShiftEntity, newItem: ShiftEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}
