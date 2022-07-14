package com.my.employees

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.core.DispatchersWrapper
import com.my.employees_domain.employees.ObserveEmployeesUseCase
import com.my.employees_domain.employees.RefreshEmployeesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class EmployeesViewModel(
    private val observeEmployeesUseCase: ObserveEmployeesUseCase,
    private val refreshEmployeesUseCase: RefreshEmployeesUseCase,
    private val dispatchers: DispatchersWrapper,
    private val converter: EmployeeUiConverter
) : ViewModel() {

    private val innerState = MutableStateFlow<List<EmployeeUi>>(emptyList())
    val state: StateFlow<List<EmployeeUi>> = innerState

    init {
        viewModelScope.launch(dispatchers.main()) {
            refreshEmployeesUseCase.invoke()

            observeEmployeesUseCase.invoke().map(converter::convert).collect {
                println("$it !!!!!!!!!!!!!!")
                innerState.value = it
            }
        }
    }

    fun refreshEmployees() {
        viewModelScope.launch(dispatchers.main()) {
            refreshEmployeesUseCase.invoke()
        }
    }
}