package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.core.Component
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employees.presentation.EmployeesNavigation
import com.my.employees_root.presentation.EmployeesRootFragment

// TODO: think about navigation
class MainActivity : AppCompatActivity(), EmployeesNavigation {

    private val fragmentFactory by lazy {
        val provideComponent = (application as Component.Provide)
        val appComponent = provideComponent.provideComponent(AppComponent::class.java)
        appComponent.provideMainFragmentFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content, fragmentFactory.employeesRootFragment())
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
    private val employeesRootFragment: EmployeesRootFragment,
    private val employeeDetailsFragment: EmployeeDetailsFragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesRootFragment::class.java -> employeesRootFragment()
            EmployeeDetailsFragment::class.java -> employeeDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun employeesRootFragment() = employeesRootFragment

    fun employeeDetailsFragment() = employeeDetailsFragment
}