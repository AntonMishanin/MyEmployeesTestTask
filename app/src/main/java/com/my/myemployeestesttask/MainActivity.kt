package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employees.EmployeesFragment
import com.my.employees.EmployeesNavigation
import com.my.specialties.SpecialtiesFragment

class MainActivity : AppCompatActivity(), EmployeesNavigation {

    private val fragmentFactory by lazy {
        (application as AppComponentProvider).provide().provideMainFragmentFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content, fragmentFactory.employeesFragment())
                .commit()
        }
    }

    override fun toEmployeeDetails(id: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmentFactory.employeeDetailsFragment().setArguments(id))
            .addToBackStack(null)
            .commit()
    }
}

class MainFragmentFactory(
    private val employeesFragment: EmployeesFragment,
    private val employeeDetailsFragment: EmployeeDetailsFragment,
    private val specialtiesFragment: SpecialtiesFragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesFragment::class.java -> employeesFragment()
            EmployeeDetailsFragment::class.java -> employeeDetailsFragment()
            SpecialtiesFragment::class.java -> specialtiesFragment
            else -> super.instantiate(classLoader, className)
        }

    fun employeesFragment() = employeesFragment

    fun employeeDetailsFragment() = employeeDetailsFragment

}