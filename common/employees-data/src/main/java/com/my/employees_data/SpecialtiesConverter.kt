package com.my.employees_data

import com.my.core.IterableConverter
import com.my.employees_data.network.SpecialtyDto
import com.my.employees_data.database.specialties.SpecialtyDbo
import com.my.employees_domain.Specialty

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class SpecialtiesConverter : IterableConverter<SpecialtyDto, SpecialtyDbo>() {

    override fun convert(input: SpecialtyDto) = SpecialtyDbo(
        //TODO: think about fail fast strategy
        id = input.id ?: throw IllegalArgumentException("id must be not null"),
        name = input.name ?: ""
    )

    fun toDomain(input: List<SpecialtyDbo>): List<Specialty> {
       return input.map { toDomain(it) }
    }

    fun toDomain(input: SpecialtyDbo) = Specialty(
        id = input.id,
        name = input.name,
        isActive = false
    )
}