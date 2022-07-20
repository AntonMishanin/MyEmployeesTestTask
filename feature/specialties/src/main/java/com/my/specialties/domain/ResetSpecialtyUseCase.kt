package com.my.specialties.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ResetSpecialtyUseCase(
    private val specialtiesRepository: SpecialtiesRepository,
    private val chooseSpecialtiesRepository: ChooseSpecialtiesRepository
) {

    suspend fun invoke() {
        val specialties = specialtiesRepository.fetchSpecialties()
        val result = specialties.map { specialty ->
            when (specialty.isActive) {
                true -> specialty.copy(isActive = false)
                false -> specialty
            }
        }
        specialtiesRepository.saveSpecialtiesToCache(result)
        chooseSpecialtiesRepository.chooseSpecialties(result.filter { it.isActive }.map { it.name })
    }
}