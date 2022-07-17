package com.my.specialties.di

import com.my.specialties.domain.*
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class DomainModule {

    @Provides
    fun provideChooseSpecialtyUseCase(
        specialtiesRepository: SpecialtiesRepository,
        chooseSpecialtiesRepository: ChooseSpecialtiesRepository
    ) = ChooseSpecialtyUseCase(specialtiesRepository, chooseSpecialtiesRepository)

    @Provides
    fun provideObserveSpecialtiesUseCase(specialtiesRepository: SpecialtiesRepository) =
        ObserveSpecialtiesUseCase(specialtiesRepository)

    @Provides
    fun provideResetSpecialtyUseCase(
        specialtiesRepository: SpecialtiesRepository,
        chooseSpecialtiesRepository: ChooseSpecialtiesRepository
    ) = ResetSpecialtyUseCase(specialtiesRepository, chooseSpecialtiesRepository)
}