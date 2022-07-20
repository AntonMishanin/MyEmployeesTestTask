package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.core.ComponentStore
import com.my.core.ProvideComponent
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees.presentation.EmployeesNavigation
import com.my.employees_root.di.EmployeesRootComponent
import com.my.employees_root.presentation.EmployeesRootFragment

// TODO: think about navigation
class MainActivity : AppCompatActivity(), EmployeesNavigation {

    private val fragmentFactory by lazy {
        val provideComponent = (application as ProvideComponent)
        val appComponent = provideComponent.provideComponent<AppComponent>()
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
    private val componentStore: ComponentStore,
    private val provideComponent: ProvideComponent
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            EmployeesRootFragment::class.java -> employeesRootFragment()
            EmployeeDetailsFragment::class.java -> employeeDetailsFragment()
            else -> super.instantiate(classLoader, className)
        }

    // TODO: think about duplication
    fun employeesRootFragment(): EmployeesRootFragment {
        val clazz = EmployeesRootComponent::class.java
        var component = componentStore.get(clazz)
        if (component == null) {
            component = provideComponent.provideComponent<AppComponent>().employeesRootComponent()
            componentStore.add(component, clazz)
        }
        return component.fragment()
    }

    fun employeeDetailsFragment(): EmployeeDetailsFragment {
        val clazz = EmployeeDetailsComponent::class.java
        var component = componentStore.get(clazz)
        if (component == null) {
            component = provideComponent.provideComponent<AppComponent>().employeeDetailsComponent()
            componentStore.add(component, clazz)
        }
        return component.fragment()
    }
}