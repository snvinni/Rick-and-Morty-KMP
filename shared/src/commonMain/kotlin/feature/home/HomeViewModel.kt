package feature.home

import androidx.compose.runtime.Composable
import core.util.Resource
import core.viewmodel.BaseViewModel
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

@Composable
expect fun provideHomeViewModel() : HomeViewModel