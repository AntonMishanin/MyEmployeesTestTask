package com.my.employees_data.database.employees

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import com.my.employees_data.database.EmployeesDatabase

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesDao(
    private val database: EmployeesDatabase
) {

    fun save(data: List<EmployeeDbo>) {
        data.forEach(::save)
    }

    private fun save(employeeDbo: EmployeeDbo) {
        val values = ContentValues().apply {
            put(BaseColumns._ID, employeeDbo.id)
            put(COLUMN_NAME_FIRST_NAME, employeeDbo.firstName)
            put(COLUMN_NAME_LAST_NAME, employeeDbo.lastName)
            put(COLUMN_NAME_BIRTHDAY, employeeDbo.birthday)
            put(COLUMN_NAME_AVATAR_URL, employeeDbo.avatarUrl)
            put(COLUMN_NAME_SPECIALTIES, employeeDbo.specialties)
        }

        database.writableDatabase?.insertWithOnConflict(
            TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    fun read(): List<EmployeeDbo> {
        val cursor = database.readableDatabase.rawQuery("SELECT  * FROM $TABLE_NAME", null)

        val employees = mutableListOf<EmployeeDbo>()
        while (cursor.moveToNext()) {
            val employee = EmployeeDbo(
                id = cursor.getString(0),
                firstName = cursor.getString(1),
                lastName = cursor.getString(2),
                birthday = cursor.getString(3),
                avatarUrl = cursor.getString(4),
                specialties = cursor.getString(5)
            )
            employees.add(employee)
        }
        cursor.close()
        return employees
    }

    protected fun finalize() = database.close()

    internal companion object {
        private const val TABLE_NAME = "Employees"
        private const val COLUMN_NAME_FIRST_NAME = "firstName"
        private const val COLUMN_NAME_LAST_NAME = "lastName"
        private const val COLUMN_NAME_BIRTHDAY = "birthday"
        private const val COLUMN_NAME_AVATAR_URL = "avatarUrl"
        private const val COLUMN_NAME_SPECIALTIES = "specialties"

        const val EMPLOYEES_SQL_CREATE = "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} TEXT PRIMARY KEY," +
                "$COLUMN_NAME_FIRST_NAME TEXT," +
                "$COLUMN_NAME_LAST_NAME TEXT," +
                "$COLUMN_NAME_BIRTHDAY TEXT," +
                "$COLUMN_NAME_AVATAR_URL TEXT," +
                "$COLUMN_NAME_SPECIALTIES TEXT)"

        const val EMPLOYEES_SQL_DELETE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}