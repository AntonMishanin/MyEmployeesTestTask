package com.my.employees_domain.employees

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class RefreshEmployeesUseCase(
    private val employeesRepository: EmployeesRepository
) {

    suspend fun invoke(){
        employeesRepository.refreshEmployees()
    }
}