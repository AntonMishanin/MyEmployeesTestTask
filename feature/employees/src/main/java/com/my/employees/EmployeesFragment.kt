package com.my.employees

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.my.core.ViewBindingFragment
import com.my.employees.databinding.FragmentEmployeesBinding
import com.my.employees.di.EmployeesViewModelFactory
import com.my.specialties.di.SpecialtiesFragmentFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
// TODO: think about cross-feature dependencies. You can use root fragment
class EmployeesFragment(
    private val employeesViewModelFactory: EmployeesViewModelFactory,
    private val specialtiesFragmentFactory: SpecialtiesFragmentFactory
) : ViewBindingFragment<FragmentEmployeesBinding>(
    FragmentEmployeesBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = specialtiesFragmentFactory
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.content, specialtiesFragmentFactory.specialtiesFragment())
                .commit()
        }

        val viewModel by viewModels<EmployeesViewModel> { employeesViewModelFactory }
        val adapter = EmployeesAdapter({
            (requireActivity() as? EmployeesNavigation)?.toEmployeeDetails(it.id)
        }, onRefreshClicked = viewModel::refreshEmployees)
        binding.list.adapter = adapter

        viewModel.state.onEach(adapter::submitList).launchIn(lifecycleScope)
    }
}