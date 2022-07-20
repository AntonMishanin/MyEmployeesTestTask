package com.my.employees_root.data

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
internal class EmployeesRemoteDataSourceTest {

    @Test
    fun `test request employees return not null response`() = runBlocking {
        val dataSource = provideEmployeesRemoteDataSource()

        val expected = dataSource.requestEmployees().employees
        val actual = null
        Assert.assertNotEquals(expected, actual)
    }

    @Test
    fun `test request employees return not empty response`() = runBlocking {
        val dataSource = provideEmployeesRemoteDataSource()

        val expected = dataSource.requestEmployees().employees
        val actual = emptyList<com.my.employees_root.data.EmployeeDto>()
        Assert.assertNotEquals(expected, actual)
    }

    private fun provideEmployeesRemoteDataSource(): com.my.employees_root.data.EmployeesRemoteDataSource {
        val service = Retrofit.Builder()
            .baseUrl("https://gitlab.65apps.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(com.my.employees_root.data.EmployeesService::class.java)
        return com.my.employees_root.data.EmployeesRemoteDataSource(service)
    }
}