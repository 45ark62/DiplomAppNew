package com.example.diplomappnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.diplomappnew.databinding.FragmentUserAccountBinding


class UserAccount : Fragment() {

    private lateinit var binding: FragmentUserAccountBinding
    private val viewModel: LoginViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserAccountBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.accessToken.observe(viewLifecycleOwner){
            accessToken ->
        }

        binding.apply {
            NavView.setNavigationItemSelectedListener {
                when(it.itemId){

                    R.id.Records -> findNavController().navigate(R.id.action_UserFragment_to_recordsFragment)
                    R.id.Accounts -> findNavController().navigate(R.id.action_UserFragment_to_accountsFragment)
                }
                drawer.closeDrawer(GravityCompat.START)
                true

            }
            btnAccounts.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentAccountsForUserAccountFragment_to_fragmentBudgetAndTargetsForUserAccountFragment)

            }


        }

    }


}