package com.picpay.desafio.android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val repository = PicPayRepository
    val _users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }
    val users: LiveData<List<User>> get() = _users
    val apiStatus: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllUsers()
                if (response.isSuccessful) {
                    if (response.body() != null && response.body()!!.isNotEmpty()) {
                        _users.postValue(response.body())
                        apiStatus.postValue(true)
                    } else {
                        apiStatus.postValue(false)
                    }
                } else {
                    apiStatus.postValue(false)
                }
            } catch (e: Exception) {
                Log.e("erro", e.message.toString())
                apiStatus.postValue(false)
            }
        }
    }
}