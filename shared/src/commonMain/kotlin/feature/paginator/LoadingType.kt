package feature.paginator

sealed interface LoadingType {
    data object FirstPage : LoadingType

    data object NextPage : LoadingType

    data object Done : LoadingType
}