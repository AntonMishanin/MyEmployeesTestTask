package com.my.employees_domain.specialties

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ResetSpecialtyUseCase(
    private val specialtiesRepository: SpecialtiesRepository
) {

    suspend fun invoke() {
        val specialties = specialtiesRepository.fetchSpecialties()
        val result = specialties.map { specialty ->
            when (specialty.isActive) {
                true -> specialty.copy(isActive = false)
                false -> specialty
            }
        }
        specialtiesRepository.saveSpecialties(result)
    }
}