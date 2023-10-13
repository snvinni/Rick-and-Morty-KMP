package feature.home

import core.util.Resource
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

    private val currentPage = MutableStateFlow(1)
    private val characterList = MutableStateFlow(emptyList<Character>())
    private val loadingType = MutableStateFlow<LoadingType>(LoadingType.FirstPage)
    private val loadError = MutableStateFlow("")

    val uiState = combine(
        currentPage,
        characterList,
        loadingType,
        loadError,
    ) { currentPage, characterList, loadingType, loadError ->
        HomeUiState(
            characterList = characterList,
            loadingType = loadingType,
            loadError = loadError,
            currentPage = currentPage
        )
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState(
            characterList = emptyList(),
            loadingType = LoadingType.FirstPage,
            loadError = "",
            currentPage = 1
        )
    )

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
            if (it.info.next == null) return@Paginator currentPage.value
            currentPage.value + 1
        },
        onSuccess = { itemList, newPage ->
            characterList.update { it + itemList.results }
            currentPage.update { newPage }
        },
        onError = { _ ->
            loadingType.update { LoadingType.Error }
        },
    )

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        if (currentPage.value != 1) {
            loadingType.update { LoadingType.NextPage }
        }

        scope.launch {
            pagination.requestItems()
        }
    }

    fun refresh() {
        characterList.value = emptyList()
        pagination.reset()
        loadCharacters()
    }

    fun onItemClick(i: Int) {
        TODO("Not yet implemented")
    }
}

data class HomeUiState(
    val characterList: List<Character>,
    val loadingType: LoadingType,
    val loadError: String,
    val currentPage: Int,
)