package com.my.specialties.di

import android.content.Context
import com.my.core.FeatureScope
import com.my.specialties.data.*
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
@Module
class DataModule {

    @FeatureScope
    @Provides
    fun provideSpecialtiesRepository(
        specialtiesMemoryCache: SpecialtiesMemoryCache,
        specialtyConverter: SpecialtyConverter,
        specialtiesDao: SpecialtiesDao
    ) = SpecialtiesRepositoryImpl(specialtiesMemoryCache, specialtyConverter, specialtiesDao)

    @Provides
    fun provideSpecialtiesMemoryCache() = SpecialtiesMemoryCache()

    @Provides
    fun provideSpecialtyConverter() = SpecialtyConverter()

    @Provides
    fun provideSpecialtiesDao(
        specialtiesDatabase: SpecialtiesDatabase
    ) = SpecialtiesDao(specialtiesDatabase)

    @Provides
    fun provideSpecialtiesDatabase(context: Context) = SpecialtiesDatabase(context)
}