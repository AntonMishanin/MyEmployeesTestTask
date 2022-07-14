package com.my.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class ViewBindingFragment<out VB : ViewBinding>(
    private val provideViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var mutableBinding: VB? = null
    protected val binding get() = mutableBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mutableBinding = provideViewBinding(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mutableBinding = null
    }
}