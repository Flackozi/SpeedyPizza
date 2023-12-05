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

    fun submit(nickname: String, min: Int, max: Int, checkBoxValues: MutableList<Int>) {
        val lunedi= checkBoxValues[0]
        val martedi= checkBoxValues[1]
        val mercoledi= checkBoxValues[2]
        val giovedi= checkBoxValues[3]
        val venerdi= checkBoxValues[4]
        val sabato= checkBoxValues[5]
        val domenica= checkBoxValues[6]

        viewModelScope.launch(Dispatchers.IO) {
            /*if(min>max){
                throw GeneralException("Min non può essere maggiore di max")
            }*/
            repository.sendConstraint(Constraints(nickname, max, min, lunedi, martedi, mercoledi, giovedi, venerdi, sabato, domenica ))
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