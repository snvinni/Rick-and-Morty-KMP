package core.util.navigation

import domain.model.Character

sealed interface Action {
    class OpenLocation(val location: Character.Location) : Action
    class OpenEpisode(val episode: Character.Episode) : Action
    class OpenDetails(val character: Character) : Action
}