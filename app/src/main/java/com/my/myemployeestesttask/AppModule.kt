package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.core.BuildConfigWrapper
import com.my.core.DispatchersWrapper
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employees_root.presentation.EmployeesRootFragment
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module
class AppModule {

    @Provides
    internal fun provideMainFragmentFactory(
        employeesRootFragment: EmployeesRootFragment,
        componentStore: ComponentStore,
        provideComponent: ProvideComponent
    ) = MainFragmentFactory(
        employeesRootFragment,
        componentStore,
        provideComponent
    )

    @[Provides AppScope]
    internal fun provideComponentProvider(
        application: Application
    ): ProvideComponent = application as ProvideComponent

    @[Provides AppScope]
    internal fun provideContext(application: Application) = application.applicationContext

    @[Provides AppScope]
    internal fun provideDispatcher(): DispatchersWrapper = DispatchersWrapper.Impl()

    @[Provides AppScope]
    internal fun provideBuildConfigWrapper() = object : BuildConfigWrapper {

        // TODO: move to BuildConfig
        override fun baseUrl() = "https://gitlab.65apps.com/"

        override fun isDebug() = BuildConfig.DEBUG
    }
}