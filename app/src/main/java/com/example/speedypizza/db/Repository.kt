package com.example.speedypizza.db

import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.ScheduleItem
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

    fun createCalendar(scheduleItemList: MutableList<ScheduleItem>) {
        scheduleItemList.forEach{item->
            dao.createCalendar1(item.day, item.min.toInt(), item.max.toInt())
            item.rider.forEach{rider->
                dao.createCalendar2(rider, item.day)
            }
        }
    }


}


