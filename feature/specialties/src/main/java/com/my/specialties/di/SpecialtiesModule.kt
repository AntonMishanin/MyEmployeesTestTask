package com.my.specialties.di

import com.my.core.DispatchersWrapper
import com.my.employees_domain.specialties.ChooseSpecialtyUseCase
import com.my.employees_domain.specialties.ObserveSpecialtiesUseCase
import com.my.employees_domain.specialties.ResetSpecialtyUseCase
import com.my.specialties.SpecialtiesFragment
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class SpecialtiesModule {

    @Provides
    fun provideSpecialtiesFragmentFactory(specialtiesFragment: SpecialtiesFragment) =
        SpecialtiesFragmentFactory(specialtiesFragment)

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