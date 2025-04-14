package com.example.sqllite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqllite.entity.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao






    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) { synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "user_db").fallbackToDestructiveMigration().build()
            }
            }
            return INSTANCE
        }
    }

}
