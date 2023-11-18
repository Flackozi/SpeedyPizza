package com.example.speedypizza.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User


@Dao
interface UserDAO {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT nickname, name, surname, password, phone, email, role  FROM User WHERE nickname = :username and password = :password")
    fun login(username: String, password: String): User


    //@Insert(entity = Constraints::class, onConflict = OnConflictStrategy.REPLACE)
    //@Query("INSERT INTO Constraints VALUES (:nickname, :lunedi, :martedi, :mercoledi, :giovedi, :venerdi, :sabato, :domenica)")
    @Insert(entity = Constraints::class, onConflict = OnConflictStrategy.REPLACE)
    fun sendConstraint(
        /*nickname: String,
        lunedi: Int,
        martedi: Int,
        mercoledi: Int,
        giovedi: Int,
        venerdi: Int,
        sabato: Int,
        domenica: Int*/
        constraints: Constraints
    )

    @Query("SELECT * FROM User WHERE role = 1 or role = 3")
    fun retrieveMyRiders(): List<User>

    @Query("UPDATE User SET role = 3 WHERE nickname = :username")
    fun deleteRider(username: String)

    @Query("UPDATE User SET role = 1 WHERE nickname =:username")
    fun addRider(username: String)
}