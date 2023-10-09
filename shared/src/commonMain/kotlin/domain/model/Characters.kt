package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String,
    val url: String
)