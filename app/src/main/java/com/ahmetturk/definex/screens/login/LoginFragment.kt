package com.ahmetturk.definex.screens.login

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ahmetturk.definex.R
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentLoginBinding
import com.ahmetturk.definex.network.NetworkResult
import com.ahmetturk.definex.network.RetrofitBuilder.apiService
import com.ahmetturk.definex.network.apiCall
import com.ahmetturk.definex.network.login.LoginRequest
import com.ahmetturk.definex.utils.setGradientTextColor
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getViewBinding(container: ViewGroup?) =
        FragmentLoginBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.titleTextView.setGradientTextColor(
            ContextCompat.getColor(requireContext(), R.color.titleTextGradientStart),
            ContextCompat.getColor(requireContext(), R.color.titleTextGradientEnd)
        )

        binding.loginButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        lifecycleScope.launch {
            val request = LoginRequest(email, password)
            when (apiCall { apiService.login(request) }) {
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), "Email or password is wrong.", Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Success -> {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }
    }
}
