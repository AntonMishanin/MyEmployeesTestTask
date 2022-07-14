package com.my.myemployeestesttask

import android.app.Application
import com.my.core.AppScope
import com.my.employees_data.EmployeesDataModule
import com.my.employees_domain.specialties.SpecialtiesDomainModule
import com.my.specialties.di.SpecialtiesFragmentFactory
import com.my.specialties.di.SpecialtiesModule
import dagger.BindsInstance
import dagger.Component

@[AppScope Component(
    modules = [EmployeesDataModule::class, SpecialtiesModule::class,
        SpecialtiesDomainModule::class, AppModule::class]
)]
internal interface AppComponent {

    fun provideSpecialtiesFragmentFactory(): SpecialtiesFragmentFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}