package com.my.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
interface DispatchersWrapper {

    fun default(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    class Impl : DispatchersWrapper {

        override fun default() = Dispatchers.Default

        override fun main() = Dispatchers.Main

        override fun unconfined() = Dispatchers.Unconfined

        override fun io() = Dispatchers.IO
    }
}