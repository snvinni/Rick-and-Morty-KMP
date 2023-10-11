package data.repository

import core.util.Resource
import core.util.mapError
import core.util.mapSuccess
import data.service.RickAndMortyService
import domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RickAndMortyRepository(
    private val service: RickAndMortyService = RickAndMortyService()
) {

    fun getCharacters(page: Int): Flow<Resource<List<Character>, String>> {

        return flow {

            emit(Resource.Loading)

            val results = service.getCharacters(page = page)
                .mapSuccess { it.results }
                .mapError { it.message ?: "error" }

            emit(results)
        }
    }
}
