package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.Repository
import com.example.speedypizza.db.UserDatabase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application){

    private val loginRepository: Repository
    var ruolo: Int = 0

    init {
        val dao = UserDatabase.getInstance(application).userDao()
        loginRepository = Repository(dao)
    }


    /*fun login(username: String, password: String){

        viewModelScope.launch(Dispatchers.IO) {
            delay(300)
            ruolo = loginRepository.login1(username, password)
        }
    }*/

    fun login(username: String, password: String): Deferred<Int> {
        return viewModelScope.async(Dispatchers.IO) {
            delay(300)
            return@async loginRepository.login1(username, password)
        }
    }



    @Suppress("UNCHECKED_CAST")
    class LoginViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return LoginViewModel(application) as T
        }
    }

}