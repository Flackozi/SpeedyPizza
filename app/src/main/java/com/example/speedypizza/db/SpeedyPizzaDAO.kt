package com.example.speedypizza.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.Exchanges
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

    @Query("SELECT * FROM Shifts WHERE rider=:nickname")
    fun retrieveShifts(nickname: String): List<Shifts>

    @Query("SELECT * FROM Exchanges WHERE receiver=:nickname")
    fun retrieveRequests(nickname: String): List<Exchanges>

    @Query("DELETE FROM Exchanges WHERE sender = :senderName AND receiver= :nickname  AND senderShift= :senderShift AND receiverShift= :receiverShift")
    fun deleteRequest(
        nickname: String,
        senderName: String,
        senderShift: String,
        receiverShift: String
    )

    @Query("DELETE FROM Exchanges WHERE (sender= :senderName AND senderShift= :senderShift) OR (receiver= :senderName AND receiverShift= :senderShift)")
    fun deleteOtherRequest(senderName: String, senderShift: String)

    @Query("UPDATE Shifts SET day = :newShift WHERE rider =:rider AND day= :oldShift")
    fun updateShift(rider: String, newShift: String, oldShift: String)

    @Insert(entity = Exchanges::class)
    fun sendRequest(exchanges: Exchanges)

}