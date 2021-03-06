package com.my.employee_details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.my.core.ComponentStore
import com.my.core.ViewBindingFragment
import com.my.employee_details.databinding.FragmentEmployeeDetailsBinding
import com.my.employee_details.di.EmployeeDetailsComponent
import com.my.employee_details.di.EmployeeDetailsViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/14/2022
 */
class EmployeeDetailsFragment(
    private val employeeDetailsViewModelFactory: EmployeeDetailsViewModelFactory,
    private val componentStore: ComponentStore
) : ViewBindingFragment<FragmentEmployeeDetailsBinding>(
    FragmentEmployeeDetailsBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        binding.toolbar.title = ""

        val viewModel by viewModels<EmployeeDetailsViewModel> { employeeDetailsViewModelFactory }
        viewModel.state.onEach { employee ->
            binding.nameValue.text = employee.name
            binding.birthdayValue.text = employee.birthday
            binding.ageValue.text = employee.age
            binding.specialtyValue.text = employee.specialty
            binding.gender.text = employee.gender
            Glide.with(this)
                .load(employee.avatarUrl)
                .placeholder(R.drawable.avatar_placeholder)
                .error(R.drawable.avatar_placeholder)
                .into(binding.avatar)
        }.launchIn(lifecycleScope)

        binding.toolbar.setNavigationOnClickListener {
            viewModel.onBackClicked()
        }
    }

    // TODO: think about pass arguments to EmployeeDetailsViewModelFactory
    fun setArguments(itemId: String): EmployeeDetailsFragment {
        employeeDetailsViewModelFactory.setArguments(itemId)
        return this
    }

    override fun onStop() {
        super.onStop()
        if (!requireActivity().isChangingConfigurations) {
            componentStore.clear(EmployeeDetailsComponent::class.java)
        }
    }
}