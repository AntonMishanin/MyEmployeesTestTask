package com.my.specialties.di

import com.my.specialties.data.SaveSpecialties
import com.my.specialties.data.SpecialtiesRepositoryImpl
import com.my.specialties.domain.SpecialtiesRepository
import dagger.Binds
import dagger.Module

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
@Module
abstract class DataModuleBinds {

    @Binds
    abstract fun bindSpecialtiesRepository(implementation: SpecialtiesRepositoryImpl): SpecialtiesRepository

    @Binds
    abstract fun bindSaveSpecialties(implementation: SpecialtiesRepositoryImpl): SaveSpecialties
}