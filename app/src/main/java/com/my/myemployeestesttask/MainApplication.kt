package com.my.myemployeestesttask

import android.app.Application
import com.my.core.ProvideComponent

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class MainApplication : Application(), ProvideComponent {

    private val appComponent by lazy {
        AppComponent.Factory(application = this).create()
    }

    override fun <T : Any> provideComponent() = appComponent as T
}