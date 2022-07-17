package com.my.employees.data

import com.my.core.DispatchersWrapper
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import com.my.employees_data.Employee
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesRepositoryImpl(
    private val employeesStorage: EmployeesStorage,
    private val employeesConverter: EmployeesConverter,
    private val dispatchers: DispatchersWrapper,
    private val employeesMemoryCache: EmployeesMemoryCache
) : com.my.employees.domain.EmployeesRepository {

    init {
        employeesStorage.setCallback { employeesDbo ->
            val employeesDomain = employeesConverter.convert(employeesDbo)
            employeesMemoryCache.replace(employeesDomain)
        }
    }

    override suspend fun fetchEmployees() = employeesMemoryCache.value()

    override suspend fun observeEmployees() = employeesMemoryCache.observe().onEach {
        if (it.isEmpty()) {
            val employeesDomain = employeesConverter.convert(employeesStorage.read())
            employeesMemoryCache.replace(employeesDomain)
        }
    }

    override suspend fun fetchEmployee(id: String): Employee =
        employeesMemoryCache.value().find { it.id == id }
            ?: throw IllegalArgumentException("Unknown id $id")
}