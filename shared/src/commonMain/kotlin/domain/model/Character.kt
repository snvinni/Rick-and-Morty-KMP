package domain.model

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val origin: Location,
    val location: Location,
    val episodes: List<Episode>,
    val gender: String,
    val imageUrl: String,
    val url: String
) {
    @Serializable
    data class Location(
        val name: String,
        val url: String
    )

    @Serializable(EpisodeSerializer::class)
    data class Episode(
        val url: String,
        val number: Int = url
            .substringAfterLast("/")
            .toInt()
    )
}

class EpisodeSerializer : KSerializer<Character.Episode> {

    override val descriptor = PrimitiveSerialDescriptor("Episode", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Character.Episode) {
        encoder.encodeString(value.url)
    }

    override fun deserialize(decoder: Decoder): Character.Episode {
        val url = decoder.decodeString()

        return Character.Episode(
            url = url
        )
    }
}
