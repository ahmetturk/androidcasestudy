package com.ahmetturk.definex.screens.main

import android.view.ViewGroup
import com.ahmetturk.definex.base.BaseFragment
import com.ahmetturk.definex.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getViewBinding(container: ViewGroup?) =
        FragmentMainBinding.inflate(layoutInflater, container, false)

}
