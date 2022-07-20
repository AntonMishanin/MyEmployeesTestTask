package com.my.employee_details.di

import com.my.core.ComponentStore
import com.my.core.DispatchersWrapper
import com.my.core.FeatureScope
import com.my.core.Navigation
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employees_data.EmployeesConverter
import com.my.employees_data.EmployeesStorage
import dagger.BindsInstance
import dagger.Component

/**
 * @Author: Anton Mishanin
 * @Date: 7/18/2022
 */
@FeatureScope
@Component(modules = [EmployeeDetailsModule::class])
interface EmployeeDetailsComponent {

    fun fragment(): EmployeeDetailsFragment

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun employeesStorage(employeesStorage: EmployeesStorage): Builder

        @BindsInstance
        fun employeesConverter(employeesConverter: EmployeesConverter): Builder

        @BindsInstance
        fun dispatchers(dispatchers: DispatchersWrapper): Builder

        @BindsInstance
        fun componentStore(componentStore: ComponentStore): Builder

        @BindsInstance
        fun navigation(navigation: Navigation): Builder

        fun build(): EmployeeDetailsComponent
    }
}