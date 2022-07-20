package com.my.employee_details.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.core.DispatchersWrapper
import com.my.core.Navigation
import com.my.employee_details.EmployeeDetailsConverter
import com.my.employee_details.EmployeeDetailsViewModel
import com.my.employee_details.domain.FetchEmployeeUseCase

class EmployeeDetailsViewModelFactory(
    private val fetchEmployeeUseCase: FetchEmployeeUseCase,
    private val dispatchers: DispatchersWrapper,
    private val employeeDetailsConverter: EmployeeDetailsConverter,
    private val navigation: Navigation
) : ViewModelProvider.Factory {

    private var id = ""

    fun setArguments(id: String) {
        this.id = id
    }

    override fun <T : ViewModel> create(modelClass: Class<T>) = EmployeeDetailsViewModel(
        fetchEmployeeUseCase,
        dispatchers,
        employeeDetailsConverter,
        id,
        navigation
    ) as T
}