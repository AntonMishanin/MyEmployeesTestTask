package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.employee_details.di.EmployeeDetailsModule
import com.my.employees.di.EmployeesFragmentFactory
import com.my.employees.di.EmployeesModule
import com.my.employees_data.EmployeesDataModule
import com.my.employees_domain.employees.EmployeesDomainModule
import com.my.employees_domain.specialties.SpecialtiesDomainModule
import com.my.specialties.di.SpecialtiesFragmentFactory
import com.my.specialties.di.SpecialtiesModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [EmployeesDataModule::class, SpecialtiesModule::class,
        SpecialtiesDomainModule::class, AppModule::class, EmployeesModule::class, EmployeesDomainModule::class,
        EmployeeDetailsModule::class]
)]
internal interface AppComponent {

    fun provideMainFragmentFactory(): MainFragmentFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}