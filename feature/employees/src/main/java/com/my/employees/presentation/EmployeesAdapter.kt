package com.my.employees.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.my.employees.R
import com.my.employees.databinding.ItemEmployeeBinding
import com.my.employees.databinding.ItemErrorBinding

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeesAdapter(
    private val onItemClicked: (EmployeeUi) -> Unit,
    private val onRefreshClicked: () -> Unit
) : ListAdapter<EmployeeUi, RecyclerView.ViewHolder>(EmployeesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_employee -> EmployeesViewHolder(parent)
            R.layout.item_progress -> ProgressViewHolder(parent)
            R.layout.item_error -> ErrorViewHolder(parent, onRefreshClicked)
            else -> throw IllegalArgumentException("Unknown viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is EmployeeUi.Content -> R.layout.item_employee
        is EmployeeUi.Progress -> R.layout.item_progress
        is EmployeeUi.Error -> R.layout.item_error
        else -> throw IllegalArgumentException("Unknown type ${currentList[position]}")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val value = currentList[position]
        if (holder is EmployeesViewHolder && value is EmployeeUi.Content) {
            holder.bind(value, onItemClicked)
        }
    }
}

class EmployeesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
) {

    private val binding = ItemEmployeeBinding.bind(itemView)

    fun bind(employee: EmployeeUi.Content, onItemClicked: (EmployeeUi) -> Unit) = with(binding) {

        nameValue.text = employee.name
        ageValue.text = employee.age
        Glide.with(itemView)
            .load(employee.avatarUrl)
            .placeholder(R.drawable.avatar_placeholder)
            .error(R.drawable.avatar_placeholder)
            .into(binding.avatar)

        root.setOnClickListener {
            onItemClicked.invoke(employee)
        }
    }
}

class ProgressViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_progress, parent, false)
)

class ErrorViewHolder(parent: ViewGroup, onRefreshClicked: () -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
) {

    private val binding = ItemErrorBinding.bind(itemView)

    init {
        binding.refresh.setOnClickListener {
            onRefreshClicked.invoke()
        }
    }
}

class EmployeesDiffCallback : DiffUtil.ItemCallback<EmployeeUi>() {

    override fun areItemsTheSame(
        oldItem: EmployeeUi,
        newItem: EmployeeUi
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: EmployeeUi,
        newItem: EmployeeUi
    ) = oldItem.match(newItem)
}