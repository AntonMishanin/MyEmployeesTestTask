package com.my.employees_data

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesStorageDao(
    private val database: EmployeesDatabase
) : EmployeesStorage {

    private var observable: (List<EmployeeDbo>) -> Unit = {}

    override fun setCallback(callback: (List<EmployeeDbo>) -> Unit) {
        observable = callback
    }

    override fun removeCallback() {
        observable = {}
    }

    override suspend fun saveEmployees(employees: List<EmployeeDbo>) {
        observable.invoke(employees)
        employees.forEach(::save)
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

    override fun read(): List<EmployeeDbo> {
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

    override fun read(id: String): EmployeeDbo {
        val query = "SELECT " +
                "${BaseColumns._ID}, " +
                "$COLUMN_NAME_FIRST_NAME," +
                "$COLUMN_NAME_LAST_NAME," +
                "$COLUMN_NAME_BIRTHDAY," +
                "$COLUMN_NAME_AVATAR_URL," +
                "$COLUMN_NAME_SPECIALTIES FROM $TABLE_NAME WHERE ${BaseColumns._ID} LIKE \"%" + id + "%\""
        val cursor = database.readableDatabase.rawQuery(query, null)
        while (cursor.moveToNext()) {
            return EmployeeDbo(
                id = cursor.getString(0),
                firstName = cursor.getString(1),
                lastName = cursor.getString(2),
                birthday = cursor.getString(3),
                avatarUrl = cursor.getString(4),
                specialties = cursor.getString(5)
            )
        }
        cursor.close()
        throw IllegalArgumentException("Unknown id $id")
    }

    override suspend fun employeesNumber(): Int {
        val count = "SELECT count(*) FROM $TABLE_NAME"
        val cursor = database.writableDatabase.rawQuery(count, null)
        cursor.moveToFirst()
        val size = cursor.getInt(0)
        cursor.close()
        return size
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