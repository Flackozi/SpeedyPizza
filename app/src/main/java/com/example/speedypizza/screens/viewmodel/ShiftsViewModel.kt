package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShiftsViewModel(application: Application): AndroidViewModel(application) {

    private val shifts: Repository

    var ridersDay: List<String>? = null


    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        shifts = Repository(dao)
    }


    fun getRidersForDay(day: String){
        viewModelScope.launch(Dispatchers.IO){
            ridersDay = shifts.getRidersDay(day)
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