package com.my.employees_data

import com.my.employees_domain.Specialty
import com.my.employees_domain.employees.Employee
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class EmployeesMemoryCache(
    private val flow: MutableStateFlow<List<Employee>> = MutableStateFlow(emptyList())
) {

    suspend fun replace(data: List<Employee>) {
        flow.emit(data)
    }

    suspend fun add(data: List<Employee>) {
        val result = mutableListOf<Employee>()
        result.addAll(flow.value)
        result.addAll(data)
        flow.emit(result)
    }

    fun flow() = flow
}

class SpecialtiesMemoryCache(
    private val flow: MutableStateFlow<List<Specialty>> = MutableStateFlow(emptyList())
) {

    suspend fun replace(data: List<Specialty>) {
        flow.emit(data)
    }

    suspend fun add(data: List<Specialty>) {
        val result = mutableListOf<Specialty>()
        result.addAll(flow.value)
        result.addAll(data)
        flow.emit(result)
    }

    fun flow() = flow
}