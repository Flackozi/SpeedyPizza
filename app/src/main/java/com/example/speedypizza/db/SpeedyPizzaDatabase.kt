package com.example.speedypizza.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User

@Database(
    entities = [User::class, Constraints::class],
    version = 6,
    //autoMigrations = [AutoMigration(4,5, spec = SpeedyPizzaDatabase.MigrazioneConstraints::class)],
    exportSchema = true
)

abstract class SpeedyPizzaDatabase: RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object{
        private var db: SpeedyPizzaDatabase? = null

        fun getInstance(context: Context): SpeedyPizzaDatabase{
            if(db==null){
                db = databaseBuilder(
                    context,
                    SpeedyPizzaDatabase::class.java,
                    "speedypizza.db"
                )
                    .fallbackToDestructiveMigration()
                    .createFromAsset("speedypizza.db")
                    //.addAutoMigrationSpec()
                    .build()
            }
            return db as SpeedyPizzaDatabase
        }
    }

    /*@RenameColumn(tableName = "Constraints", fromColumnName = "cc", toColumnName = "max")
    @DeleteColumn(tableName = "Constraints", columnName = "cc")
    class MigrazioneConstraints: AutoMigrationSpec{
        @Override
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            super.onPostMigrate(db)
        }
    }*/


}