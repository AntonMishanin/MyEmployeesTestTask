package com.my.employees_root.data

import com.google.gson.annotations.SerializedName

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
data class EmployeeDto(
    @SerializedName("f_name")
    val firstName: String?,
    @SerializedName("l_name")
    val lastName: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("avatr_url")
    val avatarUrl: String?,
    @SerializedName("specialty")
    val specialties: List<SpecialtyDto>?
)