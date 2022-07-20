package com.my.myemployeestesttask

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.LifecycleOwner
import com.my.core.*
import com.my.employee_details.EmployeeDetailsFragment
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employees_root.di.EmployeesRootComponent
import com.my.employees_root.presentation.EmployeesRootFragment

/**
 * @Author: Anton Mishanin
 * @Date: 7/20/2022
 */
internal class AppNavigation(
    private val fragmentFactory: MainFragmentFactory
) : Navigation {

    private val events = SingleLiveEvent<NavigationDestination>()
    private var activity: AppCompatActivity? = null

    fun initFragmentFactory(activity: AppCompatActivity) {
        this.activity = activity
        activity.supportFragmentManager.fragmentFactory = fragmentFactory
    }

    fun subscribe() {
        val owner = activity as LifecycleOwner
        events.observe(owner) { destination ->
            when (destination) {
                NavigationDestination.Back -> navigateBack()
                is NavigationDestination.EmployeeDetails -> navigateToEmployeeDetails(destination.id)
                NavigationDestination.EmployeesRoot -> navigateToEmployeesRoot()
            }
        }

        if (events.value == null) {
            events.value = NavigationDestination.EmployeesRoot
        }
    }

    fun unsubscribe() {
        this.activity = null
    }

    override fun navigate(destination: NavigationDestination) {
        events.value = destination
    }

    private fun navigateBack() {
        activity?.onBackPressed()
    }

    private fun navigateToEmployeeDetails(id: String) {
        val fragmentManager = activity?.supportFragmentManager ?: return
        fragmentManager
            .beginTransaction()
            .replace(R.id.content, fragmentFactory.employeeDetailsFragment().setArguments(id))
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToEmployeesRoot() {
        val fragmentManager = activity?.supportFragmentManager ?: return
        fragmentManager
            .beginTransaction()
            .add(R.id.content, fragmentFactory.employeesRootFragment())
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