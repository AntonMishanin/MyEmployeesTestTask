package com.my.employees_data

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesConverter {

    fun convert(input: EmployeeDbo) = Employee(
        id = input.id,
        firstName = input.firstName,
        lastName = input.lastName,
        birthday = input.birthday,
        avatarUrl = input.avatarUrl,
        specialties = input.specialties.split(DELIMITERS).filter { it.isNotBlank() }
    )

    fun convert(input: List<EmployeeDbo>) = input.map { convert(it) }

    fun convert(
        firstName: String?,
        lastName: String?,
        birthday: String?,
        avatarUrl: String?,
        specialties: List<String>?
    ) = EmployeeDbo(
        id = firstName + lastName + birthday,
        firstName = firstName ?: "",
        lastName = lastName ?: "",
        birthday = birthday ?: "",
        avatarUrl = avatarUrl ?: "",
        specialties = specialtiesToString(specialties ?: emptyList())
    )

    private fun specialtiesToString(data: List<String>): String {
        val result = StringBuffer()
        data.forEach { result.append("${it}$DELIMITERS") }
        return result.toString()
    }

    private companion object {
        private const val DELIMITERS = " "
    }
}