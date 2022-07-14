package com.my.specialties

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.employees_domain.Specialty
import com.my.specialties.databinding.ItemSpecialtyBinding

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

class SpecialtyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_specialty, parent, false)
) {

    private val binding = ItemSpecialtyBinding.bind(itemView)

    fun bind(specialty: Specialty, onItemClicked: (Specialty) -> Unit) = with(binding) {

        check.isChecked = specialty.isActive
        name.text = specialty.name

        root.setOnClickListener {
            onItemClicked.invoke(specialty)
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