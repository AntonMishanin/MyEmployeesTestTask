package com.my.employees_root.di

import com.my.core.ComponentStore
import com.my.core.FeatureScope
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
        employeesRootViewModelFactory: EmployeesRootViewModelFactory,
        componentStore: ComponentStore
    ) = EmployeesRootFragment(fragmentFactory, employeesRootViewModelFactory, componentStore)

    @Provides
    fun provideEmployeesRootFragmentFactory(
        specialtiesFragment: SpecialtiesFragment,
        employeesFragment: EmployeesFragment
    ) = EmployeesRootFragmentFactory(specialtiesFragment, employeesFragment)

    @FeatureScope
    @Provides
    fun provideEmployeesRootViewModelFactory(
        refreshEmployeesUseCase: RefreshEmployeesUseCase
    ) = EmployeesRootViewModelFactory(refreshEmployeesUseCase)

    @Provides
    fun provideRefreshEmployeesUseCase(
        employeesRootRepository: EmployeesRootRepository
    ) = RefreshEmployeesUseCase(employeesRootRepository)
}