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

    fun copyWithCorrectInformation(): Employee {
        val firstName = startWithUpperCase(this.firstName)
        val lastName = startWithUpperCase(this.lastName)
        val birthday = correctDate(this.birthday)
        return if (
            this.firstName == firstName &&
            this.lastName == lastName &&
            this.birthday == birthday
        ) {
            this
        } else {
            copy(firstName = firstName, lastName = lastName, birthday = birthday)
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
        val newBirthday = try {
            correctDate(birthday)
        } catch (e: NumberFormatException) {
            birthday
        } catch (e: IllegalArgumentException) {
            birthday
        }
        return when (newBirthday == birthday) {
            true -> this
            false -> copy(birthday = newBirthday)
        }
    }

    private fun correctDate(input: String): String {
        val digits = digitsFromString(input)
        if (digits.size != 3) return input

        return if (digits[0].length == DIGITS_PER_YEAR) {
            combineDate(digits[1], digits[2], year = digits[0])
        } else if (digits[1].length == DIGITS_PER_YEAR) {
            combineDate(digits[0], digits[2], year = digits[1])
        } else if (digits[2].length == DIGITS_PER_YEAR) {
            combineDate(digits[0], digits[1], year = digits[2])
        } else {
            // Wrong birthday here
            throw IllegalArgumentException("Unknown date")
        }
    }

    private fun digitsFromString(input: String): List<String> {
        val result = mutableListOf<StringBuilder>()
        result.add(StringBuilder())

        var currentIndex = 0
        input.forEach { symbol ->
            if (symbol.isDigit()) {
                result[currentIndex].append(symbol)
            } else {
                currentIndex++
                result.add(StringBuilder())
            }
        }
        return result.map { it.toString() }
    }

    private fun combineDate(first: String, second: String, year: String): String {
        val firstInt = first.toInt()
        val secondInt = second.toInt()

        return if (firstInt <= MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            "$second.$first.$year"
        } else if (firstInt <= MONTHS_PER_YEAR && secondInt > MONTHS_PER_YEAR) {
            "$second.$first.$year"
        } else if (firstInt > MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            "$first.$second.$year"
        } else {
            // Wrong date
            throw IllegalArgumentException("Unknown date")
        }
    }

    internal companion object {
        private const val DIGITS_PER_YEAR = 4
        private const val MONTHS_PER_YEAR = 12

        fun List<Employee>.copyWithCorrectInformation() =
            this.map { it.copyWithCorrectInformation() }
    }
}