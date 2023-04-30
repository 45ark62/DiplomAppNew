package com.example.diplomappnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.diplomappnew.databinding.FragmentAccountsForUserAccountBinding

class FragmentAccountsForUserAccountFragment:Fragment() {
    private lateinit var binding: FragmentAccountsForUserAccountBinding
    private val viewModel: LoginViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsForUserAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accessToken.observe(viewLifecycleOwner){
                accessToken ->
        }

        binding.apply {


        }

    }
}

