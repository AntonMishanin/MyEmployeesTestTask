package com.my.employees_root.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.my.core.ComponentStore
import com.my.core.ViewBindingFragment
import com.my.employees_root.di.EmployeesRootFragmentFactory
import com.my.employees_root.R
import com.my.employees_root.databinding.FragmentEmployeesRootBinding
import com.my.employees_root.di.EmployeesRootComponent
import com.my.employees_root.di.EmployeesRootViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/16/2022
 */
class EmployeesRootFragment(
    private val fragmentFactory: EmployeesRootFragmentFactory,
    private val employeesRootViewModelFactory: EmployeesRootViewModelFactory,
    private val componentStore: ComponentStore
) : ViewBindingFragment<FragmentEmployeesRootBinding>(
    FragmentEmployeesRootBinding::inflate
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.employees, fragmentFactory.employeesFragment())
                .commit()

            childFragmentManager
                .beginTransaction()
                .replace(R.id.specialties, fragmentFactory.specialtiesFragment())
                .commit()
        }

        val viewModel by viewModels<EmployeesRootViewModel> { employeesRootViewModelFactory }
        viewModel.state.onEach {
            // TODO: handle result
        }.launchIn(lifecycleScope)
    }

    override fun onStop() {
        super.onStop()
        if (!requireActivity().isChangingConfigurations) {
            componentStore.clear(EmployeesRootComponent::class.java)
        }
    }
}