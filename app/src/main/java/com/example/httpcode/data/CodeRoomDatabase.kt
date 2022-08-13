package com.example.httpcode.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Code::class), version = 1, exportSchema = false)
public abstract class CodeRoomDatabase : RoomDatabase() {

    abstract fun codeDao(): CodeDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: CodeRoomDatabase? = null

        fun getDatabase(context: Context): CodeRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CodeRoomDatabase::class.java,
                    "code_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}