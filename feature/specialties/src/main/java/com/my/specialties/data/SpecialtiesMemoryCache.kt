package com.my.specialties.data

import com.my.specialties.domain.Specialty
import kotlinx.coroutines.flow.MutableStateFlow

class SpecialtiesMemoryCache(
    private val flow: MutableStateFlow<List<Specialty>> = MutableStateFlow(emptyList())
) {

    suspend fun replace(data: List<Specialty>) {
        flow.emit(data)
    }

    suspend fun add(data: List<Specialty>) {
        val result = mutableListOf<Specialty>()
        result.addAll(flow.value)
        result.addAll(data)
        flow.emit(result)
    }

    fun flow() = flow
}