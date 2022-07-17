package com.my.specialties.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.my.specialties.R
import com.my.specialties.databinding.ItemSpecialtyBinding
import com.my.specialties.domain.Specialty

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