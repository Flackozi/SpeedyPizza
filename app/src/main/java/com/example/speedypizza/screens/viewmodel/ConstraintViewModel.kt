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

    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        repository = Repository(dao)
    }

    fun submit(checkBoxValues: MutableList<Int>) {
        val lunedi= checkBoxValues[0]
        val martedi= checkBoxValues[1]
        val mercoledi= checkBoxValues[2]
        val giovedi= checkBoxValues[3]
        val venerdi= checkBoxValues[4]
        val sabato= checkBoxValues[5]
        val domenica= checkBoxValues[6]

        viewModelScope.launch(Dispatchers.IO) {


            repository.SendConstraint(Constraints("giustiniman", 4, 5, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica ))
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