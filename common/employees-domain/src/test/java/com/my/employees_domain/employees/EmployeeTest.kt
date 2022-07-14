package com.my.employees_domain.employees

import org.junit.Assert
import org.junit.Test

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class EmployeeTest {

    @Test
    fun `test the birthday is empty should return placeholder`() {
        val employee = employee(birthDay = "")

        val actual = employee.copyWithCorrectBirthday().birthday
        val expected = "««"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the year in the start the birthday should be correct`() {
        val employee = employee(birthDay = "1987-03-23")

        val actual = employee.copyWithCorrectBirthday().birthday
        val expected = "23.03.1987"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the year in the middle the birthday should be correct`() {
        val employee = employee(birthDay = "03-1987-23")

        val actual = employee.copyWithCorrectBirthday().birthday
        val expected = "23.03.1987"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the year in the end the birthday should be correct`() {
        val employee = employee(birthDay = "03-23-1987")

        val actual = employee.copyWithCorrectBirthday().birthday
        val expected = "23.03.1987"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the month in the middle the birthday should be correct`() {
        val employee = employee(birthDay = "23-03-1987")

        val actual = employee.copyWithCorrectBirthday().birthday
        val expected = "23.03.1987"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the wrong birthday should return placeholder`() {
        var actual = employee(birthDay = "2t-03-1987").copyWithCorrectBirthday().birthday
        var expected = "««"
        Assert.assertEquals(expected, actual)

        actual = employee(birthDay = "21-03-558y").copyWithCorrectBirthday().birthday
        expected = "««"
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `test the name starts from lower case should return be correct`() {
        val employee = employee(firstName = "иВан", lastName = "ИваноВ").copyWithCorrectInformation()
        val actualFirst = employee.firstName
        val expectedFirst = "Иван"
        Assert.assertEquals(expectedFirst, actualFirst)

        val actualLast = employee.lastName
        val expectedLast = "Иванов"
        Assert.assertEquals(expectedLast, actualLast)
    }

    private fun employee(birthDay: String) = Employee(
        id = "",
        firstName = "",
        lastName = "",
        birthday = birthDay,
        avatarUrl = "",
        specialties = emptyList()
    )

    private fun employee(firstName: String, lastName: String) = Employee(
        id = "",
        firstName = firstName,
        lastName = lastName,
        birthday = "",
        avatarUrl = "",
        specialties = emptyList()
    )
}