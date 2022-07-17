package com.my.specialties.domain

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
interface ChooseSpecialtiesRepository {

    suspend fun chooseSpecialties(specialties: List<String>)
}