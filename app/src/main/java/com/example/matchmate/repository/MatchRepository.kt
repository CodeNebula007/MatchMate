package com.example.matchmate.data.repository

import androidx.lifecycle.LiveData
import com.example.matchmate.data.api.RetrofitInstance
import com.example.matchmate.data.db.MatchDao
import com.example.matchmate.data.model.Match
import com.example.matchmate.data.model.UserResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MatchRepository(private val matchDao: MatchDao) {

    fun getAllMatches(): LiveData<List<Match>> = matchDao.getAllMatches()

    suspend fun fetchMatchesFromApi(): List<Match> {
        return withContext(Dispatchers.IO) {
            val response = RetrofitInstance.api.getMatches()
            if (response.isSuccessful) {
                val users = response.body()?.results ?: emptyList()
                val matches = users.map(UserResult::toMatch)
                matchDao.insertAll(matches)
                matches
            } else {
                emptyList()
            }
        }
    }

    suspend fun updateMatchStatus(match: Match) {
        matchDao.updateMatch(match)
    }
}
