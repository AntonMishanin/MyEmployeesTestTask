package com.my.employees.di

import com.my.core.DispatchersWrapper
import com.my.employees.domain.EmployeesRepository
import com.my.employees.domain.FilterParamsRepository
import com.my.employees.presentation.EmployeeUiConverter
import com.my.employees.presentation.EmployeesFragment
import com.my.employees.domain.ObserveEmployeesUseCase
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class EmployeesModule {

    @Provides
    fun provideEmployeesFragment(
        employeesViewModelFactory: EmployeesViewModelFactory
    ) = EmployeesFragment(employeesViewModelFactory)

    @Provides
    fun provideEmployeesViewModelFactory(
        observeEmployeesUseCase: ObserveEmployeesUseCase,
        dispatchers: DispatchersWrapper,
        employeeUiConverter: EmployeeUiConverter
    ) = EmployeesViewModelFactory(
        observeEmployeesUseCase,
        dispatchers,
        employeeUiConverter
    )

    @Provides
    fun provideEmployeeUiConverter() = EmployeeUiConverter()

    @Provides
    fun provideObserveEmployeesUseCase(
        employeesRepository: EmployeesRepository,
        filterParamsRepository: FilterParamsRepository
    ) = ObserveEmployeesUseCase(employeesRepository, filterParamsRepository)
}