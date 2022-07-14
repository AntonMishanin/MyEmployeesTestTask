package com.my.employees.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.core.DispatchersWrapper
import com.my.employees.EmployeeUiConverter
import com.my.employees.EmployeesViewModel
import com.my.employees_domain.employees.ObserveEmployeesUseCase
import com.my.employees_domain.employees.RefreshEmployeesUseCase

class EmployeesViewModelFactory(
    private val observeEmployeesUseCase: ObserveEmployeesUseCase,
    private val refreshEmployeesUseCase: RefreshEmployeesUseCase,
    private val dispatchers: DispatchersWrapper,
    private val employeeUiConverter: EmployeeUiConverter
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = EmployeesViewModel(
        observeEmployeesUseCase,
        refreshEmployeesUseCase,
        dispatchers,
        employeeUiConverter
    ) as T
}