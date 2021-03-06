package com.my.employees_root.data

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesRemoteDataSource(
    private val service: EmployeesService
) {

    suspend fun requestEmployees() = service.requestEmployees()
}