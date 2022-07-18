package com.my.core

/**
 * @Author: Anton Mishanin
 * @Date: 7/18/2022
 */
class ComponentStore : Component.Clear {

    private val componentStore = HashMap<String, Any>()

    override fun <T : Any> clear(clazz: Class<T>): Any? = componentStore.remove(key(clazz))

    fun <T : Any> get(clazz: Class<T>) = componentStore[key(clazz)] as T?

    fun <T : Any> add(component: Any, clazz: Class<T>) = componentStore.put(key(clazz), component)

    private fun <T : Any> key(clazz: Class<T>) = clazz.canonicalName ?: ""
}