package com.my.employees_data.database.employees

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.my.employees.data.EmployeesDatabase
import com.my.employees_data.EmployeeDbo
import com.my.employees_data.EmployeesStorageDao
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@RunWith(AndroidJUnit4::class)
internal class EmployeesDaoTest {

    @Test
    fun test_save_should_read_the_same_size() {
        val database =
            com.my.employees.data.EmployeesDatabase(InstrumentationRegistry.getInstrumentation().context)
        database.writableDatabase.delete("Employees", null, null)
        val dao = EmployeesStorageDao(database)
        val data = listOf(
            employeeDbo("1"),
            employeeDbo("2")
        )

        dao.save(data)

        val expected = data.size
        val actual = dao.read().size
        Assert.assertEquals(expected, actual)
        database.close()
    }

    @Test
    fun test_save_the_same_objects_should_replace() {
        val database =
            com.my.employees.data.EmployeesDatabase(InstrumentationRegistry.getInstrumentation().context)
        database.writableDatabase.delete("Employees", null, null)
        val dao = EmployeesStorageDao(database)
        val data = listOf(
            employeeDbo("1"),
            employeeDbo("1"),
            employeeDbo("1")
        )

        dao.save(data)

        val expected = 1
        val actual = dao.read().size
        Assert.assertEquals(expected, actual)
        database.close()
    }

    private fun employeeDbo(text: String) = EmployeeDbo(
        id = "${text}dssds",
        firstName = "${text}sds",
        lastName = "${text}sds",
        birthday = "${text}sds",
        avatarUrl = "${text}sds",
        specialties = "${text}sds"
    )
}