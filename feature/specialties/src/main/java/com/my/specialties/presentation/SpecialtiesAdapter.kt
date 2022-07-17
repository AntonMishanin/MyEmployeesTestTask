package com.my.specialties.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.specialties.domain.Specialty

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class SpecialtiesAdapter(
    private val onItemClicked: (Specialty) -> Unit
) : ListAdapter<Specialty, RecyclerView.ViewHolder>(SpecialtiesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SpecialtyViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SpecialtyViewHolder) {
            holder.bind(currentList[position], onItemClicked)
        } else {
            throw IllegalArgumentException("Unknown viewHolder $holder")
        }
    }
}

class SpecialtiesDiffCallback : DiffUtil.ItemCallback<Specialty>() {

    override fun areItemsTheSame(
        oldItem: Specialty,
        newItem: Specialty
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Specialty,
        newItem: Specialty
    ) = oldItem == newItem
}