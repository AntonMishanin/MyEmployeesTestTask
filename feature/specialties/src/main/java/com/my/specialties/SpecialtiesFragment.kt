package com.my.specialties

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.my.core.ViewBindingFragment
import com.my.specialties.databinding.FragmentSpecialtiesBinding
import com.my.specialties.di.SpecialtiesViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * @Author: Anton Mishanin
 * @Date: 7/13/2022
 */
class SpecialtiesFragment(
    private val specialtiesViewModelFactory: SpecialtiesViewModelFactory
) : ViewBindingFragment<FragmentSpecialtiesBinding>(
    FragmentSpecialtiesBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<SpecialtiesViewModel> { specialtiesViewModelFactory }
        binding.reset.setOnClickListener {
            viewModel.onResetClicked()
        }

        val adapter = SpecialtiesAdapter(viewModel::onSpecialtyClicked)
        binding.listItem.adapter = adapter

        viewModel.state.onEach(adapter::submitList).launchIn(lifecycleScope)
    }
}