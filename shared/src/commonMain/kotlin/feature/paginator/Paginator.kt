package feature.paginator

import core.util.Resource

class Paginator<Data>(
    initialPage: Int,
    private inline val onLoadUpdated: (LoadingType) -> Unit,
    private inline val onRequest: suspend (page: Int) -> Resource<Data, String>,
    private inline val getNextPage: suspend (Data) -> Int,
    private inline val onSuccess: suspend (itemList: Data, newPage: Int) -> Unit,
    private inline val onError: suspend (String?) -> Unit,
) {
    private var currentPage = initialPage
    private var isMakingRequest = false
    suspend fun requestItems() {
        isMakingRequest = true

        val result = onRequest(currentPage).also {
            onLoadUpdated(LoadingType.Done)
            isMakingRequest = false
        }

        when (result) {
            is Resource.Result.Success -> {
                currentPage = getNextPage(result.data)
                onSuccess(result.data, currentPage)
            }

            is Resource.Result.Failure -> {
                onError(
                    result.error,
                )
            }

            Resource.Loading -> onLoadUpdated(if (currentPage == 1) LoadingType.FirstPage else LoadingType.NextPage)

        }
    }

    fun reset() {
        currentPage = 1
    }
}
