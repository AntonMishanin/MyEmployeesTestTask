package com.my.specialties.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.my.specialties.SpecialtiesFragment

class SpecialtiesFragmentFactory(
    private val specialtiesFragment: SpecialtiesFragment
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        when (loadFragmentClass(classLoader, className)) {
            SpecialtiesFragment::class.java -> specialtiesFragment()
            else -> super.instantiate(classLoader, className)
        }

    fun specialtiesFragment() = specialtiesFragment
}