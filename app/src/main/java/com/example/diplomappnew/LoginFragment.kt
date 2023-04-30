package com.example.diplomappnew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.diplomappnew.databinding.FragmentLoginBinding
import com.example.diplomappnew.retrofit.User
import com.example.diplomappnew.retrofit.AuthRequest
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


class LoginFragment : Fragment() {

    private  lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var MAINApi: mainApi


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()

        binding.apply {
            bComeInAccount.setOnClickListener {

                auth(
                    AuthRequest(EmailAddressAuthoriz.text.toString(),TextPasswordAuthoriz.text.toString())
                )


            }
            RegistrationText.setOnClickListener{
                findNavController().navigate(R.id.action_LoginFragment_to_RegistrationFragment)
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
        MAINApi = retrofit.create(mainApi::class.java)


}
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }


    private fun auth(authRequest: AuthRequest){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response=MAINApi.auth(authRequest)
            val message=response.errorBody()?.string()?.let { JSONObject(it).getString("message") }
            requireActivity().runOnUiThread{
                binding.Error.text=message.toString()
                val user=response.body()
                if (user!= null){
                    viewModel.accessToken.value=user.accessToken
                    findNavController().navigate(R.id.action_LoginFragment_to_UserFragment)
                }



            }

        }
    }

}