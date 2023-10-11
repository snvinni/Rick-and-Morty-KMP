package feature.details

sealed interface DetailsAction {
    class OpenLocation(val url: String) : DetailsAction
    class OpenEpisode(url: String) : DetailsAction
}