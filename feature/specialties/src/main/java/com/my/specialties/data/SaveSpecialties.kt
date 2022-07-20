package com.my.specialties.data

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
interface SaveSpecialties {

    suspend fun saveSpecialties(specialties: List<SpecialtyDbo>)
}