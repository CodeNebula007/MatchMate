package com.example.matchmate.ui.match

import androidx.lifecycle.*
import com.example.matchmate.data.model.Match
import com.example.matchmate.data.model.MatchStatus
import com.example.matchmate.data.repository.MatchRepository
import kotlinx.coroutines.launch

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    val matches: LiveData<List<Match>> = repository.getAllMatches()

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchMatches() {
        viewModelScope.launch {
            try {
                repository.fetchMatchesFromApi()
            } catch (e: Exception) {
                _error.value = "Failed to fetch matches: ${e.localizedMessage}"
            }
        }
    }

    fun updateMatchStatus(match: Match, status: MatchStatus) {
        viewModelScope.launch {
            match.status = status
            repository.updateMatchStatus(match)
        }
    }
}
