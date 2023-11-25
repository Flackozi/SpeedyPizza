package com.example.speedypizza.db

import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.Days
import com.example.speedypizza.entity.Message
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.entity.User


class Repository(private val dao: SpeedyPizzaDAO) {


    fun login(username: String, password: String): User {

        return dao.login(username, password)

    }
    fun sendConstraint(constraints: Constraints) {

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


}


