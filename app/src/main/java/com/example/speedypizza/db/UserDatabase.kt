package com.example.speedypizza.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.speedypizza.entity.Constraints
import com.example.speedypizza.entity.User

@Database(
    entities = [User::class, Constraints::class],
    version = 5,
    autoMigrations = [AutoMigration(3,5, spec = UserDatabase.MigrazioneConstraints::class)],
    exportSchema = true
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
                    //.fallbackToDestructiveMigration()
                    //.createFromAsset("speedypizza.db")
                    //.addAutoMigrationSpec()
                    .build()
            }
            return db as UserDatabase
        }
    }

    @RenameColumn(tableName = "Constraints", fromColumnName = "cc", toColumnName = "max")
    @DeleteColumn(tableName = "Constraints", columnName = "cc")
    class MigrazioneConstraints: AutoMigrationSpec{
        @Override
        override fun onPostMigrate(db: SupportSQLiteDatabase) {
            super.onPostMigrate(db)
        }
    }


}