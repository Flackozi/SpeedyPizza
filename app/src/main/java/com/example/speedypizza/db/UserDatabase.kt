package com.example.speedypizza.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.speedypizza.entity.User

@Database(
    entities = [User::class],
    version = 2
)

abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object{
        private var db: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase{
            if(db==null){
                db = databaseBuilder(
                    context,
                    UserDatabase::class.java,
                    "speedypizza.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("speedypizza.db")
                    .build()
            }
            return db as UserDatabase
        }
    }

}