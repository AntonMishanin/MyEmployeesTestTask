package com.my.employees_domain.employees

import com.my.employees_domain.Specialty
import java.lang.NumberFormatException
import kotlin.text.StringBuilder

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
data class Employee(
    val id: String,
    val firstName: String,
    val lastName: String,
    val birthday: String,
    val avatarUrl: String,
    val specialties: List<Specialty>
) {

    fun copyWithCorrectName(): Employee {
        val firstName = startWithUpperCase(this.firstName)
        val lastName = startWithUpperCase(this.lastName)
        return if (
            this.firstName == firstName &&
            this.lastName == lastName
        ) {
            this
        } else {
            copy(firstName = firstName, lastName = lastName)
        }
    }

    private fun startWithUpperCase(input: String): String {
        val result = StringBuilder()

        for (i in input.indices) {
            val symbol = when (i == 0) {
                true -> input[i].uppercase()
                false -> input[i].lowercase()
            }
            result.append(symbol)
        }

        return result.toString()
    }

    fun copyWithCorrectBirthday(): Employee {

        if (birthday.isEmpty()) return this

        val dates = arrayOf(StringBuilder(), StringBuilder(), StringBuilder())
        var currentIndex = 0
        birthday.forEach { symbol ->
            if (symbol.isDigit()) {
                dates[currentIndex].append(symbol)
            } else {
                currentIndex++

                // Wrong birthday here
                if (currentIndex >= dates.size) return this
            }
        }

        return if (dates[0].length == YEAR_LENGTH) {
            handleDayAndMonth(dates[1].toString(), dates[2].toString(), year = dates[0].toString())
        } else if (dates[1].length == YEAR_LENGTH) {
            handleDayAndMonth(dates[0].toString(), dates[2].toString(), year = dates[1].toString())
        } else if (dates[2].length == YEAR_LENGTH) {
            handleDayAndMonth(dates[0].toString(), dates[1].toString(), year = dates[2].toString())
        } else {
            // Wrong birthday here
            this
        }
    }

    private fun handleDayAndMonth(first: String, second: String, year: String): Employee {
        val firstInt: Int
        val secondInt: Int
        try {
            firstInt = first.toInt()
            secondInt = second.toInt()
        } catch (e: NumberFormatException) {
            // Wrong birthday
            return this
        }

        return if (firstInt <= MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            copy(birthday = "$second.$first.$year")
        } else if (firstInt <= MONTHS_PER_YEAR && secondInt > MONTHS_PER_YEAR) {
            copy(birthday = "$second.$first.$year")
        } else if (firstInt > MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            copy(birthday = "$first.$second.$year")
        } else {
            // Wrong birthday
            this
        }
    }

    internal companion object {
        private const val YEAR_LENGTH = 4
        private const val MONTHS_PER_YEAR = 12

        fun List<Employee>.copyWithCorrectBirthday() = this.map { it.copyWithCorrectBirthday() }
    }
}