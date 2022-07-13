package com.my.employees_data

import com.my.employees_data.database.employees.EmployeeDbo
import com.my.employees_data.database.specialties.SpecialtyDbo
import com.my.employees_data.network.EmployeeDto
import com.my.employees_data.network.SpecialtyDto
import com.my.employees_domain.Specialty
import com.my.employees_domain.employees.Employee

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesConverter {

    fun convert(data: List<EmployeeDto>): Pair<List<EmployeeDbo>, List<SpecialtyDto>> {
        val specialties = HashSet<SpecialtyDto>()
        val result = mutableListOf<EmployeeDbo>()
        data.forEach { dto ->
            val employeeDbo = convert(dto, specialties)
            result.add(employeeDbo)
        }
        return Pair(result, specialties.toList())
    }

    private fun convert(data: EmployeeDto, specialties: HashSet<SpecialtyDto>) = EmployeeDbo(
        id = data.firstName + data.lastName + data.birthday,
        firstName = data.firstName ?: "",
        lastName = data.lastName ?: "",
        birthday = data.birthday ?: "",
        avatarUrl = data.avatarUrl ?: "",
        specialties = specialtiesToString(data.specialties ?: emptyList(), specialties)
    )

    // Convert list of specialties id to string
    private fun specialtiesToString(
        data: List<SpecialtyDto>,
        specialties: HashSet<SpecialtyDto>
    ): String {
        val result = StringBuffer()
        data.forEach {
            specialties.add(it)
            result.append("${it.id}$DELIMITERS")
        }
        return result.toString()
    }

    fun toDomain(input: List<EmployeeDbo>, specialties: List<Specialty>): List<Employee> {
        val specialtiesMap = specialties.associateBy({ it.id }, { it })
        val result = mutableListOf<Employee>()
        input.forEach {
            val employee = toDomain(it, specialtiesMap)
            result.add(employee)
        }
        return result
    }

    // TODO think about specialties logic
    fun toDomain(input: EmployeeDbo, specialties: Map<Int, Specialty>) = Employee(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        birthday = input.birthday,
        avatarUrl = input.avatarUrl,
        specialties = input.specialties.split(DELIMITERS).map { specialties[it.toInt()]!! }
    )

    private companion object {
        private const val DELIMITERS = " "
    }
}