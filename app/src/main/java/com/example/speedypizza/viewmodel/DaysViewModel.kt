package com.example.speedypizza.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.Days
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DaysViewModel(application: Application): AndroidViewModel(application){

    private val days: Repository

    var daysInfo: List<Days>? = null

    init{
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        days = Repository(dao)
    }

    fun getDays(){
        viewModelScope.launch(Dispatchers.IO){
            daysInfo = days.getDaysInfo()
        }
    }

    @Suppress("UNCHECKED_CAST")
    class DaysViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DaysViewModel(application) as T
        }
    }
}