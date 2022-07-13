package com.my.employees_data

import com.my.core.DispatchersWrapper
import com.my.employees_data.database.employees.EmployeesDao
import com.my.employees_data.network.EmployeesRemoteDataSource
import com.my.employees_data.database.specialties.SpecialtiesDao
import com.my.employees_domain.employees.EmployeesRepository
import com.my.employees_domain.specialties.SpecialtiesRepository
import com.my.employees_domain.Specialty
import com.my.employees_domain.employees.Employee
import kotlinx.coroutines.withContext
import java.lang.Exception

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
class EmployeesAndSpecialtiesRepository(
    private val remoteDataSource: EmployeesRemoteDataSource,
    private val employeesDao: EmployeesDao,
    private val employeesConverter: EmployeesConverter,
    private val specialtiesDao: SpecialtiesDao,
    private val specialtiesConverter: SpecialtiesConverter,
    private val employeesMemoryCache: EmployeesMemoryCache,
    private val specialtiesMemoryCache: SpecialtiesMemoryCache,
    private val dispatchers: DispatchersWrapper
) : EmployeesRepository, SpecialtiesRepository {

    override suspend fun observeEmployees() = employeesMemoryCache.flow()

    override suspend fun refreshEmployees() = withContext(dispatchers.io()) {
        try {
            fetchFromNetworkAndSaveToStorage()
        } catch (exception: Exception) {
            fetchFromStorage(exception)
        }
    }

    private suspend fun fetchFromNetworkAndSaveToStorage() {
        val response = remoteDataSource.requestEmployees()
        val pair = employeesConverter.convert(response.employees ?: emptyList())

        val specialtiesDbo = specialtiesConverter.convert(pair.second)
        val specialtiesDomain = specialtiesConverter.toDomain(specialtiesDbo)
        specialtiesMemoryCache.replace(specialtiesDomain)
        specialtiesDao.save(specialtiesDbo)

        val employeesDomain = employeesConverter.toDomain(pair.first, specialtiesDomain)
        employeesMemoryCache.replace(employeesDomain)
        employeesDao.save(pair.first)
    }

    private suspend fun fetchFromStorage(exception: Exception) {
        val employeesDbo = employeesDao.read()
        val specialtiesDbo = specialtiesDao.read()

        if (employeesDbo.isEmpty() && specialtiesDbo.isEmpty()) {
            throw exception
        }

        val specialtiesDomain = specialtiesConverter.toDomain(specialtiesDbo)
        specialtiesMemoryCache.replace(specialtiesDomain)

        val employeesDomain = employeesConverter.toDomain(employeesDbo, specialtiesDomain)
        employeesMemoryCache.replace(employeesDomain)
    }

    override suspend fun fetchEmployee(id: String): Employee =
        employeesMemoryCache.flow().value.find { it.id == id }
            ?: throw IllegalArgumentException("Unknown id $id")

    override suspend fun observeSpecialties() = specialtiesMemoryCache.flow()

    override suspend fun saveSpecialties(data: List<Specialty>) {
        specialtiesMemoryCache.replace(data)
    }

    override suspend fun fetchSpecialties() = specialtiesMemoryCache.flow().value
}