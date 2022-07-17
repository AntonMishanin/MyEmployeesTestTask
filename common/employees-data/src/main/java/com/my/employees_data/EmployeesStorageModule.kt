package com.my.employees_data

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
@Module
class EmployeesStorageModule {

    @Provides
    fun provideEmployeesDao(database: EmployeesDatabase): EmployeesStorage = EmployeesStorageDao(database)

    @Provides
    fun provideEmployeesDatabase(context: Context) = EmployeesDatabase(context)

    @Provides
    fun provideEmployeesConverter() = EmployeesConverter()
}