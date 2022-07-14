package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.core.BuildConfigWrapper
import com.my.core.DispatchersWrapper
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class AppModule {

    @[Provides AppScope]
    internal fun provideContext(application: Application) = application.applicationContext

    @[Provides AppScope]
    internal fun provideDispatcher(): DispatchersWrapper = DispatchersWrapper.Impl()

    @[Provides AppScope]
    internal fun provideBuildConfigWrapper() = object : BuildConfigWrapper {

        override fun baseUrl() = "https://gitlab.65apps.com/"

        override fun isDebug() = BuildConfig.DEBUG
    }
}