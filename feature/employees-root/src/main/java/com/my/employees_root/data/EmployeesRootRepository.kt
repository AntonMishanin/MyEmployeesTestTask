package com.my.employees_root.data

import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import com.my.specialties.data.SaveSpecialties
import com.my.specialties.data.SpecialtyConverter
import com.my.employees.domain.FilterParamsRepository
import com.my.employees_root.EmployeesResult
import com.my.specialties.domain.ChooseSpecialtiesRepository

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
class EmployeesRootRepository(
    private val remoteDataSource: EmployeesRemoteDataSource,
    private val employeesConverter: EmployeesConverter,
    private val specialtyConverter: SpecialtyConverter,
    private val saveSpecialties: SaveSpecialties,
    private val employeesStorage: EmployeesStorage,
    private val filterParamsMemoryCache: FilterParamsMemoryCache
) : FilterParamsRepository, ChooseSpecialtiesRepository {

    suspend fun requestEmployees(): EmployeesResult {
        return try {
            networkRequest()
            EmployeesResult.Content
        } catch (e: Exception) {// TODO: for different exceptions - different state
            when (employeesStorage.employeesNumber() == 0) {
                true -> EmployeesResult.EmptyContentError(e)
                false -> EmployeesResult.ContentWithError(e)
            }
        }
    }

    private suspend fun networkRequest() {
        // Employees
        val response = remoteDataSource.requestEmployees()
        val employeesDbo = response.employees?.map {
            employeesConverter.convert(
                firstName = it.firstName,
                lastName = it.lastName,
                birthday = it.birthday,
                avatarUrl = it.avatarUrl,
                specialties = it.specialties?.map { it.name ?: "" }
            )
        } ?: emptyList()
        employeesStorage.saveEmployees(employeesDbo)

        // Specialties
        val specialtiesSet = HashSet<SpecialtyDto>()
        response.employees?.forEach {
            it.specialties?.forEach {
                specialtiesSet.add(it)
            }
        }
        val specialtiesDbo = specialtiesSet.map { specialtyConverter.convert(it.id, it.name) }
        saveSpecialties.saveSpecialties(specialtiesDbo)
    }

    override suspend fun observeFilterParams() = filterParamsMemoryCache.observable()

    override suspend fun chooseSpecialties(specialties: List<String>) =
        filterParamsMemoryCache.replace(specialties)
}