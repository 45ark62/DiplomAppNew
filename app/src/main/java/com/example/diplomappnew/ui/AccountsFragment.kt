package com.example.diplomappnew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.diplomappnew.LoginViewModel
import com.example.diplomappnew.R
import com.example.diplomappnew.databinding.FragmentAccountsBinding
import com.example.diplomappnew.databinding.FragmentUserAccountBinding


class AccountsFragment : Fragment() {
    private lateinit var binding: FragmentAccountsBinding
    private val viewModel:LoginViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accessToken.observe(viewLifecycleOwner){
                accessToken ->
        }
        binding.apply {
            NavViewAcc.setNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.MainPage -> findNavController().navigate(R.id.action_accountsFragment_to_UserFragment)
                   R.id.Records -> findNavController().navigate(R.id.action_accountsFragment_to_recordsFragment)

                }
                drawer.closeDrawer(GravityCompat.START)
                true
            }

       }

    }

}