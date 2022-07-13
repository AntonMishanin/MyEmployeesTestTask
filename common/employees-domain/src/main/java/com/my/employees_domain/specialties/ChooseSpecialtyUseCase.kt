package com.my.employees_domain.specialties

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class ChooseSpecialtyUseCase(
    private val specialtiesRepository: SpecialtiesRepository
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
        specialtiesRepository.saveSpecialties(result)
    }
}