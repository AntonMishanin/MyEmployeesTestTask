package com.my.employees.data

import com.my.employees_data.Employee
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class EmployeesMemoryCache(
    private val flow: MutableStateFlow<List<Employee>> = MutableStateFlow(emptyList())
) {

     fun replace(data: List<Employee>) {
        flow.value = data
    }

    suspend fun add(data: List<Employee>) {
        val result = mutableListOf<Employee>()
        result.addAll(flow.value)
        result.addAll(data)
        flow.emit(result)
    }

    fun value() = flow.value

    fun observe() = flow
}