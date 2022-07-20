package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/18/2022
 */
interface ProvideComponent {

    fun <T : Any> provideComponent(): T
}