package com.example.speedypizza.screens.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.ScheduleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalendarViewModel(application: Application): AndroidViewModel(application) {

    private val newCal: Repository

    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        newCal = Repository(dao)
    }


    fun newCalendar(scheduleItemList: MutableList<ScheduleItem>) {
        viewModelScope.launch(Dispatchers.IO){
            newCal.createCalendar(scheduleItemList)
        }
    }


    @Suppress("UNCHECKED_CAST")
    class CalendarViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CalendarViewModel(application) as T
        }
    }
}