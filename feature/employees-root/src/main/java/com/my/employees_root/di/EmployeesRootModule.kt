package com.my.employees_root.di

import com.my.employees.presentation.EmployeesFragment
import com.my.employees_root.data.EmployeesRootRepository
import com.my.employees_root.domain.RefreshEmployeesUseCase
import com.my.employees_root.presentation.EmployeesRootFragment
import com.my.specialties.presentation.SpecialtiesFragment
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
@Module
class EmployeesRootModule {

    @Provides
    fun provideEmployeesRootFragment(
        fragmentFactory: EmployeesRootFragmentFactory,
        employeesRootViewModelFactory: EmployeesRootViewModelFactory
    ) = EmployeesRootFragment(fragmentFactory, employeesRootViewModelFactory)

    @Provides
    fun provideEmployeesRootFragmentFactory(
        specialtiesFragment: SpecialtiesFragment,
        employeesFragment: EmployeesFragment
    ) = EmployeesRootFragmentFactory(specialtiesFragment, employeesFragment)

    @Provides
    fun provideEmployeesRootViewModelFactory(
        refreshEmployeesUseCase: RefreshEmployeesUseCase
    ) = EmployeesRootViewModelFactory(refreshEmployeesUseCase)

    @Provides
    fun provideRefreshEmployeesUseCase(
        employeesRootRepository: EmployeesRootRepository
    ) = RefreshEmployeesUseCase(employeesRootRepository)
}