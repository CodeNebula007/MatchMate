package com.example.matchmate.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.matchmate.data.model.Match

@Dao
interface MatchDao {

    @Query("SELECT * FROM matches")
    fun getAllMatches(): LiveData<List<Match>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(matches: List<Match>)

    @Update
    suspend fun updateMatch(match: Match)
}
