package com.my.employees_root.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.employees_root.domain.RefreshEmployeesUseCase
import com.my.employees_root.presentation.EmployeesRootViewModel

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
class EmployeesRootViewModelFactory(
    private val refreshEmployeesUseCase: RefreshEmployeesUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        EmployeesRootViewModel(refreshEmployeesUseCase) as T
}