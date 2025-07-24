package com.example.matchmate.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchmate.data.model.Match

@Database(entities = [Match::class], version = 1)
abstract class MatchDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchDatabase? = null

        fun getDatabase(context: Context): MatchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchDatabase::class.java,
                    "match_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
