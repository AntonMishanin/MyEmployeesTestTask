package com.my.employees_data

import com.my.employees_data.network.EmployeesRemoteDataSource
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
@Module
class EmployeesDataModule {

    @Provides
    fun provideEmployeesAndSpecialtyRepository(remoteDataSource: EmployeesRemoteDataSource) =
        EmployeesAndSpecialtiesRepository(remoteDataSource)
}