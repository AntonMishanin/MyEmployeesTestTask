package com.my.employees_root.data

import com.google.gson.annotations.SerializedName

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
data class EmployeesResponse(
    @SerializedName("response")
    val employees: List<EmployeeDto>?
)