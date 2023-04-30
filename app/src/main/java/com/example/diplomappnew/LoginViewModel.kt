package com.example.diplomappnew

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    val accessToken = MutableLiveData<String>()
}