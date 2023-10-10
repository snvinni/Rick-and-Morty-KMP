package feature.home

import core.viewmodel.BaseViewModel
import core.util.Resource
import data.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository = RickAndMortyRepository()
) : BaseViewModel() {

    val characters = rickAndMortyRepository.getCharacters(page = 1).stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = Resource.Loading
    )
}