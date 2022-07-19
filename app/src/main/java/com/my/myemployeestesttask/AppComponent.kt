package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees.di.EmployeesModule
import com.my.employees.di.DataModule
import com.my.employees_data.EmployeesStorageModule
import com.my.specialties.di.DomainModule
import com.my.employees_root.di.EmployeesRootModule
import com.my.employees_root.data.NetworkModule
import com.my.employees_root.di.EmployeesRootComponent
import com.my.specialties.di.SpecialtiesModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [AppModule::class, EmployeesStorageModule::class]
)]
internal interface AppComponent {

    fun provideMainFragmentFactory(): MainFragmentFactory

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