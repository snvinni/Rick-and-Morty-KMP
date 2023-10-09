package feature.home

import core.BaseViewModel
import core.Resource
import data.RickAndMortyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository()
) : BaseViewModel() {

    val characters = rickAndMortyRepository.getCharacters(page = 1).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )
}