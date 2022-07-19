package com.my.employees_root.di

import android.content.Context
import com.my.core.BuildConfigWrapper
import com.my.core.ComponentStore
import com.my.core.DispatchersWrapper
import com.my.core.FeatureScope
import com.my.employees.di.DataModule
import com.my.employees.di.EmployeesModule
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import com.my.employees_root.data.NetworkModule
import com.my.employees_root.presentation.EmployeesRootFragment
import com.my.specialties.di.DomainModule
import com.my.specialties.di.SpecialtiesModule
import dagger.BindsInstance
import dagger.Component

/**
 * @Author: Anton Mishanin
 * @Date: 7/19/2022
 */
@FeatureScope
@Component(
    modules = [DataModule::class, DomainModule::class, SpecialtiesModule::class,
        EmployeesModule::class, DomainModule::class, EmployeesRootModule::class, NetworkModule::class]
)
interface EmployeesRootComponent {

    fun fragment(): EmployeesRootFragment

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun employeesStorage(employeesStorage: EmployeesStorage): Builder

        @BindsInstance
        fun employeesConverter(employeesConverter: EmployeesConverter): Builder

        @BindsInstance
        fun dispatchers(dispatchers: DispatchersWrapper): Builder

        @BindsInstance
        fun buildConfigWrapper(buildConfigWrapper: BuildConfigWrapper): Builder

        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun componentStore(componentStore: ComponentStore): Builder

        fun build(): EmployeesRootComponent
    }
}