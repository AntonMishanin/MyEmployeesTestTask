package com.my.employees_domain.specialties

import com.my.employees_domain.Specialty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class SpecialtiesRepositoryMock : SpecialtiesRepository {

    private var specialties = mutableListOf(
        Specialty(id = 1, name = "1", isActive = true),
        Specialty(id = 2, name = "2", isActive = false),
        Specialty(id = 3, name = "3", isActive = false)
    )

    override suspend fun observeSpecialties(): Flow<List<Specialty>> = flowOf(specialties)

    override suspend fun saveSpecialties(data: List<Specialty>) {
        specialties.clear()
        specialties.addAll(data)
    }

    override suspend fun fetchSpecialties() = specialties
}