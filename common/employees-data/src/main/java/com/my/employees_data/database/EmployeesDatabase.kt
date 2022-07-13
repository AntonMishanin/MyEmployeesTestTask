package com.my.employees_data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.my.employees_data.database.employees.EmployeesDao.Companion.EMPLOYEES_SQL_CREATE
import com.my.employees_data.database.employees.EmployeesDao.Companion.EMPLOYEES_SQL_DELETE
import com.my.employees_data.database.specialties.SpecialtiesDao.Companion.SPECIALTIES_SQL_CREATE
import com.my.employees_data.database.specialties.SpecialtiesDao.Companion.SPECIALTIES_SQL_DELETE

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesDatabase(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SPECIALTIES_SQL_CREATE)
        db.execSQL(EMPLOYEES_SQL_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SPECIALTIES_SQL_DELETE)
        db.execSQL(EMPLOYEES_SQL_DELETE)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) =
        onUpgrade(db, oldVersion, newVersion)

    private companion object : BaseColumns {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "EmployeesWithSpecialties"
    }
}