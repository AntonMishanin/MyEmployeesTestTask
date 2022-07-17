package com.my.specialties.di

import com.my.core.DispatchersWrapper
import com.my.specialties.domain.ChooseSpecialtyUseCase
import com.my.specialties.domain.ObserveSpecialtiesUseCase
import com.my.specialties.domain.ResetSpecialtyUseCase
import com.my.specialties.presentation.SpecialtiesFragment
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module(includes = [DataModule::class, DataModuleBinds::class, DomainModule::class])
class SpecialtiesModule {

    @Provides
    fun provideSpecialtiesFragment(specialtiesViewModelFactory: SpecialtiesViewModelFactory) =
        SpecialtiesFragment(specialtiesViewModelFactory)

    @Provides
    fun provideSpecialtiesViewModelFactory(
        chooseSpecialtyUseCase: ChooseSpecialtyUseCase,
        observeSpecialtiesUseCase: ObserveSpecialtiesUseCase,
        resetSpecialtyUseCase: ResetSpecialtyUseCase,
        dispatchers: DispatchersWrapper
    ) = SpecialtiesViewModelFactory(
        chooseSpecialtyUseCase,
        observeSpecialtiesUseCase,
        resetSpecialtyUseCase,
        dispatchers
    )
}