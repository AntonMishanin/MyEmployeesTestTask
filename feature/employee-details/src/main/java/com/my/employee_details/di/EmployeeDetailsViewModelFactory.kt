package com.my.employee_details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.core.DispatchersWrapper
import com.my.employee_details.EmployeeDetailsConverter
import com.my.employee_details.EmployeeDetailsViewModel
import com.my.employees_domain.employees.FetchEmployeeUseCase

class EmployeeDetailsViewModelFactory(
    private val fetchEmployeeUseCase: FetchEmployeeUseCase,
    private val dispatchers: DispatchersWrapper,
    private val employeeDetailsConverter: EmployeeDetailsConverter
) : ViewModelProvider.Factory {

    private var id = ""

    fun setArguments(id: String){
        this.id = id
    }

    override fun <T : ViewModel> create(modelClass: Class<T>) = EmployeeDetailsViewModel(
        fetchEmployeeUseCase,
        dispatchers,
        employeeDetailsConverter,
        id
    ) as T
}