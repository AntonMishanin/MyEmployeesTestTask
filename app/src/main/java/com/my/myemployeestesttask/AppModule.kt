package com.my.myemployeestesttask

import android.app.Application
import com.my.core.*
import com.my.employee_details.di.DaggerEmployeeDetailsComponent
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
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
    internal fun provideEmployeeDetailsComponent(
        employeesStorage: EmployeesStorage,
        employeesConverter: EmployeesConverter,
        dispatchers: DispatchersWrapper,
        componentStore: ComponentStore
    ): EmployeeDetailsComponent = DaggerEmployeeDetailsComponent
        .builder()
        .employeesStorage(employeesStorage)
        .employeesConverter(employeesConverter)
        .dispatchers(dispatchers)
        .componentStore(componentStore)
        .build()

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

    @[Provides AppScope]
    internal fun provideComponentStore() = ComponentStore()
}