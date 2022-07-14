package com.my.employees

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */

interface EmployeeUi {

    val id: String

    fun match(data: EmployeeUi): Boolean

    data class Content(
        override val id: String,
        val firstName: String,
        val lastName: String,
        val age: String
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }

    data class Progress(
        override val id: String = "Progress"
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }

    data class Error(
        override val id: String = "Error",
        val message: String
    ) : EmployeeUi {

        override fun match(data: EmployeeUi) = this == data
    }
}