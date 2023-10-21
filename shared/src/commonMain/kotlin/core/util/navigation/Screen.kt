package core.util.navigation

import domain.model.Character

sealed class Screen {

    abstract val name: String

    data object Home : Screen() {
        override val name: String = "Rick and Morty"
    }

    data class CharacterDetails(
        val character: Character,
    ) : Screen() {
        override val name: String = character.name
    }

    data class EpisodeDetails(
        val episode: Character.Episode,
    ) : Screen() {
        override val name: String = "Episode: ${episode.number}"
    }

    class LocationDetails(location: Character.Location) : Screen() {
        override val name: String = location.name
    }
}