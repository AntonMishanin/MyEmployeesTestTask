package com.my.employees_data.database.specialties

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.my.employees.data.EmployeesDatabase
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@RunWith(AndroidJUnit4::class)
internal class SpecialtiesDaoTest {

    @Test
    fun test_save_should_read_the_same_size() {
        val database =
            com.my.employees.data.EmployeesDatabase(InstrumentationRegistry.getInstrumentation().context)
        database.writableDatabase.delete("Specialties", null, null)
        val dao = com.my.specialties.data.SpecialtiesDao(database)
        val data = listOf(
            specialtyDbo(1),
            specialtyDbo(2)
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
        database.writableDatabase.delete("Specialties", null, null)
        val dao = com.my.specialties.data.SpecialtiesDao(database)
        val data = listOf(
            specialtyDbo(1),
            specialtyDbo(1),
            specialtyDbo(1)
        )

        dao.save(data)

        val expected = 1
        val actual = dao.read().size
        Assert.assertEquals(expected, actual)
        database.close()
    }

    private fun specialtyDbo(id: Int) = com.my.specialties.data.SpecialtyDbo(
        id = id,
        name = "${id}sds"
    )
}