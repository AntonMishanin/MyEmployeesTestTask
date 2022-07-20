package com.my.employee_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.core.DispatchersWrapper
import com.my.core.Navigation
import com.my.core.NavigationDestination
import com.my.employee_details.domain.FetchEmployeeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class EmployeeDetailsViewModel(
    fetchEmployeeUseCase: FetchEmployeeUseCase,
    dispatchers: DispatchersWrapper,
    employeeDetailsConverter: EmployeeDetailsConverter,
    id: String,
    private val navigation: Navigation
) : ViewModel() {

    private val innerState = MutableStateFlow(EmployeeDetails.empty())
    val state: StateFlow<EmployeeDetails> = innerState

    init {
        viewModelScope.launch(dispatchers.main()) {
            val employeeDomain = fetchEmployeeUseCase.invoke(id)
            innerState.value = employeeDetailsConverter.convert(employeeDomain)
        }
    }

    fun onBackClicked() = navigation.navigate(NavigationDestination.Back)
}