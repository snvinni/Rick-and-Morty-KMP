package core.util.navigation

import domain.model.Character

sealed interface Navigate {

    data object Back : Navigate

    class LocationDetails(val location: Character.Location) : Navigate
    class EpisodeDetails(val episode: Character.Episode) : Navigate
    class CharacterDetails(val character: Character) : Navigate
}