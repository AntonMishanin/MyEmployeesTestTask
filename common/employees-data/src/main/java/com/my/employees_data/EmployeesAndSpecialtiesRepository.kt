package com.my.employees_data

import com.my.employees_data.network.EmployeesRemoteDataSource

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesAndSpecialtiesRepository(
    private val remoteDataSource: EmployeesRemoteDataSource
) {

    suspend fun fetchFreshEmployees() = remoteDataSource.requestEmployees()
}