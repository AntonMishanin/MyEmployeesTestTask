package com.my.specialties.data

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class SpecialtiesDao(
    private val database: SpecialtiesDatabase
) {

    fun save(data: List<SpecialtyDbo>) {
        data.forEach(::save)
    }

    private fun save(specialtyDbo: SpecialtyDbo) {
        val values = ContentValues().apply {
            put(BaseColumns._ID, specialtyDbo.id)
            put(COLUMN_NAME_NAME, specialtyDbo.name)
        }

        database.writableDatabase?.insertWithOnConflict(
            TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    fun read(): List<SpecialtyDbo> {
        val cursor = database.readableDatabase.rawQuery("SELECT  * FROM $TABLE_NAME", null)

        val result = mutableListOf<SpecialtyDbo>()
        while (cursor.moveToNext()) {
            val employee = SpecialtyDbo(
                id = cursor.getInt(0),
                name = cursor.getString(1)
            )
            result.add(employee)
        }

        cursor.close()
        return result
    }

    protected fun finalize() = database.close()

    internal companion object {
        private const val TABLE_NAME = "Specialties"
        private const val COLUMN_NAME_NAME = "firstName"

        const val SPECIALTIES_SQL_CREATE = "CREATE TABLE $TABLE_NAME (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "$COLUMN_NAME_NAME TEXT)"

        const val SPECIALTIES_SQL_DELETE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}