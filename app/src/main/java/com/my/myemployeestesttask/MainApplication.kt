package com.my.myemployeestesttask

import android.app.Application
import com.my.core.Component
import java.lang.IllegalArgumentException

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class MainApplication : Application(), Component.Provide, Component.Clear {

    private val componentStore = HashMap<String, Any>()

    override fun <T : Any> provideComponent(clazz: Class<T>) =
        when (clazz) {
            AppComponent::class.java -> createIfNeededAndGet(clazz) {
                AppComponent.Factory(application = this).create()
            }
            else -> throw IllegalArgumentException("Unknown class $clazz")
        } as T

    override fun <T : Any> clearComponent(clazz: Class<T>) {
        val key = clazz.canonicalName ?: ""
        componentStore.remove(key)
    }

    // TODO: think about this common logic here
    private fun <T : Any> createIfNeededAndGet(clazz: Class<T>, createComponent: () -> T): T {
        val key = clazz.canonicalName ?: ""
        val component = componentStore[key]
        return if (component == null) {
            val newComponent = createComponent.invoke()
            componentStore[key] = newComponent
            newComponent
        } else {
            component
        } as T
    }
}