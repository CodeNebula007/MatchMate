package com.example.matchmate.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Match(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val imageUrl: String,
    val email: String,
    val city: String,
    val country: String,
    val age: Int,
    var status: MatchStatus = MatchStatus.NONE
)

enum class MatchStatus {
    ACCEPTED,
    DECLINED,
    NONE
}
