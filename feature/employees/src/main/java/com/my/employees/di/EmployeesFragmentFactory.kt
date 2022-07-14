package com.my.employees.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.employees.EmployeesFragment

class EmployeesFragmentFactory(
    private val employeesFragment: EmployeesFragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesFragment::class.java -> employeesFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun employeesFragment() = employeesFragment
}