package com.my.employee_details.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class FetchEmployeeUseCase(
    private val employeeRepository: EmployeeRepository
) {

    suspend fun invoke(id: String) =
        employeeRepository.fetchEmployee(id).copyWithCorrectInformation()
}