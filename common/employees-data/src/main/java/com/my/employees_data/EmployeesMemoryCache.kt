package com.my.employees_data

import com.my.employees_domain.EmployeeResult
import com.my.employees_domain.FilterParams
import com.my.employees_domain.Specialty
import com.my.employees_domain.employees.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import java.lang.Exception

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
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

class EmployeesResultMemoryCache(
    private val flow: MutableStateFlow<EmployeeResult> = MutableStateFlow(EmployeeResult())
) {

    suspend fun replace(data: List<Specialty>) {
        val set = HashSet<String>()
        data.forEach { specialty ->
            if (specialty.isActive) {
                set.add(specialty.id.toString())
            }
        }
        val result = flow.value.copy(filterParams = FilterParams(specialtiesId = set))
        flow.emit(result)
    }

    suspend fun employees(data: List<Employee>) {
        if (data.isEmpty()) return

        val result = flow.value.copy(employees = data, exception = null)
        flow.emit(result)
    }

    suspend fun sendException(exception: Exception) {
        val result = flow.value.copy(exception = exception)
        flow.emit(result)
    }

    fun fetchEmployees() = flow.value.employees

    fun flow() = flow
}