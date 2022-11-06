package com.ahmetturk.definex.screens.login

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.ahmetturk.definex.R
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentLoginBinding
import com.ahmetturk.definex.utils.setGradientTextColor

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getViewBinding(container: ViewGroup?) =
        FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTextView.setGradientTextColor(
            ContextCompat.getColor(requireContext(), R.color.titleTextGradientStart),
            ContextCompat.getColor(requireContext(), R.color.titleTextGradientEnd)
        )
    }
}
