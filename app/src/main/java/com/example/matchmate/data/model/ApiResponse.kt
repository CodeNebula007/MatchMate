package com.example.matchmate.data.model

data class ApiResponse(
    val results: List<UserResult>
)

data class UserResult(
    val name: Name,
    val picture: Picture,
    val email: String,
    val location: Location,
    val dob: Dob
) {
    fun toMatch(): Match {
        return Match(
            fullName = "${name.first} ${name.last}",
            imageUrl = picture.large,
            email = email,
            city = location.city,
            country = location.country,
            age = dob.age
        )
    }
}

data class Name(val first: String, val last: String)
data class Picture(val large: String)
data class Location(val city: String, val country: String)
data class Dob(val age: Int)
