package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
interface Converter<in I : Any, out R : Any> {

    fun convert(input: I): R
}