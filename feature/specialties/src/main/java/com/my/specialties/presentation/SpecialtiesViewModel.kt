package com.my.specialties.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.core.DispatchersWrapper
import com.my.specialties.domain.Specialty
import com.my.specialties.domain.ChooseSpecialtyUseCase
import com.my.specialties.domain.ObserveSpecialtiesUseCase
import com.my.specialties.domain.ResetSpecialtyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class SpecialtiesViewModel(
    private val chooseSpecialtyUseCase: ChooseSpecialtyUseCase,
    private val observeSpecialtiesUseCase: ObserveSpecialtiesUseCase,
    private val resetSpecialtyUseCase: ResetSpecialtyUseCase,
    private val dispatchers: DispatchersWrapper
) : ViewModel() {

    private val innerState = MutableStateFlow<List<Specialty>>(emptyList())
    val state: StateFlow<List<Specialty>> = innerState

    init {
        viewModelScope.launch(dispatchers.main()) {
            observeSpecialtiesUseCase.invoke().collect {
                innerState.value = it
            }
        }
    }

    fun onSpecialtyClicked(specialty: Specialty) {
        viewModelScope.launch(dispatchers.main()) {
            chooseSpecialtyUseCase.invoke(specialty.id)
        }
    }

    fun onResetClicked() {
        viewModelScope.launch(dispatchers.main()) {
            resetSpecialtyUseCase.invoke()
        }
    }
}