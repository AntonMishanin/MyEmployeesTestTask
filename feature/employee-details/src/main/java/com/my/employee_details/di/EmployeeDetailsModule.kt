package com.my.employee_details.di

import com.my.core.ComponentStore
import com.my.core.DispatchersWrapper
import com.my.core.FeatureScope
import com.my.employee_details.EmployeeDetailsConverter
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employee_details.data.EmployeeRepositoryImpl
import com.my.employee_details.domain.EmployeeRepository
import com.my.employee_details.domain.FetchEmployeeUseCase
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class EmployeeDetailsModule {

    @Provides
    fun provideEmployeeDetailsFragment(
        employeeDetailsViewModelFactory: EmployeeDetailsViewModelFactory,
        componentStore: ComponentStore
    ) = EmployeeDetailsFragment(employeeDetailsViewModelFactory, componentStore)

    @FeatureScope
    @Provides
    fun provideEmployeeDetailsViewModelFactory(
        fetchEmployeeUseCase: FetchEmployeeUseCase,
        dispatchers: DispatchersWrapper,
        employeeDetailsConverter: EmployeeDetailsConverter
    ) = EmployeeDetailsViewModelFactory(
        fetchEmployeeUseCase,
        dispatchers,
        employeeDetailsConverter
    )

    @Provides
    fun provideEmployeeDetailsConverter() = EmployeeDetailsConverter()

    @Provides
    fun provideFetchEmployeeUseCase(
        employeeRepository: EmployeeRepository
    ) = FetchEmployeeUseCase(employeeRepository)

    @Provides
    fun provideEmployeeRepository(
        employeesStorage: EmployeesStorage,
        employeesConverter: EmployeesConverter
    ): EmployeeRepository = EmployeeRepositoryImpl(employeesStorage, employeesConverter)
}