package com.my.employees_domain.employees

import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
@Module
class EmployeesDomainModule {

    @Provides
    fun provideFetchEmployeesUseCase(employeesRepository: EmployeesRepository) =
        FetchEmployeeUseCase(employeesRepository)

    @Provides
    fun provideObserveEmployeesUseCase(employeesRepository: EmployeesRepository) =
        ObserveEmployeesUseCase(employeesRepository)

    @Provides
    fun provideRefreshEmployeesUseCase(employeesRepository: EmployeesRepository) =
        RefreshEmployeesUseCase(employeesRepository)
}