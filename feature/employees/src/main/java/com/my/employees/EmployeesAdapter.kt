package com.my.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.my.employees.databinding.ItemEmployeeBinding

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeesAdapter(
    private val onItemClicked: (EmployeeUi) -> Unit
) : ListAdapter<EmployeeUi, RecyclerView.ViewHolder>(EmployeesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EmployeesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EmployeesViewHolder) {
            holder.bind(currentList[position], onItemClicked)
        } else {
            throw IllegalArgumentException("Unknown viewHolder $holder")
        }
    }
}

class EmployeesViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
) {

    private val binding = ItemEmployeeBinding.bind(itemView)

    fun bind(employee: EmployeeUi, onItemClicked: (EmployeeUi) -> Unit) = with(binding) {

        nameValue.text = employee.firstName
        surnameValue.text = employee.lastName
        ageValue.text = employee.age

        root.setOnClickListener {
            onItemClicked.invoke(employee)
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
    ) = oldItem == newItem
}