package com.my.employees_root.data

import com.my.employees.domain.FilterParams
import kotlinx.coroutines.flow.MutableStateFlow

class FilterParamsMemoryCache(
    private val flow: MutableStateFlow<FilterParams> = MutableStateFlow(FilterParams())
) {

    suspend fun replace(specialties: List<String>) {
        val result = flow.value.copy(specialtiesId = specialties.toSet())
        flow.emit(result)
    }

    fun observable() = flow
}