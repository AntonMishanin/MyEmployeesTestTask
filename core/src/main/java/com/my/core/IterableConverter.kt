package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/12/2022
 */
abstract class IterableConverter<in I : Any, out R : Any> : Converter<I, R> {

    fun convert(iterable: Iterable<I>): List<R> = iterable.map(::convert)
}