package feature.home

import core.viewmodel.BaseViewModel
import data.repository.RickAndMortyRepository
import domain.model.Character
import feature.paginator.LoadingType
import feature.paginator.Paginator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository()
) : BaseViewModel() {

    private val currentPageFlow = MutableStateFlow(1)
    val characterListFlow = MutableStateFlow(emptyList<Character>())
    val loadingType = MutableStateFlow<LoadingType>(LoadingType.FirstPage)
    private val loadError = MutableStateFlow("")

    private val pagination = Paginator(
        initialPage = 1,
        onLoadUpdated = {
            loadingType.update { it }
        },
        onRequest = {
            rickAndMortyRepository.getCharacters(
                page = it
            ).last()
        },
        getNextPage = {
            if (it.info.next == null) return@Paginator currentPageFlow.value
            currentPageFlow.value + 1
        },
        onSuccess = { itemList, newPage ->
            characterListFlow.update { it + itemList.results }
            currentPageFlow.update { newPage }
        },
        onError = { error ->
            loadError.update { error ?: "" }
        },
    )

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        scope.launch {
            pagination.requestItems()
        }
    }

    fun onItemClick(i: Int) {
        TODO("Not yet implemented")
    }
}