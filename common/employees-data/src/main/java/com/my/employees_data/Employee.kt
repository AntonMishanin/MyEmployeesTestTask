package com.my.employees_data

import java.lang.NumberFormatException
import java.util.*
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
    val specialties: List<String>,
    val age: String = EMPTY_AGE
) {

    fun copyWithCorrectInformation(): Employee {
        val firstName = startWithUpperCase(this.firstName)
        val lastName = startWithUpperCase(this.lastName)
        var birthday: String
        var age: String
        try {
            val splitDate = splitDate(this.birthday)
            birthday = correctDate(splitDate)
            age = age(splitDate)
        } catch (e: NumberFormatException) {
            birthday = EMPTY_BIRTHDAY
            age = EMPTY_AGE
        } catch (e: IllegalArgumentException) {
            birthday = EMPTY_BIRTHDAY
            age = EMPTY_AGE
        }
        return if (
            this.firstName == firstName &&
            this.lastName == lastName &&
            this.birthday == birthday &&
            this.age == age
        ) {
            this
        } else {
            copy(firstName = firstName, lastName = lastName, birthday = birthday, age = age)
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
            correctDate(splitDate(birthday))
        } catch (e: NumberFormatException) {
            EMPTY_BIRTHDAY
        } catch (e: IllegalArgumentException) {
            EMPTY_BIRTHDAY
        }
        return when (newBirthday == birthday) {
            true -> this
            false -> copy(birthday = newBirthday)
        }
    }

    private fun correctDate(input: Triple<String, String, String>) =
        "${input.first}.${input.second}.${input.third}"

    private fun age(input: Triple<String, String, String>): String {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val currentMonth =
            Calendar.getInstance().get(Calendar.MONTH) + 1//+1 because months became from 0
        val currentDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        return try {
            var years = currentYear - input.third.toInt()
            val months = currentMonth - input.second.toInt()
            val days = currentDayOfMonth - input.first.toInt()
            if (months < 0 || days < 0) {
                years -= 1
            }
            years.toString()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            EMPTY_AGE
        }
    }

    private fun splitDate(input: String): Triple<String, String, String> {
        val digits = digitsFromString(input)
        if (digits.size != 3) throw IllegalArgumentException("Unknown date")

        return if (digits[0].length == DIGITS_PER_YEAR) {
            detectDayMonthYear(digits[1], digits[2], year = digits[0])
        } else if (digits[1].length == DIGITS_PER_YEAR) {
            detectDayMonthYear(digits[0], digits[2], year = digits[1])
        } else if (digits[2].length == DIGITS_PER_YEAR) {
            detectDayMonthYear(digits[0], digits[1], year = digits[2])
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

    private fun detectDayMonthYear(
        first: String,
        second: String,
        year: String
    ): Triple<String, String, String> {
        val firstInt = first.toInt()
        val secondInt = second.toInt()
        Triple(second, first, year)
        return if (firstInt <= MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            Triple(second, first, year)
        } else if (firstInt <= MONTHS_PER_YEAR && secondInt > MONTHS_PER_YEAR) {
            Triple(second, first, year)
        } else if (firstInt > MONTHS_PER_YEAR && secondInt <= MONTHS_PER_YEAR) {
            Triple(first, second, year)
        } else {
            // Wrong date
            throw IllegalArgumentException("Unknown date")
        }
    }

    companion object {
        private const val DIGITS_PER_YEAR = 4
        private const val MONTHS_PER_YEAR = 12
        private const val EMPTY_AGE = "««"
        private const val EMPTY_BIRTHDAY = "««"

        fun List<Employee>.copyWithCorrectInformation() =
            this.map { it.copyWithCorrectInformation() }
    }
}