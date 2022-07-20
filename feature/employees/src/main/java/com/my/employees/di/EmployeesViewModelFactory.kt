package com.my.employees.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.core.DispatchersWrapper
import com.my.core.Navigation
import com.my.employees.presentation.EmployeeUiConverter
import com.my.employees.presentation.EmployeesViewModel
import com.my.employees.domain.ObserveEmployeesUseCase

class EmployeesViewModelFactory(
    private val observeEmployeesUseCase: ObserveEmployeesUseCase,
    private val dispatchers: DispatchersWrapper,
    private val employeeUiConverter: EmployeeUiConverter,
    private val navigation: Navigation
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = EmployeesViewModel(
        observeEmployeesUseCase,
        dispatchers,
        employeeUiConverter,
        navigation
    ) as T
}