package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val origin: Location,
    val location: Location,
    val gender: String,
    val imageUrl: String,
    val url: String
) {
    @Serializable
    data class Location(
        val name: String,
        val url: String
    )
}