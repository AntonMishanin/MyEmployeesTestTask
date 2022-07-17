package com.my.employees_data

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
//TODO: think about split interface
interface EmployeesStorage {

    fun setCallback(callback: (List<EmployeeDbo>) -> Unit)

    fun removeCallback()

    fun read(): List<EmployeeDbo>

    fun read(id: String): EmployeeDbo

    suspend fun saveEmployees(employees: List<EmployeeDbo>)

    suspend fun employeesNumber(): Int
}