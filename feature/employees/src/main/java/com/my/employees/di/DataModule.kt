package com.my.employees.di

import com.my.core.AppScope
import com.my.core.DispatchersWrapper
import com.my.employees_data.EmployeesConverter
import com.my.employees.data.EmployeesMemoryCache
import com.my.employees.data.EmployeesRepositoryImpl
import com.my.employees.domain.EmployeesRepository
import com.my.employees_data.EmployeesStorage
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
@Module
class DataModule {

    @[Provides AppScope]
    fun provideEmployeesAndSpecialtyRepository(
        employeesStorage: EmployeesStorage,
        employeesConverter: EmployeesConverter,
        dispatchers: DispatchersWrapper,
        employeesMemoryCache: EmployeesMemoryCache
    ): EmployeesRepository = EmployeesRepositoryImpl(
        employeesStorage,
        employeesConverter,
        dispatchers,
        employeesMemoryCache
    )

    @Provides
    fun provideEmployeesMemoryCache() = EmployeesMemoryCache()
}