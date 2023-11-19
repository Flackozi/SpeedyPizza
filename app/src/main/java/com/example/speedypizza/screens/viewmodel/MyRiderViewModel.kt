package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyRiderViewModel(application: Application): AndroidViewModel(application) {

    private val myRiderRepository: Repository
    private val deleteRider: Repository
    private val getPhone: Repository
    private val addRider: Repository

    var myRiders: List<User>? = null

    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        myRiderRepository = Repository(dao)
        deleteRider = Repository(dao)
        getPhone = Repository(dao)
        addRider = Repository(dao)
    }

    fun retrieveMyRider() {
        viewModelScope.launch(Dispatchers.IO){
            myRiders = myRiderRepository.retrieveMyRider()
        }
    }

    fun deleteRider(username: String){
        viewModelScope.launch(Dispatchers.IO){
            deleteRider.deleteRider(username)
        }
    }

    fun addRider(username: String){
        viewModelScope.launch(Dispatchers.IO){
            addRider.addRider(username)
        }

    }

    @Suppress("UNCHECKED_CAST")
    class MyRiderViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MyRiderViewModel(application) as T
        }
    }


}