package com.my.specialties.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ObserveSpecialtiesUseCase(
    private val specialtiesRepository: SpecialtiesRepository
) {

    suspend fun invoke() = specialtiesRepository.observeSpecialties()
}