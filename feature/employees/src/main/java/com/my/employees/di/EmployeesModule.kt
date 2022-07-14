package com.my.employees.di

import com.my.core.DispatchersWrapper
import com.my.employees.EmployeeUiConverter
import com.my.employees.EmployeesFragment
import com.my.employees_domain.employees.ObserveEmployeesUseCase
import com.my.employees_domain.employees.RefreshEmployeesUseCase
import com.my.specialties.di.SpecialtiesFragmentFactory
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class EmployeesModule {

    @Provides
    fun provideEmployeesFragmentFactory(
        specialtiesFragment: EmployeesFragment,
        specialtiesFragmentFactory: SpecialtiesFragmentFactory
    ) = EmployeesFragmentFactory(specialtiesFragment, specialtiesFragmentFactory)

    @Provides
    fun provideEmployeesFragment(
        employeesViewModelFactory: EmployeesViewModelFactory,
        specialtiesFragmentFactory: SpecialtiesFragmentFactory
    ) = EmployeesFragment(employeesViewModelFactory, specialtiesFragmentFactory)

    @Provides
    fun provideEmployeesViewModelFactory(
        observeEmployeesUseCase: ObserveEmployeesUseCase,
        refreshEmployeesUseCase: RefreshEmployeesUseCase,
        dispatchers: DispatchersWrapper,
        employeeUiConverter: EmployeeUiConverter
    ) = EmployeesViewModelFactory(
        observeEmployeesUseCase,
        refreshEmployeesUseCase,
        dispatchers,
        employeeUiConverter
    )

    @Provides
    fun provideEmployeeUiConverter() = EmployeeUiConverter()
}