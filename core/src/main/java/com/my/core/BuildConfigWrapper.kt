package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
interface BuildConfigWrapper {

    fun baseUrl(): String

    fun isDebug(): Boolean
}