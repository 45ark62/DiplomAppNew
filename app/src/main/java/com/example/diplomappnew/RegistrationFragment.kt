package com.example.diplomappnew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.diplomappnew.databinding.FragmentLoginBinding
import com.example.diplomappnew.databinding.FragmentRegistrationBinding
import com.example.diplomappnew.retrofit.Register


import com.example.diplomappnew.retrofit.mainApi
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    private val viewModel:LoginViewModel by activityViewModels()
    private lateinit var MAINApiR:mainApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        binding.apply {
            buttonRegistr.setOnClickListener{
                register(
                    Register(editEmailAddressRegistration.text.toString(),editPasswordRegistr.text.toString())
                )
            }
        }
    }
    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.50.122:5000").client(client)
//            .baseUrl("http://10.0.2.2:5000").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        MAINApiR = retrofit.create(mainApi::class.java)


    }
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }
    private fun register(regRequest: Register) {
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val ResponseReg = MAINApiR.register(regRequest)
            val message =
                ResponseReg.errorBody()?.string()?.let { JSONObject(it).getString("message") }
            requireActivity().runOnUiThread {
                binding.ErrorReg.text = message.toString()
                val userR = ResponseReg.body()
                if (userR != null) {
                    viewModel.accessToken.value = userR.accessToken
                    findNavController().navigate(R.id.action_RegistrationFragment_to_UserFragment)
                }
            }
        }
    }
}
