package com.example.diplomappnew

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diplomappnew.databinding.ContentMainBinding
import com.example.diplomappnew.retrofit.mainApi
import kotlinx.coroutines.CoroutineExceptionHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)





    }

    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }



}