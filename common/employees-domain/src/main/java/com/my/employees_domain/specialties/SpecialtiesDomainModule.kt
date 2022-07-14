package com.my.employees_domain.specialties

import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class SpecialtiesDomainModule {

    @Provides
    fun provideChooseSpecialtyUseCase(specialtiesRepository: SpecialtiesRepository) =
        ChooseSpecialtyUseCase(specialtiesRepository)

    @Provides
    fun provideObserveSpecialtiesUseCase(specialtiesRepository: SpecialtiesRepository) =
        ObserveSpecialtiesUseCase(specialtiesRepository)

    @Provides
    fun provideResetSpecialtyUseCase(specialtiesRepository: SpecialtiesRepository) =
        ResetSpecialtyUseCase(specialtiesRepository)
}