package com.example.postsapplication.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation


open class BaseFragment<T : ViewDataBinding> : Fragment() {
    lateinit var binding: T

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
    }
    fun setViewModelWithDataBinding(inflater: LayoutInflater, res: Int): View {

        binding = DataBindingUtil.inflate(
            inflater, res, null, false
        )
        binding.executePendingBindings()
        binding.lifecycleOwner = this
        return binding.root
    }


}
