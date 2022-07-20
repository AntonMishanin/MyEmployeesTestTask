package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees_data.EmployeesStorageModule
import com.my.employees_root.di.EmployeesRootComponent
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [AppModule::class, EmployeesStorageModule::class]
)]
internal interface AppComponent {

    fun provideAppNavigation(): AppNavigation

    fun employeeDetailsComponent(): EmployeeDetailsComponent

    fun employeesRootComponent(): EmployeesRootComponent

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    class Factory(
        private val application: Application
    ) : com.my.core.Component.Factory<AppComponent> {

        override fun create(): AppComponent = DaggerAppComponent
            .builder()
            .application(application)
            .build()
    }
}