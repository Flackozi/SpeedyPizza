package com.example.speedypizza.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.Days
import com.example.speedypizza.entity.Message
import com.example.speedypizza.entity.Shifts
import com.example.speedypizza.entity.User


@Dao
interface SpeedyPizzaDAO {

    @Insert(entity = User::class)
    fun insertUser(user: User)

    @Query("SELECT nickname, name, surname, password, phone, email, role  FROM User WHERE nickname = :username and password = :password")
    fun login(username: String, password: String): User

    @Insert(entity = Constraints::class, onConflict = OnConflictStrategy.REPLACE)
    fun sendConstraint(constraints: Constraints)

    @Query("SELECT * FROM User WHERE role = 1 or role = 3")
    fun retrieveMyRiders(): List<User>

    @Query("UPDATE User SET role = 3 WHERE nickname = :username")
    fun deleteRider(username: String)

    @Query("UPDATE User SET role = 1 WHERE nickname =:username")
    fun addRider(username: String)

    //@Query("INSERT INTO Shifts VALUES (:rider, :day)")
    @Insert(entity = Shifts::class)
    fun createCalendar2(shifts: Shifts)

    //@Query("INSERT INTO Days VALUES (:day, :min, :max)")
    @Insert(entity = Days::class)
    fun createCalendar1(day: Days)

    @Query("SELECT messageID, messageText, messageDate, messageReceiver, messageType FROM Message WHERE messageReceiver = :nickname")
    fun retrieveMessages(nickname: String): List<Message>

    @Query("DELETE FROM Exchanges")
    fun deleteExchanges()
    @Query("DELETE FROM Days")
    fun deleteDays()
    @Query("DELETE FROM Shifts")
    fun deleteShifts()

    @Query("DELETE FROM Message")
    fun deleteNotifications()


}