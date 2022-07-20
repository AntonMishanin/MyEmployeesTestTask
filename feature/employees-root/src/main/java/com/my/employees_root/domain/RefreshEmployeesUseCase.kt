package com.my.employees_root.domain

import com.my.employees_root.data.EmployeesRootRepository

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
class RefreshEmployeesUseCase(
    private val employeesRootRepository: EmployeesRootRepository
) {

    suspend fun invoke() = employeesRootRepository.requestEmployees()
}