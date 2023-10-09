package data

import core.Resource
import core.mapError
import core.mapSuccess
import data.service.RickAndMortyService
import domain.model.Characters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RickAndMortyRepository(
    private val service: RickAndMortyService = RickAndMortyService()
) {

    fun getCharacters(page: Int): Flow<Resource<List<Characters>, String>> {

        return flow {

            emit(Resource.Loading)

            val results = service.getCharacters(page = page)
                .mapSuccess { it.results }
                .mapError { it.message ?: "Error" }

            emit(results)
        }
    }
}
