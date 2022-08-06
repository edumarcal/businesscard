package me.dio.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusinessCard::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun businessDao() : IBusinessCardDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null;
        fun getDatabase(context:Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()

                INSTANCE = instance;

                return instance;
            }
        }
    }
}