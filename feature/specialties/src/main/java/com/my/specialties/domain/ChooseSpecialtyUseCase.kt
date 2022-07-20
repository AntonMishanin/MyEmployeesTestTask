package com.my.specialties.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ChooseSpecialtyUseCase(
    private val specialtiesRepository: SpecialtiesRepository,
    private val chooseSpecialtiesRepository: ChooseSpecialtiesRepository
) {

    suspend fun invoke(id: Int) {
        val specialties = specialtiesRepository.fetchSpecialties()
        val result = specialties.map { specialty ->
            if (specialty.id == id) {
                specialty.copy(isActive = true)
            } else {
                when (specialty.isActive) {
                    true -> specialty.copy(isActive = false)
                    false -> specialty
                }
            }
        }
        specialtiesRepository.saveSpecialtiesToCache(result)
        chooseSpecialtiesRepository.chooseSpecialties(result.filter { it.isActive }.map { it.name })
    }
}