package com.my.employee_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.my.core.ViewBindingFragment
import com.my.employee_details.databinding.FragmentEmployeeDetailsBinding
import com.my.employee_details.di.EmployeeDetailsViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeDetailsFragment(
    private val employeeDetailsViewModelFactory: EmployeeDetailsViewModelFactory
) : ViewBindingFragment<FragmentEmployeeDetailsBinding>(
    FragmentEmployeeDetailsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<EmployeeDetailsViewModel> { employeeDetailsViewModelFactory }
        viewModel.state.onEach {
            binding.nameValue.text = it.firstName
            binding.surnameValue.text = it.lastName
            binding.birthdayValue.text = it.birthday
            binding.ageValue.text = it.age
            binding.specialtyValue.text = it.specialty
        }.launchIn(lifecycleScope)

        binding.back.setOnClickListener {
            // TODO: think about navigation
            requireActivity().onBackPressed()
        }
    }

    // TODO: think about pass arguments to EmployeeDetailsViewModelFactory
    fun setArguments(itemId: String): EmployeeDetailsFragment {
        employeeDetailsViewModelFactory.setArguments(itemId)
        return this
    }
}