package com.example.diplomappnew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.diplomappnew.databinding.FragmentBudgetAndTargetsForUserAccountBinding


class FragmentBudgetAndTargetsForUserAccountFragment : Fragment() {
    private lateinit var binding: FragmentBudgetAndTargetsForUserAccountBinding
    private val viewModel: LoginViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBudgetAndTargetsForUserAccountBinding.inflate(inflater, container, false)
        return binding.root}

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewModel.accessToken.observe(viewLifecycleOwner) { accessToken ->
            }

            binding.apply {

                }


            }

        }


