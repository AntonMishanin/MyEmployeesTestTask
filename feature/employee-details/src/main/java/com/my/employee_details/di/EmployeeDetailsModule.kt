package com.my.employee_details.di

import com.my.core.DispatchersWrapper
import com.my.employee_details.EmployeeDetailsConverter
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employees_domain.employees.FetchEmployeeUseCase
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class EmployeeDetailsModule {

    @Provides
    fun provideEmployeeDetailsFragmentFactory(
        employeeDetailsFragment: EmployeeDetailsFragment
    ) = EmployeeDetailsFragmentFactory(employeeDetailsFragment)

    @Provides
    fun provideEmployeeDetailsFragment(
        employeeDetailsViewModelFactory: EmployeeDetailsViewModelFactory
    ) = EmployeeDetailsFragment(employeeDetailsViewModelFactory)

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
}