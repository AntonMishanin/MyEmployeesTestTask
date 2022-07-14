package com.my.employees_data

import com.my.core.AppScope
import com.my.core.DispatchersWrapper
import com.my.employees_data.database.DatabaseModule
import com.my.employees_data.database.employees.EmployeesDao
import com.my.employees_data.database.specialties.SpecialtiesDao
import com.my.employees_data.network.EmployeesRemoteDataSource
import com.my.employees_data.network.NetworkModule
import com.my.employees_domain.employees.EmployeesRepository
import com.my.employees_domain.specialties.SpecialtiesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
@Module(includes = [EmployeesDataModuleBinds::class, NetworkModule::class, DatabaseModule::class])
class EmployeesDataModule {

    @[Provides AppScope]
    fun provideEmployeesAndSpecialtyRepository(
        remoteDataSource: EmployeesRemoteDataSource,
        employeesDao: EmployeesDao,
        employeesConverter: EmployeesConverter,
        specialtiesDao: SpecialtiesDao,
        specialtiesConverter: SpecialtiesConverter,
        employeesMemoryCache: EmployeesMemoryCache,
        specialtiesMemoryCache: SpecialtiesMemoryCache,
        dispatchers: DispatchersWrapper,
        filterParamsMemoryCache: FilterParamsMemoryCache
    ) = EmployeesAndSpecialtiesRepository(
        remoteDataSource,
        employeesDao,
        employeesConverter,
        specialtiesDao,
        specialtiesConverter,
        employeesMemoryCache,
        specialtiesMemoryCache,
        dispatchers,
        filterParamsMemoryCache
    )

    @Provides
    fun provideEmployeesConverter() = EmployeesConverter()

    @Provides
    fun provideSpecialtiesConverter() = SpecialtiesConverter()

    @Provides
    fun provideEmployeesMemoryCache() = EmployeesMemoryCache()

    @Provides
    fun provideSpecialtiesMemoryCache() = SpecialtiesMemoryCache()

    @Provides
    fun provideFilterParamsMemoryCache() = FilterParamsMemoryCache()
}

@Module
abstract class EmployeesDataModuleBinds {

    @Binds
    abstract fun bindEmployeesRepository(implementation: EmployeesAndSpecialtiesRepository): EmployeesRepository

    @Binds
    abstract fun bindSpecialtiesRepository(implementation: EmployeesAndSpecialtiesRepository): SpecialtiesRepository
}