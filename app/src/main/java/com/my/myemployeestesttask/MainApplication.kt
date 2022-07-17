package com.my.myemployeestesttask

import android.app.Application

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
internal class MainApplication : Application(), AppComponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(application = this)
            .build()
    }

    override fun provide() = appComponent
}