package com.my.specialties.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface SpecialtiesRepository {

    suspend fun observeSpecialties(): Flow<List<Specialty>>

    suspend fun saveSpecialtiesToCache(data: List<Specialty>)

    suspend fun fetchSpecialties(): List<Specialty>
}