package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/17/2022
 */
interface Component {

    interface Factory<out T : Any> {

        fun create(): T
    }

    interface Provide {

        fun <T : Any> provideComponent(clazz: Class<T>): T
    }

    interface Clear {

        fun <T : Any> clearComponent(clazz: Class<T>)
    }
}