package com.ahmetturk.definex.screens.settings

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.ahmetturk.definex.R
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentEmptyBinding

class SettingsFragment : BaseFragment<FragmentEmptyBinding>() {

    override fun getViewBinding(container: ViewGroup?) = FragmentEmptyBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setText(R.string.settings)
    }

}
