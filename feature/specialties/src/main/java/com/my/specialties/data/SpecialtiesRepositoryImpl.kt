package com.my.specialties.data

import com.my.specialties.domain.SpecialtiesRepository
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
class SpecialtiesRepositoryImpl(
    private val specialtiesMemoryCache: SpecialtiesMemoryCache,
    private val specialtyConverter: SpecialtyConverter,
    private val specialtiesDao: SpecialtiesDao
) : SpecialtiesRepository, SaveSpecialties {

    override suspend fun observeSpecialties() = specialtiesMemoryCache.flow().onEach {
        if (it.isEmpty()) {
            val specialtiesDbo = specialtiesDao.read()
            val specialtiesDomain = specialtyConverter.toDomain(specialtiesDbo)
            specialtiesMemoryCache.replace(specialtiesDomain)
        }
    }

    override suspend fun saveSpecialtiesToCache(data: List<com.my.specialties.domain.Specialty>) =
        specialtiesMemoryCache.replace(data)

    override suspend fun fetchSpecialties() = specialtiesMemoryCache.flow().value

    override suspend fun saveSpecialties(specialties: List<SpecialtyDbo>) {
        val specialtiesDomain = specialtyConverter.toDomain(specialties)
        specialtiesMemoryCache.replace(specialtiesDomain)
        specialtiesDao.save(specialties)
    }
}