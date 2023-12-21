package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.Constraints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConstraintViewModel (application: Application): AndroidViewModel(application){
    private val repository: Repository
    private val constraints: Repository

    var con: List<Constraints>? = null

    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        repository = Repository(dao)
        constraints  = Repository(dao)
    }


    fun submit(constraints: Constraints) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.sendConstraint(constraints)
        }
    }

    fun getConstraints(){
        viewModelScope.launch(Dispatchers.IO){
            con = constraints.getConstraints()
        }
    }


    @Suppress("UNCHECKED_CAST")
    class ConstraintViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ConstraintViewModel(application) as T
        }
    }
}