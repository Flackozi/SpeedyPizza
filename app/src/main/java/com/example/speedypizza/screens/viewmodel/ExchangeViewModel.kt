package com.example.speedypizza.screens.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.Exchanges
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExchangeViewModel (application: Application): AndroidViewModel(application){
    var riderTurns: List<String>?=null
    var myRiders: List<User>? = null
    var myShifts:List<Shifts>?= null
    var riderShifts:List<Shifts>?= null
    var receivedRequests:List<Exchanges>?= null

    private val repository: Repository


    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        repository = Repository(dao)
    }


    fun retrieveMyRider() {
        viewModelScope.launch(Dispatchers.IO) {
            myRiders=repository.retrieveMyRider()
            //println(myRiders?.map { user -> user.nickname })
        }

    }

    fun retrieveMyShifts(nickname: String){
        viewModelScope.launch(Dispatchers.IO){
            myShifts=repository.retrieveAllShifts()
            //println(myShifts?.map { shift -> shift.day })
        }
    }

    fun retrieveRiderShifts(){
        viewModelScope.launch(Dispatchers.IO){
            riderShifts=repository.retrieveAllShifts()
            //println(nickname)
            //println(repository.retrieveShifts(nickname))
            //riderTurns=riderShifts?.map { shift -> shift.day }
            println(riderTurns)
        }
    }


    fun retrieveExchange(nickname: String) {
        viewModelScope.launch(Dispatchers.IO){
            receivedRequests=repository.retrieveRequests(nickname)
        }
    }

    //toglie dal db la richesta che è stata rifiutata
    fun deleteRequest(nickname: String, senderName: String, senderShift: String, receiverShift: String) {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteRequest(nickname, senderName, senderShift, receiverShift)
        }
    }

    //elimina il turno vecchio del rider
    fun updateShift(rider: String, newShift: String, oldShift: String) {
        viewModelScope.launch(Dispatchers.IO){
            Log.d("rider", rider)
            Log.d("day", newShift)
            repository.updateShift(rider, newShift, oldShift)
        }
    }

    fun deleteOtherRequest(senderName: String, senderShift: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteOtherRequest(senderName, senderShift)
        }
    }

   /* fun sendRequest(sender: Exchanges, senderShift: String, receiver: String, receiverShift: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.newRequest(Exchanges(sender, receiver, senderShift, receiverShift))
        }
    }*/

    fun sendRequest(exchanges: List<Exchanges>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.newRequest(exchanges)
        }
    }
    @Suppress("UNCHECKED_CAST")
    class ExchangeViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ExchangeViewModel(application) as T
        }
    }
}