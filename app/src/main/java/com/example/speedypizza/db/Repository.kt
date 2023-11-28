package com.example.speedypizza.db

import android.util.Log
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.Exchanges
import com.example.speedypizza.entity.Days
import com.example.speedypizza.entity.Message
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.entity.User


class Repository(private val dao: SpeedyPizzaDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }
    fun SendConstraint(constraints: Constraints) {

        dao.sendConstraint(constraints)
    }

    fun retrieveMyRider(): List<User> {

        return dao.retrieveMyRiders()

    }

    fun deleteRider(username: String){
         return dao.deleteRider(username)
    }

    fun addRider(username: String){
        return dao.addRider(username)
    }

    fun createCalendar(dayList: MutableList<Days>, shiftList: MutableList<Shifts>) {

        dao.deleteExchanges()
        dao.deleteShifts()
        dao.deleteDays()
        dao.deleteNotifications()

        dayList.forEach { day ->
            dao.createCalendar1(day)
        }

        shiftList.forEach { shift ->
            dao.createCalendar2(shift)
        }
           /* item.rider.forEach{rider->
                dao.createCalendar2(rider, item.day)
            }
        }*/
    }

    fun insertUser(newUser: User){
        return dao.insertUser(newUser)
    }

    fun retrieveMessages(nickname: String): List<Message> {

        return dao.retrieveMessages(nickname)

    }


    fun retrieveShifts(nickname: String): List<Shifts> {
        return dao.retrieveShifts(nickname)
    }

    fun retrieveRequests(nickname: String): List<Exchanges> {
        return dao.retrieveRequests(nickname)
    }

    fun deleteRequest(nickname: String, senderName: String, senderShift: String, recieverShift: String) {
        dao.deleteRequest(nickname, senderName, senderShift, recieverShift)
    }

    fun updateShift(rider: String, newShift: String, oldShift: String) {

        dao.updateShift(rider, newShift, oldShift)
    }

    fun deleteOtherRequest(senderName: String, senderShift: String) {
        dao.deleteOtherRequest(senderName, senderShift)
    }

    fun newRequest(exchanges: Exchanges) {
        dao.sendRequest(exchanges)
    }

}


