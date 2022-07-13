package com.my.employees_domain.employees

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class FetchEmployeeUseCase(
    private val employeesRepository: EmployeesRepository
) {

    suspend fun invoke(id: String) = employeesRepository.fetchEmployee(id).copyWithCorrectBirthday()
}