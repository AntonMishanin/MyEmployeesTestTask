package com.my.employees_root.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.employees_root.EmployeesResult
import com.my.employees_root.domain.RefreshEmployeesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
class EmployeesRootViewModel(
    private val refreshEmployeesUseCase: RefreshEmployeesUseCase
) : ViewModel() {

    private val innerState = MutableStateFlow<EmployeesResult>(EmployeesResult.Progress)
    val state: StateFlow<EmployeesResult> = innerState

    init {
        viewModelScope.launch {
            innerState.value = refreshEmployeesUseCase.invoke()
        }
    }
}