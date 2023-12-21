package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginViewModel(application: Application): AndroidViewModel(application){

    private val loginRepository: Repository


    var loggedUser: User? = null



    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        loginRepository = Repository(dao)

    }

    fun login(username: String, password: String): Deferred<User> {


        return viewModelScope.async(Dispatchers.IO) {

            loggedUser= loginRepository.login(username, password)
            if(loggedUser==null){
                throw GeneralException("Credenziali errate")
            }

            return@async loggedUser!!
        }
    }

    fun createAccount(newUser: User){

        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.insertUser(newUser)
            loggedUser = newUser
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

