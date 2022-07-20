package com.my.myemployeestesttask

import android.app.Application
import android.content.Context
import com.my.core.*
import com.my.employee_details.di.DaggerEmployeeDetailsComponent
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import com.my.employees_root.di.DaggerEmployeesRootComponent
import com.my.employees_root.di.EmployeesRootComponent
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
@Module(includes = [AppModuleBinds::class])
internal class AppModule {

    @Provides
    internal fun provideEmployeeDetailsComponent(
        employeesStorage: EmployeesStorage,
        employeesConverter: EmployeesConverter,
        dispatchers: DispatchersWrapper,
        componentStore: ComponentStore,
        appNavigation: AppNavigation
    ): EmployeeDetailsComponent = DaggerEmployeeDetailsComponent
        .builder()
        .employeesStorage(employeesStorage)
        .employeesConverter(employeesConverter)
        .dispatchers(dispatchers)
        .componentStore(componentStore)
        .navigation(appNavigation)
        .build()

    @Provides
    internal fun provideEmployeesRootComponent(
        employeesStorage: EmployeesStorage,
        employeesConverter: EmployeesConverter,
        dispatchers: DispatchersWrapper,
        buildConfigWrapper: BuildConfigWrapper,
        context: Context,
        componentStore: ComponentStore,
        appNavigation: AppNavigation
    ): EmployeesRootComponent = DaggerEmployeesRootComponent
        .builder()
        .employeesStorage(employeesStorage)
        .employeesConverter(employeesConverter)
        .dispatchers(dispatchers)
        .buildConfigWrapper(buildConfigWrapper)
        .context(context)
        .componentStore(componentStore)
        .navigation(appNavigation)
        .build()

    @[Provides AppScope]
    internal fun provideAppNavigation(
        mainFragmentFactory: MainFragmentFactory
    ) = AppNavigation(mainFragmentFactory)

    @Provides
    internal fun provideMainFragmentFactory(
        componentStore: ComponentStore,
        provideComponent: ProvideComponent
    ) = MainFragmentFactory(
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

        override fun baseUrl() = BuildConfig.EMPLOYEES_BASE_URL

        override fun isDebug() = BuildConfig.DEBUG
    }

    @[Provides AppScope]
    internal fun provideComponentStore() = ComponentStore()
}

@Module
internal abstract class AppModuleBinds {

    @Binds
    abstract fun bindNavigation(implementation: AppNavigation): Navigation
}