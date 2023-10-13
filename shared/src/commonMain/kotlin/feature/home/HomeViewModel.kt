package feature.home

import core.viewmodel.BaseViewModel
import data.repository.RickAndMortyRepository
import domain.model.Character
import feature.paginator.LoadingType
import feature.paginator.Paginator
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository()
) : BaseViewModel() {

    private val currentPage = MutableStateFlow(1)
    private val characters = MutableStateFlow(emptyList<Character>())
    private val loadingType = MutableStateFlow<LoadingType>(LoadingType.FirstPage)

    val uiState = combine(
        characters,
        loadingType,
    ) { characterList, loadingType ->
        HomeUiState(
            characterList = characterList.toImmutableList(),
            loadingType = loadingType,
        )
    }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeUiState(
            characterList = persistentListOf(),
            loadingType = LoadingType.FirstPage,
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
            characters.update { it + itemList.results }
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
        characters.value = emptyList()
        pagination.reset()
        loadCharacters()
    }
}

data class HomeUiState(
    val characterList: ImmutableList<Character>,
    val loadingType: LoadingType,
)