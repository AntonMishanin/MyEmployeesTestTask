package com.my.employees_domain.specialties

import com.my.employees_domain.Specialty
import kotlinx.coroutines.flow.Flow

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface SpecialtiesRepository {

    suspend fun observeSpecialties(): Flow<List<Specialty>>

    suspend fun saveSpecialties(data: List<Specialty>)

    suspend fun fetchSpecialties(): List<Specialty>
}