package com.my.employees_data.database

import android.content.Context
import com.my.employees_data.database.employees.EmployeesDao
import com.my.employees_data.database.specialties.SpecialtiesDao
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
@Module
internal class DatabaseModule {

    @Provides
    fun provideSpecialtiesDao(database: EmployeesDatabase) = SpecialtiesDao(database)

    @Provides
    fun provideEmployeesDao(database: EmployeesDatabase) = EmployeesDao(database)

    @Provides
    fun provideEmployeesDatabase(context: Context) = EmployeesDatabase(context)
}