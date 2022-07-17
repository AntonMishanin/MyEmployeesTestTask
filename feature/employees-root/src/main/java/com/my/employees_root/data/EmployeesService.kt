package com.my.employees_root.data

import retrofit2.http.GET

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
interface EmployeesService {

    @GET("65gb/static/raw/master/testTask.json")
    suspend fun requestEmployees(): EmployeesResponse
}