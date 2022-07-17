package com.my.employees_root.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.employees.presentation.EmployeesFragment
import com.my.specialties.presentation.SpecialtiesFragment

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
class EmployeesRootFragmentFactory(
    private val specialtiesFragment: SpecialtiesFragment,
    private val employeesFragment: EmployeesFragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesFragment::class.java -> employeesFragment()
            SpecialtiesFragment::class.java -> specialtiesFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun employeesFragment() = employeesFragment

    fun specialtiesFragment() = specialtiesFragment
}