package com.my.employees.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.my.core.ViewBindingFragment
import com.my.employees.databinding.FragmentEmployeesBinding
import com.my.employees.di.EmployeesViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class EmployeesFragment(
    private val employeesViewModelFactory: EmployeesViewModelFactory
) : ViewBindingFragment<FragmentEmployeesBinding>(
    FragmentEmployeesBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<EmployeesViewModel> { employeesViewModelFactory }
        val adapter = EmployeesAdapter(
            onItemClicked = viewModel::onEmployeeClicked,
            onRefreshClicked = viewModel::onRefreshClicked
        )
        binding.list.adapter = adapter

        viewModel.state.onEach(adapter::submitList).launchIn(lifecycleScope)
    }
}