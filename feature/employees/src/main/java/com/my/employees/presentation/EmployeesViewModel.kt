package com.my.employees.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.core.DispatchersWrapper
import com.my.employees.domain.ObserveEmployeesUseCase
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
    private val dispatchers: DispatchersWrapper,
    private val converter: EmployeeUiConverter
) : ViewModel() {

    private val innerState = MutableStateFlow<List<EmployeeUi>>(converter.progress())
    val state: StateFlow<List<EmployeeUi>> = innerState

    init {
        viewModelScope.launch(dispatchers.main()) {

            observeEmployeesUseCase.invoke().map(converter::convert).collect {
                innerState.emit(it.toList())
            }
        }
    }

    fun refreshEmployees() = Unit
}