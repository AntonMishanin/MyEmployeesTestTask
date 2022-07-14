package com.my.employee_details.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.employee_details.EmployeeDetailsFragment

class EmployeeDetailsFragmentFactory(
    private val employeeDetailsFragment: EmployeeDetailsFragment,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeeDetailsFragment::class.java -> employeeDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun employeeDetailsFragment() = employeeDetailsFragment
}