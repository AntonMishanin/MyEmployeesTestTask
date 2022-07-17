package com.my.specialties.data

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class SpecialtyConverter {

    fun convert(id: Int?, name: String?) = SpecialtyDbo(
        id = id ?: throw IllegalArgumentException("id must be not null"),
        name = name ?: ""
    )

    fun toDomain(input: List<SpecialtyDbo>): List<com.my.specialties.domain.Specialty> {
        return input.map { toDomain(it) }
    }

    fun toDomain(input: SpecialtyDbo) = com.my.specialties.domain.Specialty(
        id = input.id,
        name = input.name,
        isActive = false
    )
}