package com.my.specialties.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.core.DispatchersWrapper
import com.my.employees_domain.specialties.ChooseSpecialtyUseCase
import com.my.employees_domain.specialties.ObserveSpecialtiesUseCase
import com.my.employees_domain.specialties.ResetSpecialtyUseCase
import com.my.specialties.SpecialtiesViewModel

class SpecialtiesViewModelFactory(
    private val chooseSpecialtyUseCase: ChooseSpecialtyUseCase,
    private val observeSpecialtiesUseCase: ObserveSpecialtiesUseCase,
    private val resetSpecialtyUseCase: ResetSpecialtyUseCase,
    private val dispatchers: DispatchersWrapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) = SpecialtiesViewModel(
        chooseSpecialtyUseCase,
        observeSpecialtiesUseCase,
        resetSpecialtyUseCase,
        dispatchers
    ) as T
}