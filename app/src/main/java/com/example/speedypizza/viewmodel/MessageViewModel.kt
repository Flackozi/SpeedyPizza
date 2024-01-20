package com.example.speedypizza.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.speedypizza.db.DBGenerator
import com.example.speedypizza.db.Repository
import com.example.speedypizza.entity.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel(application: Application): AndroidViewModel(application){

    private val messageRepository: Repository


    var messageList: List<Message>? = null

    init {
        val dao = DBGenerator.getInstance(application).speedyPizzaDao()
        messageRepository = Repository(dao)

    }


    fun retrieveMessages(nickname: String) {
        viewModelScope.launch(Dispatchers.IO){
            messageList = messageRepository.retrieveMessages(nickname)
        }
    }


    @Suppress("UNCHECKED_CAST")
    class MessageViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MessageViewModel(application) as T
        }
    }
}