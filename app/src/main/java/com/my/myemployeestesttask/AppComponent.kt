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
import com.my.specialties.di.SpecialtiesModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [DataModule::class, DomainModule::class, SpecialtiesModule::class,
        EmployeesModule::class, AppModule::class, DomainModule::class, EmployeesRootModule::class,
        NetworkModule::class, EmployeesStorageModule::class]
)]
internal interface AppComponent {

    fun provideMainFragmentFactory(): MainFragmentFactory

    fun employeeDetailsComponent(): EmployeeDetailsComponent

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