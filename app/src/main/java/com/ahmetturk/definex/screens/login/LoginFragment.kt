package com.ahmetturk.definex.screens.login

import android.view.ViewGroup
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun getViewBinding(container: ViewGroup?) =
        FragmentLoginBinding.inflate(layoutInflater, container, false)

}
