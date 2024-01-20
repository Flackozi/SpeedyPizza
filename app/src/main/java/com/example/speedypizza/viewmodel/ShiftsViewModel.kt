package com.example.speedypizza.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.Shifts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShiftsViewModel(application: Application): AndroidViewModel(application) {

    private val shifts: Repository

    var allShifts: List<Shifts>? = null


    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        shifts = Repository(dao)
    }

    fun getShifts(){
        viewModelScope.launch(Dispatchers.IO){
            allShifts = shifts.getShifts()
        }
    }


    @Suppress("UNCHECKED_CAST")
    class ShiftsViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShiftsViewModel(application) as T
        }
    }
}