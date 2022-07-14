package com.my.employees.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.employees.EmployeesFragment
import com.my.specialties.SpecialtiesFragment
import com.my.specialties.di.SpecialtiesFragmentFactory

class EmployeesFragmentFactory(
    private val employeesFragment: EmployeesFragment,
    private val specialtiesFragmentFactory: SpecialtiesFragmentFactory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesFragment::class.java -> employeesFragment()
            SpecialtiesFragment::class.java -> specialtiesFragmentFactory.specialtiesFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun employeesFragment() = employeesFragment
}